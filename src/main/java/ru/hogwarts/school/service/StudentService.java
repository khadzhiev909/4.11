package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentService{
    Student createStudent(Student student);

    Optional<Student> getStudentById(long id);

    Student editStudent(Student student);

    void removeStudent(long id);

    Collection<Student> findAllStudentsByAgeBetween(int max, int min);

    Faculty findFacultyByStudent(Long id);

    Collection<Student> getAll();
}