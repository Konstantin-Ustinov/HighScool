package com.scool.highscool.repository;

import com.scool.highscool.models.Student;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    private final Map<Long, Student> students = new HashMap<>();
    private long sharedId = 0;

    public Student add(Student newStudent) {
        newStudent.setId(++sharedId);
        return students.put(newStudent.getId(), newStudent);
    }

    public Student findById(long id) {
        return students.get(id);
    }

    public Student edit(Student student) {
        return students.put(student.getId(), student);
    }

    public Student remove(long id) {
        return students.remove(id);
    }

    public Set<Student> findAllByAge(int age) {
        return students.values().stream().filter(f -> f.getAge() == age).collect(Collectors.toSet());
    }
}
