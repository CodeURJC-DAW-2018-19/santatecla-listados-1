package com.urjc.daw.api_rest;

import com.urjc.daw.models.answer.Answer;
import com.urjc.daw.models.answer.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answer")
public class AnswerRest {

    @Autowired
    AnswerService answerService;

    @GetMapping(value = "/{id}")
    public Answer getAnswer(@PathVariable long id) {
        Answer answer = answerService.findOne(id).get();
        System.out.println(answer.getIdAnswer());
        return answer;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Answer createAnswer(@RequestBody Answer answer){
        answerService.addAnswer(answer);
        return answer;
    }

    @PutMapping("/{id}")
    public Answer updateAnswer(@PathVariable long id, @RequestBody Answer updatedAnswer){
        answerService.findOne(id).get();
        updatedAnswer.setIdAnswer(id);
        answerService.addAnswer(updatedAnswer);
        return updatedAnswer;
    }
}
