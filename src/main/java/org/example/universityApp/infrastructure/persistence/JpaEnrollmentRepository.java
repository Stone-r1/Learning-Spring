package org.example.universityApp.infrastructure.persistence;

import org.example.universityApp.application.course.Course;
import org.example.universityApp.application.enrollment.Enrollment;
import org.example.universityApp.application.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaEnrollmentRepository extends JpaRepository<Enrollment, Long> {
    public Optional<Enrollment> findEnrollmentByCourseAndStudent(Course course, Student student);
}
