package com.urjc.daw.models.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    public Page<Question> findAll(Pageable page){
        return repository.findAll(page);
    }

    public Optional<Question> findOne(Long idItem) {
        return repository.findById(idItem);
    }

    public void addQuestion(Question question){
        repository.save(question);
    }
    public void deleteQuestionById(long id) {
        repository.deleteById(id);
    }
    public Iterable<Question> findAllById(){ return repository.findAll();}

    //public question findByConceptAndId (Optional<concept> concept, long id){ return repository.findByConceptAndId(concept.get(), id);}
}