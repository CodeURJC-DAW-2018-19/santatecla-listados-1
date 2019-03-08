package com.urjc.daw.api_rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.lessons.Lesson;
import com.urjc.daw.models.lessons.LessonService;
import com.urjc.daw.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lesson")
public class LessonsRest {

    interface LessonDetails extends Lesson.BasicInfo,Lesson.ConceptList, Concept.BasicInfo{}

    @Autowired
    LessonService lessonService;

    @GetMapping(value = "/{id}")
    @JsonView(LessonDetails.class)
    public ResponseEntity<Lesson> getLesson(@PathVariable long id) {
        Optional<Lesson> lesson=lessonService.findOne(id);
        if(lesson.isPresent()){
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/pag")
    public Page<Lesson> getPage (Pageable page){
        return lessonService.findAll(page);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(LessonDetails.class)
    public Lesson createLesson(@RequestBody Lesson lesson){
        lessonService.addLesson(lesson);
        return lesson;
    }

    @PutMapping("/{id}")
    @JsonView(LessonDetails.class)
    public Lesson updateLesson(@PathVariable long id, @RequestBody Lesson updatedLesson){
        lessonService.findOne(id).get();
        updatedLesson.setIdLesson(id);
        lessonService.addLesson(updatedLesson);
        return updatedLesson;
    }

    @DeleteMapping("/{id}")
    @JsonView(LessonDetails.class)
    public Lesson deteleLesson(@PathVariable long id){
        Lesson deleteLesson = lessonService.findOne(id).get();
        lessonService.deleteLessonById(id);
        return deleteLesson;
    }
}
