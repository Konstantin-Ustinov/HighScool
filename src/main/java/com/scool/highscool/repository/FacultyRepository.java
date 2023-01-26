package com.scool.highscool.repository;

import com.scool.highscool.models.Faculty;
import com.scool.highscool.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
    List<Faculty> findByNameIgnoreCase(String name);
    List<Faculty> findByColorIgnoreCase(String color);

    //Collection<Student> get(int id);
}
