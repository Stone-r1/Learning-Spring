package org.example.universityApp.presentation;


import jakarta.validation.Valid;
import org.example.universityApp.application.student.CreateStudentRequest;
import org.example.universityApp.domain.studentService.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public void addStudent(
            @Valid @RequestBody CreateStudentRequest createStudentRequest
    ) {
        studentService.addStudent(createStudentRequest);
    }
}
