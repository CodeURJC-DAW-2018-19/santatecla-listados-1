package com.urjc.daw.Models.Lessons;

import com.urjc.daw.Models.Lessons.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    ArrayList<Lesson> findLessonByIdUser (Integer idUser);
}