package org.example.universityApp.domain.models.entities;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String governmentId;

    private String firstName;

    private String lastName;

    private Short academicYear;

    private String faculty;

    private String mobileNumber;

    private Double Gpa;
}
