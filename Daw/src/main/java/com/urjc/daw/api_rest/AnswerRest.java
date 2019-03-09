package com.urjc.daw.api_rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.answer.Answer;
import com.urjc.daw.models.answer.AnswerService;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.concept.ConceptService;
import com.urjc.daw.models.question.Question;
import com.urjc.daw.models.question.QuestionService;
import com.urjc.daw.models.user.User;
import com.urjc.daw.models.user.UserComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/answer")
public class AnswerRest extends OperationsRest<Answer> {
    interface AnswerDetails extends Answer.BasicInfo, Answer.QuestionDet, Answer.UserDet,
            User.BasicInfo, Question.BasicInfo {
    }

    @Autowired
    AnswerService answerService;

    @Autowired
    ConceptService conceptService;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserComponent userComponent;

    @ModelAttribute
    public void addUserToModel(Model model) {
        boolean logged = userComponent.getLoggedUser() != null;
        model.addAttribute("logged", logged);
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
    public ResponseEntity<Answer> saveAnswer (@RequestBody Answer answer, @PathVariable long idQuestion){
        Optional<Question> q = questionService.findOne(idQuestion);
        ResponseEntity<Answer> responseEntity;
        if (q.isPresent()) {
            Question question = q.get();
            //* * * *    Update answer's info    * * * *
            answer.setState("pending");
            answer.setCorrect(false);
            answer.setIdUser(userComponent.getLoggedUser());
            answer.setQuestion(question);
            responseEntity = safeCreate(answer,answerService.repository);
            //* * * *    Update question's parameters related to answer    * * * *
            question.addAnswer(answer);
            question.metrics();
            questionService.addQuestion(question);
        }else{
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
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
