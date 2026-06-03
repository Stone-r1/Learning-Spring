package org.example.universityApp.application.enrollment;


import lombok.Data;
import org.example.universityApp.application.course.Course;
import org.example.universityApp.application.student.Student;


@Data
public class Enrollment {
    private Long id;
    private Student student;
    private Course course;
}
