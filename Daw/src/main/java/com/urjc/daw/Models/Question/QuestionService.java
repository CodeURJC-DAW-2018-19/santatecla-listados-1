package com.urjc.daw.Models.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
