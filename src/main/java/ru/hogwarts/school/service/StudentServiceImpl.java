package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> findStudent(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void removeStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> findAllStudentsByAgeBetween(int max, int min) {
        return studentRepository.findByAgeBetween(max, min);
    }

    @Override
    public Faculty findFacultyByStudent(Long id) {
        return findStudent(id).get().getFaculty();
    }

//    @Override
//    public Collection<Student> getAll() {
//        return studentRepository.findAll();
//    }
}
