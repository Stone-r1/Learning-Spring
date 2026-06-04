package org.example.universityApp.application.enrollment;


import jakarta.persistence.*;
import lombok.Data;
import org.example.universityApp.application.course.Course;
import org.example.universityApp.application.student.Student;


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
