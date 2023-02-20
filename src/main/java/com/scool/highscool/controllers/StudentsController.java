package com.scool.highscool.controllers;

import com.scool.highscool.models.Student;
import com.scool.highscool.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentsController {
    private Logger logger = LoggerFactory.getLogger(StudentsController.class);

    private final StudentService service;

    public StudentsController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/age")
    public ResponseEntity<List<Student>> findAllByAgeBetween(@RequestParam int ageStart, @RequestParam int ageEnd) {
        logger.info("Was invoked method for findAllByAgeBetween() student");
        List<Student> students = service.findAllByAgeBetween(ageStart, ageEnd);

        if (students != null) {
            return ResponseEntity.ok(students);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findById(@PathVariable long id) {
        logger.info("Was invoked method for findById() student");
        Student student = service.findById(id);

        if (student != null) {
            return ResponseEntity.ok(student);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("findAllStudentsByFaculty/{id}")
    public ResponseEntity<Collection<Student>> findAllStudentsByFaculty(@PathVariable int id) {
        logger.info("Was invoked method for findAllStudentsByFaculty() student");
        return ResponseEntity.ok(service.findAllStudentsByFaculty(id));
    }

    @GetMapping("/count_in_school")
    public ResponseEntity<Integer> getCountAllStudents() {
        logger.info("Was invoked method for getCountAllStudents() student");
        return ResponseEntity.ok(service.getCountAllStudents());
    }

    @GetMapping("/avg_age")
    public ResponseEntity<Integer> getAvgAgeStudents() {
        logger.info("Was invoked method for getAvgAgeStudents() student");
        return ResponseEntity.ok(service.getAvgAgeStudents());
    }

    @GetMapping("/avg_age_by_stream")
    public ResponseEntity<Double> avgAgeByStream() {
        logger.info("Called method avgAgeByStream() student");
        return ResponseEntity.ok(service.avgAgeByStream());
    }

    @GetMapping("/get_last_five_students")
    public ResponseEntity<Collection<Student>> getLastFiveStudents() {
        logger.info("Was invoked method for getLastFiveStudents() student");
        return ResponseEntity.ok(service.getLastFiveStudents());
    }

    @GetMapping("/find_all_start_lit")
    public ResponseEntity<List<Student>> findAllStartWith(@RequestParam String lit) {
        logger.info("Called method findAllStartWith() student");
        return ResponseEntity.ok(service.findAllStartWith(lit));
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(Student student) {
        logger.info("Was invoked method for addStudent() student");
        return ResponseEntity.ok(service.add(student));
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(Student student) {
        logger.info("Was invoked method for editStudent() student");
        return ResponseEntity.ok(service.edit(student));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        logger.info("Was invoked method for deleteStudent() student");
        service.remove(id);

        return ResponseEntity.ok().build();

    }
}
