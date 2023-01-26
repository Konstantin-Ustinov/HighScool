package com.scool.highscool.services;

import com.scool.highscool.models.Faculty;
import com.scool.highscool.models.Student;
import com.scool.highscool.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty add(Faculty newFaculty) {
        return repository.save(newFaculty);
    }

    public List<Faculty> findByNameOrColor(String name, String color) {
        return repository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public List<Faculty> findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    public List<Faculty> findByColor(String color) {
        return repository.findByColorIgnoreCase(color);
    }

    public List<Faculty> findAll() {
        return repository.findAll();
    }

//    public Collection<Student> getAllStudentsByFaculty(int id) {
//        return repository.findAllFromStudentWhereFacultyid(id);
//    }

    public Faculty find(long id) {
        return repository.findById(id).get();
    }

    public Faculty edit(Faculty faculty) {
        return repository.save(faculty);
    }

    public void remove(long id) {
        repository.deleteById(id);
    }
}
