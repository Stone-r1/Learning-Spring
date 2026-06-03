package org.example.universityApp.application.student;


public record CreateStudentRequest(
        String governmentId,
        String firstName,
        String lastName,
        String faculty,
        String mobileNumber,
        Short academicYear
) {}
