package com.urjc.daw.api_rest;

import com.urjc.daw.models.lessons.LessonService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessonsRest {
    LessonService lessonService;
}
