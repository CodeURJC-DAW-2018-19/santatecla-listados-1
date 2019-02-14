package com.urjc.daw.Models.Lessons;

import org.springframework.data.jpa.repository.JpaRepository;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LessonRepository extends JpaRepository<Lesson, Long> {

}