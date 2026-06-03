package org.example.universityApp.application.professor;


import lombok.Data;


@Data
public class Professor {
    private Long id;
    private String firstName;
    private String lastName;
    private String faculty;
    private String mobileNumber;
    private String department;
}
