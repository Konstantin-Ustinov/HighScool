package com.scool.highscool.controllers;

import com.scool.highscool.models.Faculty;
import com.scool.highscool.services.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("faculties")
public class FacultiesController {
    private Logger logger = LoggerFactory.getLogger(FacultiesController.class);

    private final FacultyService service;

    public FacultiesController(FacultyService service) {
        this.service = service;
    }

    @GetMapping("/find")
    public ResponseEntity<List<Faculty>> findByNameOrColor(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String color) {

        logger.info("Was invoked method for findByNameOrColor() faculty");
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
        logger.info("Was invoked method for findFacultyById()");
        Faculty faculty = service.find(id);

        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/find_max_length_name")
    public ResponseEntity<String> findMaxLengthName() {
        logger.info("Called method findMaxLengthName() faculty");
        return ResponseEntity.ok(service.findMaxLengthName());
    }

    @PostMapping
    public ResponseEntity<Faculty> add(Faculty faculty) {
        logger.info("Was invoked method for add() faculty");
        return ResponseEntity.ok(service.add(faculty));
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(Faculty faculty) {
        logger.info("Was invoked method for editFaculty()");
        return ResponseEntity.ok(service.edit(faculty));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable long id) {
        logger.info("Was invoked method for deleteFaculty()");
        service.remove(id);

        return ResponseEntity.ok().build();

    }
}
