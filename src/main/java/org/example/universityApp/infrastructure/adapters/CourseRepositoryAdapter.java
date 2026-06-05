package org.example.universityApp.infrastructure.adapters;

import org.example.universityApp.domain.models.entities.Course;
import org.example.universityApp.domain.repositories.CourseRepository;
import org.example.universityApp.infrastructure.persistence.JpaCourseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class CourseRepositoryAdapter implements CourseRepository {
    private final JpaCourseRepository courseRepository;

    public CourseRepositoryAdapter(
            JpaCourseRepository courseRepository
    ) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> findCourseByCode(
            String courseCode
    ) {
        return courseRepository.findCourseByCode(courseCode);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course save(
            Course course
    ) {
        return courseRepository.save(course);
    }
}