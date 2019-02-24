package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Answer.Answer;
import com.urjc.daw.Models.Answer.AnswerService;
import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Question.Question;
import com.urjc.daw.Models.Question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;


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
        answer.setQuestion(question.get());
        answer.setState("pending");
        answer.setCorrect(false);
        answerService.addAnswer(answer);
        question.get().addAnswer(answer);
        questionService.addQuestion(question.get());
        return "redirect:/MainPage";
    }

}
