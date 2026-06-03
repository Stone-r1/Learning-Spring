package org.example.universityApp.application.course;


import lombok.Data;


@Data
public class Course {
    private Long id;
    private String code; // SEC1 PROG1 etc
    private String name; // full name
    private Integer credits;
    private Integer maxStudents;
}
