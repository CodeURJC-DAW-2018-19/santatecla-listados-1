package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Question.Question;
import com.urjc.daw.Models.Question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class QuestionController {

    @Autowired
    public QuestionService questionService;

    @GetMapping("/deleteQuestion/{id}")
    public String deleteConcept(Model model, @PathVariable long id) {
        questionService.deleteQuestionById(id);
        return "redirect:/addNewQuestions";
    }

    @PostMapping("/saveQuestion/{idQuestion}")
    public String saveQuestion(@Valid Question question, Model model, SessionStatus sessionStatus) {
        questionService.addQuestion(question);
        sessionStatus.setComplete();
        return "redirect:/addnewQuestions";
    }
}
