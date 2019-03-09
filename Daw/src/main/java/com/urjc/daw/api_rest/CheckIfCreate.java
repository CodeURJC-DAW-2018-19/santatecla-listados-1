package com.urjc.daw.api_rest;


import com.urjc.daw.models.answer.Answer;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CheckIfCreate <T>{
    public ResponseEntity<T> checkIfExist(Optional<Answer> answer);
}
