package org.example.universityApp.domain.models.entities;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Student student;

    @ManyToOne(optional = false)
    private Course course;
}
