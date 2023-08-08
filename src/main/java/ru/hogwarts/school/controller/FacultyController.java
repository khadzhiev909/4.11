package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyServiceImpl;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyServiceImpl facultyService;

    @Autowired
    public FacultyController(FacultyServiceImpl facultyService) {
        this.facultyService = facultyService;
    }


    @GetMapping("{id}")
    public ResponseEntity<Optional<Faculty>> getStudentInfo(@PathVariable Long id) {
        Optional<Faculty> faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createStudent(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }


    @PutMapping
    public ResponseEntity<Faculty> editStudent(@RequestBody Faculty faculty) {
        Faculty faculty1 = facultyService.editFaculty(faculty);
        if (faculty1 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty1);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Faculty> findFacultyByName(@RequestParam String name) {
        return ResponseEntity.ok(facultyService.findFacultyByName(name));
    }

    @GetMapping(params ={"color"})
    public ResponseEntity<Collection<Faculty>> findAllByColor(@RequestParam String color) {
        return ResponseEntity.ok(facultyService.findFacultyByColor(color));
    }

    @GetMapping("byStudent")
    public ResponseEntity<Student> findFacultiesByStudent(@RequestParam Long id) {
        return ResponseEntity.ok(facultyService.findFacultiesByStudent(id));
    }

}
