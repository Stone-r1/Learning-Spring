package org.example.universityApp.application.student;


public record GetStudentResponse(
        Long id,
        String firstName,
        String lastName,
        Short academicYear,
        String faculty,
        String mobileNumber,
        Double Gpa
) {}
