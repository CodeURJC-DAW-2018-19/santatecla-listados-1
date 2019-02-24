package com.urjc.daw.Models.Question;

import com.urjc.daw.Models.Answer.Answer;
import com.urjc.daw.Models.Concept.Concept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByidConcept(Concept idConcept);
    List<Question> findAll();
    Question findByidQuestion(long idQuestion);
    //Question findByConceptAndId(Concept concept, long id);
}
