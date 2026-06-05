package org.example.universityApp.infrastructure.config;


import org.example.universityApp.domain.repositories.CourseRepository;
import org.example.universityApp.domain.services.CourseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CourseConfiguration {

    @Bean
    public CourseService courseService(
            CourseRepository courseRepository
    ) {
        return new CourseService(courseRepository);
    }
}
