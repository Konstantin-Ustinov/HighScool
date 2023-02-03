package com.scool.highscool.services;

import com.scool.highscool.models.Student;
import com.scool.highscool.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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

    public Collection<Student> findAllStudentsByFaculty(int id) {
        return repository.findAllByFaculty_id((long) id);
    }

    public Student edit(Student student) {
        return repository.save(student);
    }

    public void remove(long id) {
        repository.deleteById(id);
    }

    public List<Student> findAllByAgeBetween(int ageStart, int ageEnd) {
        return repository.findByAgeBetween(ageStart, ageEnd);
    }
}
