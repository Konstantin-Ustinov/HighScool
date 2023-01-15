package com.scool.highscool.controllers;

import com.scool.highscool.models.Student;
import com.scool.highscool.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/age {age}")
    public ResponseEntity<Set<Student>> findAllByAge(@PathVariable int age) {
        Set<Student> students = service.findAllByAge(age);

        if (students != null) {
            return ResponseEntity.ok(students);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findById(@PathVariable long id) {
        Student student = service.findById(id);

        if (student != null) {
            return ResponseEntity.ok(student);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Student> add(Student faculty) {
        return ResponseEntity.ok(service.add(faculty));
    }

    @PutMapping
    public ResponseEntity<Student> editFaculty(Student faculty) {
        return ResponseEntity.ok(service.edit(faculty));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteFaculty(@PathVariable long id) {
        Student faculty = service.remove(id);

        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }

        return ResponseEntity.badRequest().build();
    }
}
