package org.example.universityApp.domain.services;


import org.example.universityApp.domain.models.entities.Course;
import org.example.universityApp.application.enrollment.CreateEnrollmentRequest;
import org.example.universityApp.domain.models.entities.Enrollment;
import org.example.universityApp.domain.models.entities.Student;
import org.example.universityApp.domain.exceptions.UniversityExceptions;
import org.example.universityApp.infrastructure.persistence.JpaCourseRepository;
import org.example.universityApp.infrastructure.persistence.JpaEnrollmentRepository;
import org.example.universityApp.infrastructure.persistence.JpaStudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EnrollmentService {
    private final JpaEnrollmentRepository enrollmentRepository;
    private final JpaStudentRepository studentRepository;
    private final JpaCourseRepository courseRepository;

    public EnrollmentService(
            JpaEnrollmentRepository jpaEnrollmentRepository,
            JpaStudentRepository studentRepository,
            JpaCourseRepository courseRepository
    ) {
        this.enrollmentRepository = jpaEnrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    private Student getStudent(
            String governmentId
    ) {
        return studentRepository.findStudentByGovernmentId(governmentId)
                .orElseThrow(() ->
                        new UniversityExceptions.StudentNotFoundException(
                                "Student for government " + governmentId + " not found"
                        )
                );
    }

    private Course getCourse(
            String courseCode
    ) {
        return courseRepository.findCourseByCode(courseCode)
                .orElseThrow(() ->
                        new UniversityExceptions.CourseNotFoundException(
                             "Course with code " + courseCode + " not found"
                        )
                );
    }

    public void createEnrollment(
            CreateEnrollmentRequest createEnrollmentRequest
    ) {
        Course course = getCourse(createEnrollmentRequest.courseCode());
        Student student = getStudent(createEnrollmentRequest.governmentId());

        Optional<Enrollment> enrollment =
                enrollmentRepository.findEnrollmentByCourseAndStudent(course, student);

        if (enrollment.isPresent()) {
            throw new UniversityExceptions.EnrollmentAlreadyExistsException(
                    "Enrollment form already exists"
            );
        } else {
            Enrollment newEnrollment = new Enrollment();
            newEnrollment.setCourse(course);
            newEnrollment.setStudent(student);
            enrollmentRepository.save(newEnrollment);
        }
    }
}
