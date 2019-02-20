package com.urjc.daw.Models.Concept;

import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Lessons.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConceptService{
    @Autowired
    private ConceptRepository repository;


    public void addConcept(Concept concept){
        repository.save(concept);
    }
    public void deleteConceptById(long id) {
        repository.deleteById(id);
    }
}
