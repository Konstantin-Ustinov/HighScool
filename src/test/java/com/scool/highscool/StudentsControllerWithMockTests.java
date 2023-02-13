package com.scool.highscool;

import com.scool.highscool.controllers.StudentsController;
import com.scool.highscool.models.Faculty;
import com.scool.highscool.models.Student;
import com.scool.highscool.services.StudentService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentsController.class)
public class StudentsControllerWithMockTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @Test
    public void findAllByAgeBetweenTest() throws Exception {
        long id = 1L;
        String mark = "Mark";
        String mike = "Mike";
        int age = 13;
        Faculty faculty = new Faculty("aaa", "bbb");

        Student student1 = new Student(mark, age);
        student1.setFaculty(faculty);
        student1.setId(id);

        Student student2 = new Student(mike, ++age);
        student2.setFaculty(faculty);
        student2.setId(++id);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);

        when(service.findAllByAgeBetween(anyInt(), anyInt())).thenReturn(studentList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/age?ageStart=12&ageEnd=16"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllStudentsByFacultyTest() throws Exception {
        long id = 1L;
        String mark = "Mark";
        String mike = "Mike";
        int age = 13;
        Faculty faculty = new Faculty("aaa", "bbb");

        Student student1 = new Student(mark, age);
        student1.setFaculty(faculty);
        student1.setId(id);

        Student student2 = new Student(mike, ++age);
        student2.setFaculty(faculty);
        student2.setId(++id);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);

        when(service.findAllStudentsByFaculty(anyInt())).thenReturn(studentList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/findAllStudentsByFaculty/" + 0))
                .andExpect(status().isOk());
    }

    @Test
    public void addStudentTest() throws Exception {
        long id = 1L;
        String name = "Mark";
        int age = 13;
        Faculty faculty = new Faculty("aaa", "bbb");

        Student student = new Student(name, age);
        student.setFaculty(faculty);
        student.setId(id);

        JSONObject studentJSON = new JSONObject();
        studentJSON.put("name", name);
        studentJSON.put("age", age);
        studentJSON.put("faculty", faculty);

        when(service.add(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/students")
                        .content(studentJSON.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.faculty").value(faculty));
    }

    @Test
    public void findByIdTest() throws Exception {
        Long id = 1L;
        String name = "Mark";
        int age = 13;
        Faculty faculty = new Faculty("aaa", "bbb");

        Student student = new Student(name, age);
        student.setFaculty(faculty);
        student.setId(id);

        when(service.findById(any(Long.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.faculty").value(faculty));
    }

    @Test
    public void editStudentTest() throws Exception {
        Long id = 1L;
        String name = "Mark";
        int age = 13;
        Faculty faculty = new Faculty("aaa", "bbb");

        Student student = new Student(name, age);
        student.setFaculty(faculty);
        student.setId(id);

        when(service.edit(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/students")
                        .content(student.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.faculty").value(faculty));
    }

    @Test
    public void deleteStudentTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/students/" + id))
                .andExpect(status().isOk());
    }

}
