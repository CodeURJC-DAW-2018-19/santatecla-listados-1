package com.urjc.daw.api_rest;


import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.concept.ConceptService;
import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.lessons.LessonService;
import com.urjc.daw.models.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value ="{id}")
    @JsonView(ConceptDetails.class)
    public ResponseEntity<Concept> getConcept(@PathVariable long id){
        Optional<Concept> concept= conceptService.findByOneId(id);
        return safeDelete(concept,conceptService.repository);
    }

    @GetMapping(value ="lesson/{idLesson}")
   // @JsonView(ConceptDetails.class)
    public Page<Concept> getConcepts(@PathVariable long idLesson, Pageable page){

        return  conceptService.findByLesson(lessonService.findOne(idLesson).get(),page);
    }

    @DeleteMapping(value ="{id}")
    @JsonView(ConceptDetails.class)
    public Concept deleteConcept(@PathVariable long id){
        Concept deleteConcept = conceptService.findByOneId(id).get();
        conceptService.deleteConceptById(id);
        return deleteConcept;
    }

    @PostMapping("/")
    @JsonView(ConceptDetails.class)
    public Concept saveConcept(@RequestBody Concept concept){
        conceptService.addConcept(concept);
        return  concept;
    }
}
