package org.example.universityApp.application.enrollment;

import jakarta.validation.constraints.NotBlank;


public record CreateEnrollmentRequest(

        @NotBlank
        String governmentId,

        @NotBlank
        String courseCode
) {}
