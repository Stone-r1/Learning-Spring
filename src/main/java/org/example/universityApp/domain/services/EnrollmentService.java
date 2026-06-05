package org.example.universityApp.domain.services;


import org.example.universityApp.domain.models.entities.Course;
import org.example.universityApp.application.enrollment.CreateEnrollmentRequest;
import org.example.universityApp.domain.models.entities.Enrollment;
import org.example.universityApp.domain.models.entities.Student;
import org.example.universityApp.domain.exceptions.UniversityExceptions;
import org.example.universityApp.domain.repositories.CourseRepository;
import org.example.universityApp.domain.repositories.EnrollmentRepository;
import org.example.universityApp.domain.repositories.StudentRepository;

import java.util.Optional;


public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
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
