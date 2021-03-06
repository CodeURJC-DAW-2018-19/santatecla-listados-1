package com.urjc.daw.api_rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.answer.Answer;
import com.urjc.daw.models.answer.AnswerService;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.concept.ConceptService;
import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.question.Question;
import com.urjc.daw.models.question.QuestionService;
import com.urjc.daw.models.user.User;
import com.urjc.daw.models.user.UserComponent;
import com.urjc.daw.models.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/answer")
public class AnswerRest extends OperationsRest<Answer> {

    interface AnswerDetails extends Answer.BasicInfo, Answer.QuestionDet, Answer.UserDet,
            User.BasicInfo, Question.BasicInfo, Question.AnswerList {
    }
    interface ConceptDetails extends Concept.QuestionList,Answer.BasicInfo, Answer.QuestionDet, Answer.UserDet,
            User.BasicInfo, Question.BasicInfo, Question.AnswerList {
    }

    @Autowired
    AnswerService answerService;

    @Autowired
    ConceptService conceptService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @Autowired
    UserComponent userComponent;

    @ModelAttribute
    public void addUserToModel(Model model) {
        boolean logged = userComponent.getLoggedUser() != null;
        model.addAttribute("logged", logged);
    }

    @GetMapping(value = "/user/{idUser}")
    @JsonView(AnswerDetails.class)
    public Page<Answer> getConcepts(@PathVariable long idUser,@PageableDefault(size = 10)Pageable page){
        return answerService.getByIdUser(page,userService.findById(idUser).get());
    }

    @GetMapping(value = "/user/{idUser}/{idConcept}")
    @JsonView(AnswerDetails.class)
    public ResponseEntity<List<Answer>> getConcepts(@PathVariable long idUser,@PathVariable long idConcept){
        List<Answer> list = new ArrayList<>();
        List<Answer> aux = new ArrayList<>();
        Optional<Concept> optional = conceptService.findByOneId(idConcept);
        if(optional.isPresent()){
            for (Question q:optional.get().getSetQuestion()) {
                list.addAll(q.getAnswer());
            }
            for (Answer ans: list) {
                if(ans.getIdUser()==idUser){
                    aux.add(ans);
                }
            }
            return new ResponseEntity<>(aux, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /*@GetMapping(value = "/concept/{idConcept}")
    @JsonView(ConceptDetails.class)
    public ResponseEntity<Concept> getConcepts(@PathVariable long idConcept) {
        Optional<Concept> concept = conceptService.findByOneId(idConcept);
        if (concept.isPresent()) {
            return new ResponseEntity<>(concept.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @GetMapping(value = "/concept/{idConcept}")
    @JsonView(QuestionRest.QuestionDetails.class)
    public ResponseEntity<List<Answer>> getAnswersByConcept(@PathVariable long idConcept, @PageableDefault Pageable page){
        List<Answer> list = new ArrayList<>();
        Optional<Concept> optional = conceptService.findByOneId(idConcept);
        if(optional.isPresent()){
            for (Question q:optional.get().getSetQuestion()) {
                list.addAll(q.getAnswer());
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    @JsonView(AnswerDetails.class)
    public ResponseEntity<Answer> getAnswer(@PathVariable long id) {
        Optional<Answer> answer = answerService.findOne(id);
        return checkIfExist(answer);
    }

    @PostMapping("/{idQuestion}")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(AnswerDetails.class)
    public ResponseEntity<Answer> saveAnswer(@RequestBody Answer answer, @PathVariable long idQuestion) {
        Optional<Question> q = questionService.findOne(idQuestion);
        ResponseEntity<Answer> responseEntity;
        if (q.isPresent()) {
            Question question = q.get();

            //* * * *    Update answer    * * * *
            answer.setIdUser(userComponent.getLoggedUser());
            answer.setQuestion(question);

            if(question.getType()==1){
                answer.correctType1(answer.getInfo().equals("true"));
            }else if(question.getType()==3){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }else{
                answer.setState("pending");
                answer.setCorrect(false);
            }

            responseEntity = safeCreate(answer, answerService.repository);
            //* * * *    Update question's parameters related to answer    * * * *
            question.addAnswer(answer);
            question.metrics();
            questionService.addQuestion(question);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    @PutMapping(path = "/{idQuestion}/{correction}")
    @JsonView(AnswerDetails.class)
    public ResponseEntity<Answer> correctionTeacher(@PathVariable long idQuestion, @PathVariable boolean correction){
        Optional<Question> option = questionService.findOne(idQuestion);
        if(option.isPresent()){
            Answer ans = new ArrayList<>(option.get().getAnswer()).get(0);
            ans.correctionTeacher(correction);
            answerService.addAnswer(ans);
            option.get().metrics();
            questionService.addQuestion(option.get());
            return new ResponseEntity<Answer>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Answer>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping(path = "/{idQuestion}/options/{ret}/{total}")
    @JsonView(AnswerDetails.class)
    public ResponseEntity<Answer> sendItemsSelected(@PathVariable long idQuestion, @PathVariable String ret, @PathVariable String total) {

        Optional<Question> option = questionService.findOne(idQuestion);

        if (option.isPresent()) {
            Question question = option.get();
            if(question.getType()!=3){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Answer answer = new Answer(ret);
            answer.setIdUser(userComponent.getLoggedUser());
            answer.setQuestion(question);
            answer.correctType2(ret.split("sss"), total.split("sss"));
            ResponseEntity<Answer> responseEntity = safeCreate(answer, answerService.repository);

            question.addAnswer(answer);
            question.metrics();
            questionService.addQuestion(question);

            return responseEntity;

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @JsonView(AnswerDetails.class)
    public Answer updateAnswer(@PathVariable long id, @RequestBody Answer updatedAnswer) {
        answerService.findOne(id).get();
        updatedAnswer.setIdAnswer(id);
        answerService.addAnswer(updatedAnswer);
        return updatedAnswer;
    }
}
