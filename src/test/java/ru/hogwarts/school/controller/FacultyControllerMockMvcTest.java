package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
public class FacultyControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @MockBean
    private FacultyRepository facultyRepository;


    @Test
    public void testGetFacultyById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/faculty/6"))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreateFaculty() throws Exception {

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", 1L);
        facultyObject.put("name", "name");
        facultyObject.put("color", "red");

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("name");
        faculty.setColor("red");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.color").value("red"));
    }

    @Test
    public void testFindFacultyByStudentId() {

    }

    @Test
    public void testFindFacultyByColor() {

    }
}
