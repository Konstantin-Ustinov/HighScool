package com.scool.highscool.services;

import com.scool.highscool.models.Student;
import com.scool.highscool.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StudentService {
    private StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student add(Student newStudent) {
        return repository.add(newStudent);
    }

    public Student findById(long id) {
        return repository.findById(id);
    }

    public Student edit(Student student) {
        return repository.edit(student);
    }

    public Student remove(long id) {
        return repository.remove(id);
    }

    public Set<Student> findAllByAge(int age) {
        return repository.findAllByAge(age);
    }
}
