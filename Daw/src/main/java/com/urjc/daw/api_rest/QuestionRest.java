package com.urjc.daw.api_rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.answer.Answer;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.lessons.Lesson;
import com.urjc.daw.models.question.Question;
import com.urjc.daw.models.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/question")
public class QuestionRest {

    interface QuestionDetails extends Question.BasicInfo,Question.AnswerList,Question.ConceptDet,
            Concept.BasicInfo, Answer.BasicInfo {}

    @Autowired
    QuestionService service;

    @GetMapping(value = "/{id}")
    @JsonView(QuestionDetails.class)
    public Question getQuestion(@PathVariable long id) {
        return service.findOne(id).get();
    }

    @GetMapping("/")
    @JsonView(QuestionDetails.class)
    public Collection<Question> getQuestions() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @JsonView(QuestionDetails.class)
    public Question deleteBook(@PathVariable long id) {
        Question deletedBook = service.findOne(id).get();
        service.delete(id);
        return deletedBook;
    }

}
