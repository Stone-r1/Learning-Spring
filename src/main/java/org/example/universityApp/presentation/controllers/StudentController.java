package org.example.universityApp.presentation.controllers;


import jakarta.validation.Valid;
import org.example.universityApp.application.student.CreateStudentRequest;
import org.example.universityApp.application.student.GetStudentResponse;
import org.example.universityApp.domain.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(
            StudentService studentService
    ) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(
            @Valid @RequestBody CreateStudentRequest createStudentRequest
    ) {
        studentService.addStudent(createStudentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
    }

    @GetMapping("/get/{government_id}")
    public GetStudentResponse getStudent(
            @PathVariable("government_id") String governmentId
    ) {
        return studentService.getStudent(governmentId);
    }
}
