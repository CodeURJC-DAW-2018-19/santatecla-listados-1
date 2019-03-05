package com.urjc.daw.api_rest;


import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.concept.ConceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/concept")
public class ConceptRest {

    @Autowired
    ConceptService conceptService;

    @DeleteMapping(value ="{id}")
    public Concept deleteConcept(@PathVariable long id){
        Concept deleteConcept = conceptService.findByOneId(id).get();
        conceptService.deleteConceptById(id);
        return deleteConcept;
    }

    @PostMapping("/")
    public Concept saveConcept(@RequestBody Concept concept){

        conceptService.addConcept(concept);
        return  concept;
    }
}
