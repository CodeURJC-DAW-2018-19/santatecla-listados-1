package com.urjc.daw.ViewsControllers;

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

    @GetMapping(path = "/")
    public String showIndex(Model model){
        return "index";
    }

    @GetMapping(path = "/mainPage")
    public String showMainPage(Model model){
        model.addAttribute("lessons", lessonService.findAll());
        return "MainPage";
    }

    @GetMapping("/lessons/{id}")
    public String showBook(Model model, @PathVariable Integer id) {

        Optional<Lesson> lesson = lessonService.findOne(id);
        if(lesson.isPresent()) {
            model.addAttribute("lesson", lesson.get());
        }

        return "MainPage";
    }

    @GetMapping(path = "/TeacherConcept")
    public String showConceptTeacher(Model model){
        return "ConceptView/TeacherConcept_View";
    }
}
