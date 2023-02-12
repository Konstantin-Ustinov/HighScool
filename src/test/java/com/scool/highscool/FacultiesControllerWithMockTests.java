package com.scool.highscool;

import com.scool.highscool.controllers.FacultiesController;
import com.scool.highscool.models.Faculty;
import com.scool.highscool.models.Student;
import com.scool.highscool.services.FacultyService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = FacultiesController.class)
public class FacultiesControllerWithMockTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyService service;

    @Test
    public void findByNameOrColorTest() throws Exception {
        Faculty facultyName = new Faculty("FacultyName", "Color 1");
        Faculty facultyColor = new Faculty("FacultyColor", "Color 2");

        when(service.findByNameOrColor(any(String.class), any(String.class))).thenReturn(List.of(facultyName, facultyColor));
        when(service.findByName(any(String.class))).thenReturn(List.of(facultyName));
        when(service.findByColor(any(String.class))).thenReturn(List.of(facultyColor));
        when(service.findAll()).thenReturn(List.of(facultyName, facultyColor));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculties/find?name=" + facultyName.getName() + "&color=" + facultyColor.getColor()))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculties/find?name=" + facultyName.getName()))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculties/find?&color=" + facultyColor.getColor()))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculties/find"))
                .andExpect(status().isOk());
    }

    @Test
    public void findFacultyByIdTest() throws Exception {
        Faculty faculty = new Faculty("FacultyName", "Color 1");
        faculty.setId(1L);

        when(service.find(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculties/find?name=" + faculty.getName()))
                .andExpect(status().isOk());
    }

    @Test
    public void addTest() throws Exception {
        String name = "FacultyName";
        String color = "Color 1";
        Long id = 1L;

        Faculty faculty = new Faculty(name, color);
        faculty.setId(id);

        JSONObject facultyJSON = new JSONObject();
        facultyJSON.put("name", name);
        facultyJSON.put("faculty", faculty);

        when(service.add(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculties")
                        .content(facultyJSON.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void editTest() throws Exception {
        String name = "FacultyName";
        String color = "Color 1";
        Long id = 1L;

        Faculty faculty = new Faculty(name, color);
        faculty.setId(id);

        when(service.edit(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculties")
                        .content(faculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void deleteTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculties/" + id))
                .andExpect(status().isOk());
    }
}
