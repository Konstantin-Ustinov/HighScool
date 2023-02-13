package com.scool.highscool.controllers;

import com.scool.highscool.models.Student;
import com.scool.highscool.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentsController {
    private final StudentService service;

    public StudentsController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/age")
    public ResponseEntity<List<Student>> findAllByAgeBetween(@RequestParam int ageStart, @RequestParam int ageEnd) {
        List<Student> students = service.findAllByAgeBetween(ageStart, ageEnd);

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

    @GetMapping("findAllStudentsByFaculty/{id}")
    public ResponseEntity<Collection<Student>> findAllStudentsByFaculty(@PathVariable int id) {
        return ResponseEntity.ok(service.findAllStudentsByFaculty(id));
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(Student student) {
        return ResponseEntity.ok(service.add(student));
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(Student student) {
        return ResponseEntity.ok(service.edit(student));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        service.remove(id);

        return ResponseEntity.ok().build();

    }
}
