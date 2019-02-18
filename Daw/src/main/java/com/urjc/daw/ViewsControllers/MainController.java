package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.Concept.ConceptService;
import com.urjc.daw.Models.Item.ItemService;
import com.urjc.daw.Models.Lessons.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    LessonService lessonService;

    @Autowired
    ConceptService conceptService;

    @Autowired
    ItemService itemService;


    @GetMapping(path = "/")
    public String showIndex(Model model) {
        return "index";
    }

    @GetMapping(path = "/mainPage")
    public String showMainPage(Model model) {
        model.addAttribute("lessons", lessonService.findAll());
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

    @GetMapping(path = "/TeacherConcept")
    public String showConceptTeacher(Model model) {
        model.addAttribute("itemsCorrect", itemService.findItemByState(true));
        model.addAttribute("itemsIncorrect", itemService.findItemByState(false));
        return "ConceptView/ConceptTeacher/TeacherConcept_View";
    }
}
