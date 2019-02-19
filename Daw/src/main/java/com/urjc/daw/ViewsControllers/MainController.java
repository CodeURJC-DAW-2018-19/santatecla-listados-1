package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Concept.ConceptService;
import com.urjc.daw.Models.Item.ItemService;
import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Lessons.LessonService;
import com.urjc.daw.Models.User.User;
import com.urjc.daw.Models.User.UserRepository;
import com.urjc.daw.Models.Question.QuestionService;
import com.urjc.daw.Models.Answer.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    LessonService lessonService;

    @Autowired
    ConceptService conceptService;

    @Autowired
    ItemService itemService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;



    @RequestMapping(path = "/")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(path = "/sign_in")
    public String signin(Model model){
        return "sign_in";
    }

    @PostMapping(path = "/add")
    public @ResponseBody String add (Model model,User user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        if(user.getName()==null || user.getpassword()==null){
            return "sign_in";
        }else{
            user.setUserType("ROLE_STUDENT");
            userRepository.save(user);
            return "login";
        }
    }


    @RequestMapping(path = "/MainPage")
    public String showMainPage(Model model, HttpServletRequest request) {
        model.addAttribute("lessons", lessonService.findAll());
        model.addAttribute("admin",request.isUserInRole("STUDENT"));
        return "MainPage";
    }

    @GetMapping("/deleteLessons/{id}")
    public String deleteLessons(Model model,@PathVariable long id) {
        lessonService.deleteLessonById(id);
        return "MainPage";
    }

    @GetMapping("/deleteConcept/{id}")
    public String deleteConcept(Model model,@PathVariable long id) {
        conceptService.deleteConceptById(id);
        return "MainPage";
    }

    @PostMapping("/saveLesson")
    public String saveLesson(Model model, Lesson lesson) {
        lessonService.addLesson(lesson);
        return "MainPage";
    }

    @RequestMapping(path = "/TeacherConcept")
    public String showConceptTeacher(Model model) {
        model.addAttribute("itemsCorrect", itemService.findItemByState(true));
        model.addAttribute("itemsIncorrect", itemService.findItemByState(false));
        return "ConceptView/TeacherConcept_View";
    }

    @RequestMapping(path = "/StudentConcept")
    public String showStudentConcept(Model model) {
        model.addAttribute("question", questionService.findAll());
        model.addAttribute("answer", answerService.findAll());
        model.addAttribute("items", itemService.findAll());
        return "ConceptView/StudentConceptView";
    }
}
