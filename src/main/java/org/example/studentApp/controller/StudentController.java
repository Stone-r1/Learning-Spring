package org.example.studentApp.controller;


import jakarta.validation.Valid;
import org.example.studentApp.models.Student;
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
    public Student createStudent(
            @Valid @RequestBody Student student
    ) {
        return studentService.createStudent(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(
            @PathVariable Long id
    ) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/students/{id}")
    public Student updateStudentById(
            @PathVariable Long id,
            @Valid @RequestBody Student student
    ) {
        return studentService.updateStudentById(id, student);
    }

    @DeleteMapping("/students/{id}")
    public Student deleteStudentById(
            @PathVariable Long id
    ) {
        return studentService.deleteStudentById(id);
    }
}
