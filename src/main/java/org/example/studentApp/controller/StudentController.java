package org.example.studentApp.controller;


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
            @RequestBody Student student
    ) {
        return studentService.createStudent(student);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(
            @PathVariable Long id
    ) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/student/{id}")
    public Student updateStudentById(
            @PathVariable Long id,
            @RequestBody Student student
    ) {
        return studentService.updateStudentById(id, student);
    }

    @DeleteMapping("/student/{id}")
    public Student deleteStudentById(
            @PathVariable Long id
    ) {
        return studentService.deleteStudentById(id);
    }
}
