package org.example.universityApp.domain.models.entities;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    private Integer credits;

    private Integer maxStudents;
}
