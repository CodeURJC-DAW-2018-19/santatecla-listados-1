package com.urjc.daw.Models.Lessons;

import com.urjc.daw.Models.Concept.Concept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class LessonService {

    @Autowired
    private LessonRepository repository;

    public void deleteLessonById(Long id){repository.deleteById(id);}

    public Page<Lesson> findAll(Pageable page){
        return repository.findAll(page);
    }

    public Optional<Lesson> findOne(Long id) {
        return repository.findById(id);
    }

    public void addLesson(Lesson lesson){
        repository.save(lesson);
    }

    public List<Lesson> searchLessons(String name){
        return repository.searchByName(name);
    }
}
