package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Lessons.LessonService;
import com.urjc.daw.Models.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LessonController {

    @Autowired
    LessonService lessonService;





    @PostMapping("/lessonSearch")
    public String indexSearch(Model model, @RequestParam("searchText") String searchText){
        List<Lesson> searchLessons = lessonService.searchLessons(searchText);
        model.addAttribute("lessons", searchLessons);
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
