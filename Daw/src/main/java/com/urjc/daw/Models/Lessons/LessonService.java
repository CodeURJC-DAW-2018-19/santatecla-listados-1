package com.urjc.daw.Models.Lessons;

import com.urjc.daw.Models.Concept.Concept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class LessonService {

    @Autowired
    private LessonRepository repository;

    public List<Lesson> findAllLessons(){
        return repository.findAll();
    }
}
