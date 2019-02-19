package com.urjc.daw.Models.Concept;

import com.urjc.daw.Models.Concept.Concept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ConceptRepository extends JpaRepository<Concept, Long> {
        Optional<Concept> findByIdConcept(long IdConcept);
}