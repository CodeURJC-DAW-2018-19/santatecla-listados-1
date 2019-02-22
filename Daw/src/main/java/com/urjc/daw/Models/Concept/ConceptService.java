package com.urjc.daw.Models.Concept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Page<Concept> findAll(Pageable page){
        return repository.findAll(page);
    }

    public Optional<Concept> findByOneId(Long id) {
        return repository.findById(id);
    }
}
