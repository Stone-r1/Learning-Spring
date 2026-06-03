package org.example.universityApp.application.student;


public record GetStudentResponse(
        String governmentId,
        String firstName,
        String lastName,
        Short academicYear,
        String faculty,
        String mobileNumber,
        Double Gpa
) {}
