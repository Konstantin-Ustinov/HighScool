package com.scool.highscool.repository;

import com.scool.highscool.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(int ageStart, int ageEnd);

    Collection<Student> findAllByFaculty_id(long id);

    @Query(value = "SELECT count(name) FROM student", nativeQuery = true)
    Integer countStudents();

    @Query(value = "SELECT avg(age) FROM student", nativeQuery = true)
    Integer avgAgeStudents();

    @Query(value = "SELECT * FROM (SELECT * FROM student ORDER BY id DESC LIMIT 5) as st ORDER BY id ASC", nativeQuery = true)
    Collection<Student> getLastFiveStudents();


}
