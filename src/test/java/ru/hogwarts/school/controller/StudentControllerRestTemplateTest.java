package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerRestTemplateTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetStudentById() {
        Assertions
                .assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/student/1", Student.class))
                .isNotNull();
    }

    @Test
    public void testCreateStudent() {
        Faculty faculty = new Faculty("name", "red");
        Student student = new Student();
        student.setId(1L);
        student.setName("Bob");
        student.setAge(34);
        student.setFaculty(faculty);

        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }


    @Test
    public void testFindStudentByFaculty() {
        Faculty faculty = new Faculty("name", "red");
        faculty.setId(1L);

        Student student = new Student();
        student.setId(1L);
        student.setName("Bob");
        student.setAge(34);
        student.setFaculty(faculty);

        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/byStudent/" + student.getId(), String.class))
                .isNotNull();
    }

    @Test
    public void testFindStudentsByAgeBetween() {
        Faculty faculty = new Faculty("name", "red");
        faculty.setId(1L);

        Student student = new Student();
        student.setId(2L);
        student.setName("Bob");
        student.setAge(34);
        student.setFaculty(faculty);

        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/byAgeBetween?max=35&min=16", String.class))
                .isNotNull()
                .isEqualTo(student);
    }

//    @Test
//    public void testDeleteStudent() {
//        Assertions
//                .assertThat(this.testRestTemplate.delete("http://localhost:" + port + "/student/1"));
//    }

}
