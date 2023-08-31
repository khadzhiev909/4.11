package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import ru.hogwarts.school.model.Faculty;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)/*Поднимает все приложение и делает реальные запросы*/
public class FacultyControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void testGetFacultyById() {
        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/faculty/1", Faculty.class);

        assertThat(facultyResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testCreateFaculty() {
        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate.postForEntity("http://localhost:" + port + "/faculty", new Faculty("name", "Оранжевый"), Faculty.class);

        assertThat(facultyResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(facultyResponseEntity.getBody().getName()).isEqualTo("name");
    }
// ------------------------------------------------------------------------\


    @Test
    public void testGetFacultyByName() {
        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/faculty?name=name", Faculty.class);

        assertThat(facultyResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(facultyResponseEntity.getBody().getName()).isEqualTo("name");
    }

    @Test
    public void testFindAllByColor() {
        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/faculty?color=red", Faculty.class);

        assertThat(facultyResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(facultyResponseEntity.getBody().getColor()).isEqualTo("red");
    }
    @Test
    public void testGetStudentsByFaculty() {
        Assertions
                .assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/faculty/1",Faculty.class).getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions
               .assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/faculty/1",Faculty.class).getBody().getName()).isEqualTo("name");
    }

    @Test
    public void testDeleteFaculty() {
        testRestTemplate.delete("http://localhost:" + port + "/faculty/1");

        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/faculty/1", Faculty.class);

        Assertions
                .assertThat(facultyResponseEntity).isNull();
    }
}
