package com.urjc.daw.views_controllers;

import com.urjc.daw.files.UploadFileServiceImpl;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.concept.ConceptRepository;
import com.urjc.daw.models.concept.ConceptService;
import com.urjc.daw.models.item.ItemRepository;
import com.urjc.daw.models.lessons.LessonService;
import com.urjc.daw.models.question.QuestionRepository;
import com.urjc.daw.models.answer.AnswerRepository;
import com.urjc.daw.models.user.UserRepository;
import com.urjc.daw.models.lessons.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ConceptController {
    @Autowired
    ConceptService conceptService;

    @Autowired
    ConceptRepository conceptRepository;

    @Autowired
    LessonService lessonService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UploadFileServiceImpl uploadFileService;

    @GetMapping("/deleteConcept/{id}")
    public String deleteConcept(Model model, @PathVariable long id) {
        conceptService.deleteConceptById(id);
        return "redirect:/MainPage";
    }

    @PostMapping("/saveConcept/{id}")
    public String saveConcept(Model model, Concept concept, @PathVariable long id, @RequestParam("file") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            concept.setPicture(uniqueFilename);
        }

        Lesson lesson = lessonService.findOne(id).get();
        concept.setIdLesson(lesson);
        conceptService.addConcept(concept);
        lesson.addConcept(concept);
        lessonService.addLesson(lesson);
        return "redirect:/MainPage";
    }
}
