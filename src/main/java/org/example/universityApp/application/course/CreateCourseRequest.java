package org.example.universityApp.application.course;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public record CreateCourseRequest(
        @NotBlank
        String name,

        @NotBlank
        String code,

        @Min(4) @Max(6)
        Integer credits,

        @Min(0) @Max(60)
        Integer maxStudents
) {}
