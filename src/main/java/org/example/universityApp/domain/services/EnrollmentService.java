package org.example.universityApp.domain.services;


import org.example.universityApp.application.enrollment.CreateEnrollmentRequest;
import org.example.universityApp.application.enrollment.Enrollment;
import org.example.universityApp.domain.exceptions.EnrollmentAlreadyExistsException;
import org.example.universityApp.infrastructure.persistence.JpaEnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EnrollmentService {
    private final JpaEnrollmentRepository enrollmentRepository;

    public EnrollmentService(
            JpaEnrollmentRepository jpaEnrollmentRepository
    ) {
        this.enrollmentRepository = jpaEnrollmentRepository;
    }

    public void createEnrollment(
            CreateEnrollmentRequest createEnrollmentRequest
    ) {
        Optional<Enrollment> enrollment =
                enrollmentRepository.findEnrollmentByCourseAndStudent(createEnrollmentRequest.course(), createEnrollmentRequest.student());

        if (enrollment.isPresent()) {
            throw new EnrollmentAlreadyExistsException(
                    "Enrollment form already exists"
            );
        } else {
            Enrollment newEnrollment = new Enrollment();
            newEnrollment.setCourse(createEnrollmentRequest.course());
            newEnrollment.setStudent(createEnrollmentRequest.student());
            enrollmentRepository.save(newEnrollment);
        }
    }
}
