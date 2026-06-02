package org.example.studentApp.models.requests;


public record CreateStudentRequest(
        String name,
        Long course
) {}
