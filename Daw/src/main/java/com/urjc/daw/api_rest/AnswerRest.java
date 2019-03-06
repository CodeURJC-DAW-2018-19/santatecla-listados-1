package com.urjc.daw.api_rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.answer.Answer;
import com.urjc.daw.models.answer.AnswerService;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.question.Question;
import com.urjc.daw.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answer")
public class AnswerRest {
    interface AnswerDetails extends Answer.BasicInfo,Answer.QuestionDet,Answer.UserDet,
            User.BasicInfo,Question.BasicInfo {}
    @Autowired
    AnswerService answerService;

    @GetMapping(value = "/{id}")
    @JsonView(AnswerDetails.class)
    public Answer getAnswer(@PathVariable long id) {
        Answer answer = answerService.findOne(id).get();
        System.out.println(answer.getIdAnswer());
        return answer;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(AnswerDetails.class)
    public Answer createAnswer(@RequestBody Answer answer){
        answerService.addAnswer(answer);
        return answer;
    }

    @PutMapping("/{id}")
    @JsonView(AnswerDetails.class)
    public Answer updateAnswer(@PathVariable long id, @RequestBody Answer updatedAnswer){
        answerService.findOne(id).get();
        updatedAnswer.setIdAnswer(id);
        answerService.addAnswer(updatedAnswer);
        return updatedAnswer;
    }
}
