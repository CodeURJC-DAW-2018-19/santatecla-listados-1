package com.urjc.daw.api_rest;


import com.urjc.daw.models.answer.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class CheckIfCreate <T>{

    public ResponseEntity<T> checkIfExist(Optional<T> t){
        if (t.isPresent()) {
            return new ResponseEntity<>(t.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
