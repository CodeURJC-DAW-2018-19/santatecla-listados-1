package com.urjc.daw.api_rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.answer.Answer;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.question.Question;
import com.urjc.daw.models.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/question")
public class QuestionRest extends OperationsRest<Question> {

    interface QuestionDetails extends Question.BasicInfo,Question.AnswerList,Question.ConceptDet,Question.ItemList,
            Concept.BasicInfo, Answer.BasicInfo, Item.BasicInfo {}

    @Autowired
    QuestionService questionService;

    @GetMapping(value = "/{id}")
    @JsonView(QuestionDetails.class)
    public ResponseEntity<Question> getQuestion (@PathVariable long id) {
        Optional<Question> question = questionService.findOne(id);
        return checkIfExist(question);
    }

    @GetMapping("/")
    @JsonView(QuestionDetails.class)
    public Collection<Question> getQuestions() {
        return questionService.findAll();
    }

    @DeleteMapping("/{id}")
    @JsonView(QuestionDetails.class)
    public Question deleteBook(@PathVariable long id) {
        Question deletedBook = questionService.findOne(id).get();
        questionService.delete(id);
        return deletedBook;
    }

}
