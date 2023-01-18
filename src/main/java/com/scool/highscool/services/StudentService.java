package com.scool.highscool.services;

import com.scool.highscool.models.Student;
import com.scool.highscool.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student add(Student newStudent) {
        return repository.save(newStudent);
    }

    public Student findById(long id) {
        return repository.findById(id).get();
    }

    public Student edit(Student student) {
        return repository.save(student);
    }

    public void remove(long id) {
        repository.deleteById(id);
    }

    public List<Student> findAllByAge(int age) {
        return repository.findAll().stream().filter(f -> f.getAge() == age).collect(Collectors.toList());
    }
}
