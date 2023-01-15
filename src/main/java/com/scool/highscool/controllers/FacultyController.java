package com.scool.highscool.controllers;

import com.scool.highscool.models.Faculty;
import com.scool.highscool.services.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping("/color {color}")
    public ResponseEntity<Set<Faculty>> findAllByColor(@PathVariable String color) {
        Set<Faculty> faculty = service.findAllByColor(color);

        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> findFacultyById(@PathVariable long id) {
        Faculty faculty = service.find(id);

        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Faculty> add(Faculty faculty) {
        return ResponseEntity.ok(service.add(faculty));
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(Faculty faculty) {
        return ResponseEntity.ok(service.edit(faculty));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        Faculty faculty = service.remove(id);

        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }

        return ResponseEntity.badRequest().build();
    }
}
