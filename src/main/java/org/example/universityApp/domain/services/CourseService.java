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

    public void createCourse(
            Course createCourseRequest
    ) {
        Optional<Course> course = courseRepository.findCourseByCode(createCourseRequest.getCode());

        if (course.isPresent()) {
            throw new UniversityExceptions.CourseAlreadyExistsException(
                    "Course with code " + createCourseRequest.getCode() + " already exists"
            );
        }

        courseRepository.save(createCourseRequest);
    }

    public Course getCourseByCode(
            String courseCode
    ) {
        return courseRepository.findCourseByCode(courseCode)
                .orElseThrow(() ->
                        new UniversityExceptions.CourseNotFoundException(
                                "Course with code " + courseCode + " not found"
                        )
                );
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
