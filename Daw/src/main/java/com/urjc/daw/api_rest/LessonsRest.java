package com.urjc.daw.api_rest;

import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.lessons.Lesson;
import com.urjc.daw.models.lessons.LessonService;
import com.urjc.daw.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;
import java.util.List;

@RestController
@RequestMapping("/api/lesson")
public class LessonsRest {

    @Autowired
    LessonService lessonService;

    @GetMapping(value = "/{id}")
    public Lesson getLesson(@PathVariable long id) {
        Lesson lesson = lessonService.findOne(id).get();
        System.out.println(lesson.getIdLesson());
        return lesson;
    }

    @GetMapping(value = "/pag")
    public Page<Lesson> get (Pageable page){
        return lessonService.findAll(page);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Lesson createLesson(@RequestBody Lesson lesson){
        lessonService.addLesson(lesson);
        return lesson;
    }

    @PutMapping("/{id}")
    public Lesson updateLesson(@PathVariable long id, @RequestBody Lesson updatedLesson){
        lessonService.findOne(id).get();
        updatedLesson.setIdLesson(id);
        lessonService.addLesson(updatedLesson);
        return updatedLesson;
    }

    @DeleteMapping("/{id}")
    public Lesson deteleLesson(@PathVariable long id){
        Lesson deleteLesson = lessonService.findOne(id).get();
        lessonService.deleteLessonById(id);
        return deleteLesson;
    }
}
