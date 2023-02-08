package com.scool.highscool;

import com.scool.highscool.controllers.AvatarsController;
import com.scool.highscool.controllers.StudentsController;
import com.scool.highscool.models.Faculty;
import com.scool.highscool.models.Student;
import com.scool.highscool.repository.AvatarRepository;
import com.scool.highscool.repository.StudentRepository;
import com.scool.highscool.services.AvatarService;
import com.scool.highscool.services.StudentService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentsController.class)
public class HighSchoolApplicationWithMockTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

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
