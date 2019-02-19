package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Concept.ConceptService;
import com.urjc.daw.Models.Item.ItemService;
import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Lessons.LessonService;
import com.urjc.daw.Models.User.User;
import com.urjc.daw.Models.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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


    @RequestMapping(path = "/")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(path = "/sign_in")
    public String signin(Model model){
        return "sign_in";
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String add (Model model,User user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        if(user.getName()==null || user.getpassword()==null || user.getUserType()==null){
            return "sign_in";
        }else{
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

    @RequestMapping("/saveLesson")
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
}
