package com.urjc.daw.Models.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;

    public List<Question> findAll(){
        return repository.findAll();
    }

    public Optional<Question> findOne(Long idItem) {
        return repository.findById(idItem);
    }
}
