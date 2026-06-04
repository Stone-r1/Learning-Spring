package org.example.universityApp.infrastructure.persistence;

import org.example.universityApp.application.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaCourseRepository extends JpaRepository<Course, Long> {
    public Optional<Course> findCourseByCode(String courseCode);
}
