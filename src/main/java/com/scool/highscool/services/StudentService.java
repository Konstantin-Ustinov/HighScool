package com.scool.highscool.services;

import com.scool.highscool.models.Student;
import com.scool.highscool.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student add(Student newStudent) {
        return repository.save(newStudent);
    }

    public Student findById(long id) {
        return repository.findById(id).get();
    }

    public Collection<Student> findAllStudentsByFaculty(int id) {
        return repository.findAllByFaculty_id((long) id);
    }

    public Student edit(Student student) {
        return repository.save(student);
    }

    public void remove(long id) {
        repository.deleteById(id);
    }

    public List<Student> findAllByAgeBetween(int ageStart, int ageEnd) {
        return repository.findByAgeBetween(ageStart, ageEnd);
    }

    public Integer getCountAllStudents() {
        return repository.countStudents();
    }

    public Integer getAvgAgeStudents() {
        return repository.avgAgeStudents();
    }

    public Collection<Student> getLastFiveStudents() {
        return repository.getLastFiveStudents();
    }

    public List<Student> findAllStartWith(String lit) {
        List<Student> allStudents = repository.findAll();
        return allStudents.stream().parallel().filter(s -> s.getName().toUpperCase().startsWith(lit)).collect(Collectors.toList());
    }

    public Double avgAgeByStream() {
        return repository.findAll().stream().mapToInt(Student::getAge).average().getAsDouble();
    }

    public void startThread1() {
        List<Student> students = repository.findAll();

        Runnable task1 = () -> {
            students.forEach(s -> {
                System.out.println( s.getName());
            });
        };

        new Thread(task1).start();
        new Thread(task1).start();
    }

    public void startThread2() {
        List<Student> students = repository.findAll();

        Runnable task1 = () -> {
            synchronized (students) {
                students.forEach(s -> {
                    System.out.println(s.getName());
                });
            }
        };

        new Thread(task1).start();
        new Thread(task1).start();
    }
}
