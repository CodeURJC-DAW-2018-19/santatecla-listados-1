package com.urjc.daw.api_rest;


import com.urjc.daw.models.lessons.Lesson;
import com.urjc.daw.models.question.Question;
import com.urjc.daw.models.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/question")
public class QuestionRest {


    @Autowired
    QuestionService service;

    @GetMapping(value = "/{id}")
    public Question getQuestion(@PathVariable long id) {
        return service.findOne(id).get();
    }

    @GetMapping("/")
    public Collection<Question> getQuestions() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public Question deleteBook(@PathVariable long id) {
        Question deletedBook = service.findOne(id).get();
        service.delete(id);
        return deletedBook;
    }

}
