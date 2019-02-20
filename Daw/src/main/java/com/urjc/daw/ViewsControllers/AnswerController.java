package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Answer.AnswerService;
import com.urjc.daw.Models.Lessons.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnswerController {

    @Autowired
    AnswerService answerService;

}
