package org.example.universityApp.presentation.controllers;


import jakarta.validation.Valid;
import org.example.universityApp.application.enrollment.CreateEnrollmentRequest;
import org.example.universityApp.application.enrollment.EnrollmentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentUseCase enrollmentUseCase;

    public EnrollmentController(
            EnrollmentUseCase enrollmentUseCase
    ) {
        this.enrollmentUseCase = enrollmentUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEnrollment(
            @Valid @RequestBody CreateEnrollmentRequest createEnrollmentRequest
    ) {
        enrollmentUseCase.createEnrollment(createEnrollmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Enrollment created successfully");
    }
}
