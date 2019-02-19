package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Lessons.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LessonController {

    @Autowired
    LessonService lessonService;


    @RequestMapping(path = "/MainPage")
    public String showMainPage(Model model, HttpServletRequest request) {
        model.addAttribute("lessons", lessonService.findAll());
        model.addAttribute("admin",request.isUserInRole("STUDENT"));
        return "MainPage";
    }

    @GetMapping("/deleteLessons/{id}")
    public String deleteLessons(Model model, @PathVariable long id) {
        lessonService.deleteLessonById(id);
        return "redirect:/MainPage";
    }

    @PostMapping("/saveLesson")
    public String saveLesson(Model model, Lesson lesson) {
        lessonService.addLesson(lesson);
        return "redirect:/MainPage";
    }

}