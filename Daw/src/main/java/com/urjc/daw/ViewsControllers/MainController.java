package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Item.ItemService;
import com.urjc.daw.Models.Lessons.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    LessonService lessonService;

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



    @RequestMapping(path = "/TeacherConcept")
    public String showConceptTeacher(Model model) {
        model.addAttribute("itemsCorrect",itemService.findItemByState(true));
        model.addAttribute("itemsIncorrect",itemService.findItemByState(false));
        return "ConceptView/ConceptTeacher/TeacherConcept_View";

    }
}
