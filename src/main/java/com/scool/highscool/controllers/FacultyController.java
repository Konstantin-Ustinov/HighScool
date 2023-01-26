package com.scool.highscool.controllers;

import com.scool.highscool.models.Faculty;
import com.scool.highscool.models.Student;
import com.scool.highscool.services.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping("/find")
    public ResponseEntity<List<Faculty>> findByNameOrColor(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String color) {

        List<Faculty> faculty;

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

//    @GetMapping("/get_all_students")
//    public ResponseEntity<Collection<Student>> getAllStudentsByFaculty(@RequestParam int id) {
//        Collection<Student> students = service.getAllStudentsByFaculty(id);
//
//        if (students != null) {
//            return ResponseEntity.ok(students);
//        }
//
//        return ResponseEntity.badRequest().build();
//    }

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
