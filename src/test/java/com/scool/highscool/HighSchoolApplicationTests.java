package com.scool.highscool;

import com.scool.highscool.controllers.StudentsController;
import com.scool.highscool.models.Faculty;
import com.scool.highscool.models.Student;
import com.scool.highscool.services.FacultyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HighSchoolApplicationTests {

    @LocalServerPort 
    private int port;

    @Autowired
    private StudentsController studentsController;

    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private TestRestTemplate testRestTemplate;
    
        
    @Test
    void contextLoads() {
        Assertions.assertThat(studentsController).isNotNull();
    }

    @Test
    void testFindAllByAgeBetween() {
        Assertions.assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/students/age", String.class)).isNotNull();
    }

    @Test
    void testFindById() {
        Assertions.assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/students?id=1", String.class)).isNotNull();
    }

    @Test
    void testFindAllStudentsByFaculty() {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/students/findAllStudentsByFaculty?id=1", String.class)).isNotNull();
    }


}
