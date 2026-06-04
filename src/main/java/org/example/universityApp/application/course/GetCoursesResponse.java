package org.example.universityApp.application.course;

public record GetCoursesResponse(
        String name,
        String code,
        Integer credits,
        Integer maxStudents
) {}
