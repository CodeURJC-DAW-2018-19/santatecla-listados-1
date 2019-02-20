package com.urjc.daw.Models.Answer;

import com.urjc.daw.Models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByidQuestion(long idQuestion);

    Object findAnswerByIdUser(User user);
}
