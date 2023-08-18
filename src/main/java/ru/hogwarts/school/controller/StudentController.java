package ru.hogwarts.school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentServiceImpl;
import ru.hogwarts.school.model.Student;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentServiceImpl studentService;
    private final AvatarRepository avatarRepository;

    private final AvatarService avatarService;

    @Autowired
    public StudentController(StudentServiceImpl studentService, AvatarRepository avatarRepository, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
        this.avatarService = avatarService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("byAgeBetween")
    public ResponseEntity<Collection<Student>> findStudentsByAgeBetween(@RequestParam int max, @RequestParam int min) {
        return ResponseEntity.ok(studentService.findAllStudentsByAgeBetween(max, min));
    }

    @GetMapping("byStudent")
    public ResponseEntity<Faculty> findFacultyByStudent(@RequestParam Long id) {
        return ResponseEntity.ok(studentService.findFacultyByStudent(id));
    }



//     @GetMapping(value = "/{id}/avatar/preview")
//        public ResponseEntity<byte[]>downloadAvatar(@PathVariable Long id) {
//            Avatar avatar = avatarService.findAvatar(id);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
//            headers.setContentLength(avatar.getData().length);
//
//            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
//        }
//
//        @GetMapping(value = "/{id}/avatar")
//        public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
//            Avatar avatar = avatarService.findAvatar(id);
//
//            Path path = Path.of(avatar.getFilePath());
//
//            try (InputStream is = Files.newInputStream(path);
//                 OutputStream os = response.getOutputStream();) {
//                response.setStatus(200);
//                response.setContentType(avatar.getMediaType());
//                response.setContentLength((int) avatar.getFileSize());
//                is.transferTo(os);
//            }
//        }


//    @GetMapping("/getAll")
//    public ResponseEntity<Collection<Student>> getAll() {
//        return ResponseEntity.ok(studentService.getAll());
//    }

}
