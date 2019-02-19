package com.urjc.daw.Models.Lessons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query(value = "SELECT * FROM lesson WHERE UPPER(name) LIKE CONCAT('%', CONCAT(UPPER(:name), '%'))", nativeQuery = true)
    List<Lesson> searchByName(@Param("name") String name);
}