package com.scool.highscool.services;

import com.scool.highscool.models.Faculty;
import com.scool.highscool.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FacultyService {
    private FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty add(Faculty newFaculty) {
        return repository.add(newFaculty);
    }

    public Set<Faculty> findAllByColor(String color) {
        return repository.findAllByColor(color);
    }

    public Faculty find(long id) {
        return repository.find(id);
    }

    public Faculty edit(Faculty faculty) {
        return repository.edit(faculty);
    }

    public Faculty remove(long id) {
        return repository.remove(id);
    }
}
