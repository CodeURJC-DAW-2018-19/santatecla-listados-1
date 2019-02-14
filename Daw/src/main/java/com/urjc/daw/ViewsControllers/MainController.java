package com.urjc.daw.ViewsControllers;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Item.ItemRepository;
import com.urjc.daw.Models.Item.ItemService;
import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Lessons.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    LessonService lessonService;

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



    @GetMapping(path = "/TeacherConcept")
    public String showConceptTeacher(Model model) {
        model.addAttribute("itemsCorrect",itemService.findItemByState(true));
        model.addAttribute("itemsIncorrect",itemService.findItemByState(false));
        return "ConceptView/ConceptTeacher/TeacherConcept_View";

    }
}
