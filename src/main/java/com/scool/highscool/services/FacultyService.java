package com.scool.highscool.services;

import com.scool.highscool.models.Faculty;
import com.scool.highscool.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty add(Faculty newFaculty) {
        return repository.save(newFaculty);
    }

    public List<Faculty> findAllByColor(String color) {
        return repository.findAll().stream().filter(f -> f.getColor().equals(color)).collect(Collectors.toList());
    }

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
