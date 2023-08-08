package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);

    Optional<Faculty> findFaculty(long id);

    Faculty editFaculty(Faculty faculty);

    void removeFaculty(long id);

    Faculty findFacultyByName(String name);

    Collection<Faculty> getAll();

    Collection<Faculty> findFacultyByColor(String color);

    Student findFacultiesByStudent(Long id);
}
