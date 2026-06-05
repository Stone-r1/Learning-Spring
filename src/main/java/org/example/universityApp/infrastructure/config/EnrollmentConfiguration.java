package org.example.universityApp.infrastructure.config;


import org.example.universityApp.domain.repositories.CourseRepository;
import org.example.universityApp.domain.repositories.EnrollmentRepository;
import org.example.universityApp.domain.repositories.StudentRepository;
import org.example.universityApp.domain.services.EnrollmentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EnrollmentConfiguration {

    @Bean
    public EnrollmentService enrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {
        return new EnrollmentService(
                enrollmentRepository,
                studentRepository,
                courseRepository
        );
    }
}
