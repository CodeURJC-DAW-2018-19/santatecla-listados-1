package com.urjc.daw.Models.Answer;

import com.urjc.daw.Models.Question.Question;
import com.urjc.daw.Models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByidQuestion(long idQuestion);

    Optional<Question> findByIdQuestion(Long aLong);

    Object findAnswerByIdUser(User user);

    List<Answer> findByState (String state);

    List<Answer> findByCorrectAndIdUser (Boolean correct, User idUser);
}
