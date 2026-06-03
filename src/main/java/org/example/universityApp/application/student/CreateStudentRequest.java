package org.example.universityApp.application.student;


public record CreateStudentRequest(
        Long governmentId,
        String firstName,
        String lastName,
        String faculty,
        String mobileNumber
) {}
