package org.example.universityApp.domain.repositories;


import org.example.universityApp.domain.models.entities.Course;

import java.util.List;
import java.util.Optional;


public interface CourseRepository {
    public Optional<Course> findCourseByCode(String courseCode);
    public List<Course> findAll();
    Course save(Course course);
}
