package com.urjc.daw.api_rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.files.UploadFileServiceImpl;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.concept.ConceptService;
import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.lessons.Lesson;
import com.urjc.daw.models.lessons.LessonService;
import com.urjc.daw.models.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("api/concept")
public class ConceptRest extends OperationsRest<Concept> {

    interface ConceptDetails extends Concept.BasicInfo,Concept.ItemList,Concept.QuestionList,
            Item.BasicInfo,Question.BasicInfo {}
            
    @Autowired
    ConceptService conceptService;
    @Autowired
    LessonService lessonService;
    @Autowired
    UploadFileServiceImpl uploadFileService;

    @GetMapping(value ="/{id}")
    @JsonView(ConceptDetails.class)
    public ResponseEntity<Concept> getConcept(@PathVariable long id){
        Optional<Concept> concept= conceptService.findByOneId(id);
        return safeDelete(concept,conceptService.repository);
    }

    @GetMapping(value ="/lesson/{idLesson}")
    @JsonView(ConceptDetails.class)
    public Page<Concept> getConcepts(@PathVariable long idLesson,@PageableDefault(size = 10)Pageable page){
        return conceptService.findByLesson(lessonService.findOne(idLesson).get(),page);
    }

    @DeleteMapping(value ="/{id}")
    @JsonView(ConceptDetails.class)
    public ResponseEntity<Concept> deleteConcept(@PathVariable long id){
        return safeDelete(conceptService.findByOneId(id),conceptService.repository);
    }

    @PostMapping("/lesson/{idLesson}")
    @JsonView(ConceptDetails.class)
    public ResponseEntity<Concept> saveConcept(@RequestBody Concept concept, @RequestParam("file") MultipartFile multipartFile, @PathVariable long idLesson) throws IOException {
        String filename = uploadFileService.copy(multipartFile);
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(filename).toUriString();
        concept.setPicture(fileUrl);

        Optional<Lesson> optionalLesson = lessonService.findOne(idLesson);
        if(optionalLesson.isPresent()){
            Lesson lesson = optionalLesson.get();

            concept.setIdLesson(lesson);
            ResponseEntity<Concept> responseEntity = safeCreate(concept,conceptService.repository);

            lesson.addConcept(concept);

            lessonService.addLesson(lesson);
            return responseEntity;

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
