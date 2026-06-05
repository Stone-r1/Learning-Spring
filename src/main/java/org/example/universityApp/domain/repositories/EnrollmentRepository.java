package org.example.universityApp.domain.repositories;


import org.example.universityApp.domain.models.entities.Course;
import org.example.universityApp.domain.models.entities.Enrollment;
import org.example.universityApp.domain.models.entities.Student;

import java.util.Optional;


public interface EnrollmentRepository {
    public Optional<Enrollment> findEnrollmentByCourseAndStudent(Course course, Student student);
    Enrollment save(Enrollment enrollment);
}
