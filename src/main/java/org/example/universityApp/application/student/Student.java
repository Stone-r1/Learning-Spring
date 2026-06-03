package org.example.universityApp.application.student;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String governmentId;

    private String firstName;

    private String lastName;

    private Short academicYear;

    private String faculty;

    private String mobileNumber;

    private Double Gpa;
}
