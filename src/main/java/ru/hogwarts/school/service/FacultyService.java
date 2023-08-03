package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.Optional;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);

    Optional<Faculty> findFaculty(long id);

    Faculty editFaculty(Faculty faculty);

    void removeFaculty(long id);
}
