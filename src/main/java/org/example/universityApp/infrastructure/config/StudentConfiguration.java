package org.example.universityApp.infrastructure.config;


import org.example.universityApp.domain.repositories.StudentRepository;
import org.example.universityApp.domain.services.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StudentConfiguration {

    @Bean
    public StudentService studentService(
            StudentRepository studentRepository
    ) {
        return new StudentService(studentRepository);
    }
}
