package com.urjc.daw.Models.Concept;

import com.urjc.daw.Models.Concept.Concept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ConceptRepository extends JpaRepository<Concept, Integer> {

    Concept findUserById(Long id);

    Concept findAllByTittle(String tittle);
}