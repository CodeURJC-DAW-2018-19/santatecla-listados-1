package com.urjc.daw.Repository;

import com.urjc.daw.Models.Concept.Concept;
import org.springframework.data.repository.CrudRepository;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ConceptRepository extends CrudRepository<Concept, Integer> {

}