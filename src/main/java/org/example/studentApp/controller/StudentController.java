package org.example.studentApp.controller;


import jakarta.validation.Valid;
import org.example.studentApp.models.requests.CreateStudentRequest;
import org.example.studentApp.models.responses.StudentResponse;
import org.example.studentApp.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(
            StudentService studentService
    ) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponse createStudent(
            @Valid @RequestBody CreateStudentRequest studentRequest
    ) {
        return studentService.createStudent(studentRequest);
    }

    @GetMapping("/students")
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public StudentResponse getStudentById(
            @PathVariable Long id
    ) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/students/course/{course}")
    public List<StudentResponse> getStudentsByCourse(
            @PathVariable Long course
    ) {
        return studentService.getStudentsByCourse(course);
    }

    @PutMapping("/students/{id}")
    public StudentResponse updateStudentById(
            @PathVariable Long id,
            @Valid @RequestBody CreateStudentRequest studentRequest
    ) {
        return studentService.updateStudentById(id, studentRequest);
    }

    @DeleteMapping("/students/{id}")
    public StudentResponse deleteStudentById(
            @PathVariable Long id
    ) {
        return studentService.deleteStudentById(id);
    }
}
