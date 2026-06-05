package org.example.universityApp.domain.services;


import org.example.universityApp.domain.models.entities.Course;
import org.example.universityApp.domain.exceptions.UniversityExceptions;
import org.example.universityApp.domain.repositories.CourseRepository;

import java.util.List;
import java.util.Optional;


public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(
            CourseRepository courseRepository
    ) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(
            Course course
    ) {
        Optional<Course> existingCourse = courseRepository.findCourseByCode(course.getCode());
        if (existingCourse.isPresent()) {
            throw new UniversityExceptions.CourseAlreadyExistsException(
                    "Course with code " + course.getCode() + " already exists"
            );
        }

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
