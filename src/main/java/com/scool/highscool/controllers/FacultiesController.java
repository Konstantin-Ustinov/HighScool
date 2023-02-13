package com.scool.highscool.controllers;

import com.scool.highscool.models.Faculty;
import com.scool.highscool.services.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("faculties")
public class FacultiesController {
    private final FacultyService service;

    public FacultiesController(FacultyService service) {
        this.service = service;
    }

    @GetMapping("/find")
    public ResponseEntity<List<Faculty>> findByNameOrColor(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String color) {

        if ((name != null && !name.isBlank()) && (color != null && !color.isBlank())) {
            return ResponseEntity.ok(service.findByNameOrColor(name, color));
        }

        if ((name != null && !name.isBlank())) {
            return ResponseEntity.ok(service.findByName(name));
        }

        if ((color != null && !color.isBlank())) {
            return ResponseEntity.ok(service.findByColor(color));
        }

        return ResponseEntity.ok(service.findAll());
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
    public ResponseEntity deleteFaculty(@PathVariable long id) {
        service.remove(id);

        return ResponseEntity.ok().build();

    }
}
