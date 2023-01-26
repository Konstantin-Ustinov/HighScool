package com.scool.highscool.repository;

import com.scool.highscool.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(int ageStart, int ageEnd);

    Collection<Student> findAllByFaculty_id(long id);
}
