package com.urjc.daw.api_rest;

import com.urjc.daw.models.lessons.Lesson;
import com.urjc.daw.models.lessons.LessonService;
import com.urjc.daw.models.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lesson")
public class LessonsRest {
    LessonService lessonService;

    @GetMapping(value = "/{id}")
    public Lesson getLesson(@PathVariable long id) {
        Lesson lesson = lessonService.findOne(id).get();
        System.out.println(lesson.getIdLesson());
        return lesson;
    }
}
