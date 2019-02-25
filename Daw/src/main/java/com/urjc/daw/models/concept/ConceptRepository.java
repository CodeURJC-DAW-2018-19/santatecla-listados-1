package com.urjc.daw.models.concept;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ConceptRepository extends JpaRepository<Concept, Long> {
        Optional<Concept> findByIdConcept(long IdConcept);
}