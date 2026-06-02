package org.example.studentApp.models.responses;


public record StudentResponse(
        Long id,
        String name,
        Long course
) {}
