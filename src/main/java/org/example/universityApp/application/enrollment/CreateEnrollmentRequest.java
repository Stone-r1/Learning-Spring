package org.example.universityApp.application.enrollment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.universityApp.application.course.Course;
import org.example.universityApp.application.student.Student;


public record CreateEnrollmentRequest(
        @NotNull @Valid
        Student student,

        @NotNull @Valid
        Course course
) {}
