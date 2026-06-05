package org.example.universityApp.infrastructure.adapters;

import org.example.universityApp.domain.models.entities.Course;
import org.example.universityApp.domain.models.entities.Enrollment;
import org.example.universityApp.domain.models.entities.Student;
import org.example.universityApp.domain.repositories.EnrollmentRepository;
import org.example.universityApp.infrastructure.persistence.JpaCourseRepository;
import org.example.universityApp.infrastructure.persistence.JpaEnrollmentRepository;
import org.example.universityApp.infrastructure.persistence.JpaStudentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class EnrollmentRepositoryAdapter implements EnrollmentRepository {
    private final JpaEnrollmentRepository enrollmentRepository;
    private final JpaCourseRepository courseRepository;
    private final JpaStudentRepository studentRepository;

    public EnrollmentRepositoryAdapter(
            JpaEnrollmentRepository enrollmentRepository,
            JpaCourseRepository courseRepository,
            JpaStudentRepository studentRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public Optional<Enrollment> findEnrollmentByCourseAndStudent(
            Course course,
            Student student
    ) {
        return enrollmentRepository.findEnrollmentByCourseAndStudent(course, student);
    }

    @Override
    public Enrollment save(
            Enrollment enrollment
    ) {
        return enrollmentRepository.save(enrollment);
    }
}
