package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.Concept.ConceptService;
import com.urjc.daw.Models.Item.ItemService;
import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Lessons.LessonService;
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


    @RequestMapping(path = "/")
    public String login(Model model) {
        return "login";
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
        return "ConceptView/ConceptTeacher/TeacherConcept_View";
    }
}
