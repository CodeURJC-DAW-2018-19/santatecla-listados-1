package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Answer.Answer;
import com.urjc.daw.Models.Answer.AnswerService;
import com.urjc.daw.Models.Concept.ConceptService;
import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Question.Question;
import com.urjc.daw.Models.Question.QuestionService;
import com.urjc.daw.Models.User.UserComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class AnswerController {

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


    @GetMapping(path = "/updateAnswerTrue/{idAnswer}/{idConcept}")
    public String updateTrue(Model model, @PathVariable long idAnswer, @PathVariable long idConcept) {
        Optional<Answer> ans = answerService.findOne(idAnswer);
        ans.get().setState("right");
        ans.get().correct();
        answerService.addAnswer(ans.get());
        questionService.addQuestion(ans.get().accesToQuestion());
        return "redirect:/TeacherConcept_View/{idConcept}";
    }


    @GetMapping(path = "/updateAnswerFalse/{idAnswer}/{idConcept}")
    public String updateFalse(Model model, @PathVariable long idAnswer, @PathVariable long idConcept) {
        Optional<Answer> ans = answerService.findOne(idAnswer);
        ans.get().setState("wrong");
        ans.get().correct();
        answerService.addAnswer(ans.get());
        questionService.addQuestion(ans.get().accesToQuestion());
        return "redirect:/TeacherConcept_View/{idConcept}";
    }

    @PostMapping(path = "/saveAnswer/{idQuestion}")
    public String addAnswer(Model model, Answer answer, @PathVariable long idQuestion) {
        Optional<Question> question = questionService.findOne(idQuestion);
        int type = question.get().getType();
        answer.setQuestion(question.get());
        answer.setState("pending");
        answer.setCorrect(false);
        answer.setIdUser(userComponent.getLoggedUser());
        answerService.addAnswer(answer);
        question.get().addAnswer(answer);
        question.get().metrics();
        questionService.addQuestion(question.get());
        return "redirect:/StudentConceptView/" + question.get().getIdConcept();
    }


    @GetMapping(path = "/sendAnswerTrue/{idQuestion}/{correct}")
    public String sendAnswer(Model model, @PathVariable long idQuestion, @PathVariable boolean correct) {
        String correcto = "";
        if (correct)
            correcto = "true";
        else
            correcto = "false";

        Answer answer = new Answer(correcto);

        Question question = questionService.findOne(idQuestion).get();
        answer.setIdUser(userComponent.getLoggedUser());
        answer.setQuestion(question);
        question.addAnswer(answer);
        question.metrics();
        answerService.addAnswer(answer);
        questionService.addQuestion(question);
        answer.correctType1(correct);
        answerService.addAnswer(answer);
        return "redirect:/StudentConceptView/" + question.getIdConcept();
    }

    @GetMapping(path = "/sendSelectedItems/{idQuestion}/{ret}/{total}")
    public String sendItemsSelected(Model model, @PathVariable long idQuestion, @PathVariable String ret, @PathVariable String total) {
        String[] items = ret.split("sss");
        String[] all = total.split("sss");
        Answer answer = new Answer(ret);

        Question question = questionService.findOne(idQuestion).get();
        answer.setIdUser(userComponent.getLoggedUser());
        answer.setQuestion(question);
        question.addAnswer(answer);
        question.metrics();
        answerService.addAnswer(answer);
        questionService.addQuestion(question);
        answer.correctType2(items, all);
        answerService.addAnswer(answer);

        return "redirect:/StudentConceptView/" + question.getIdConcept();
    }


}
