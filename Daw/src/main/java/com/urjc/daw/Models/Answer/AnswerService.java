package com.urjc.daw.Models.Answer;

import com.urjc.daw.Models.Question.Question;
import com.urjc.daw.Models.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository repository;

    public void addAnswer(Answer answer){repository.save(answer);}

    public Page<Answer> findAll(Pageable page){
        return repository.findAll(page);
    }

    public Optional<Answer> findOne(Long idItem) {
        return repository.findById(idItem);
    }

    public Optional<Question> findQuestion(Long idQuestion){ return repository.findByIdQuestion(idQuestion);}

    public List<Answer> findByCorrectAndIdUser(Boolean correct, User idUser){
        return repository.findByCorrectAndIdUser(correct,idUser);
    }

}
