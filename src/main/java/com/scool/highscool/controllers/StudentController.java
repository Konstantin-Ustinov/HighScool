package com.scool.highscool.controllers;

import com.scool.highscool.models.Student;
import com.scool.highscool.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/age {age}")
    public ResponseEntity<List<Student>> findAllByAge(@PathVariable int age) {
        List<Student> students = service.findAllByAge(age);

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
    public ResponseEntity<Student> add(Student student) {
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
