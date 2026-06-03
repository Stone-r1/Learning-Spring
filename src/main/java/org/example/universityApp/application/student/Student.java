package org.example.universityApp.application.student;


import lombok.Data;


@Data
public class Student {
    private Long id;
    private Long governmentId;
    private String firstName;
    private String lastName;
    private Short academicYear; // if nothing in base - automatically assign 1 elsewise base + 1
    private String faculty;
    private String mobileNumber;
    private Double Gpa; // fetch from the base or 0.0
}
