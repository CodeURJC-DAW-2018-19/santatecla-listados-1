package com.urjc.daw.Models.Lessons;

import com.urjc.daw.Models.Lessons.Lesson;
import org.springframework.data.repository.CrudRepository;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

}