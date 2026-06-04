package org.example.universityApp.domain.services;


import org.example.universityApp.application.course.Course;
import org.example.universityApp.application.course.CreateCourseRequest;
import org.example.universityApp.domain.exceptions.CourseAlreadyExistsException;
import org.example.universityApp.infrastructure.persistence.JpaCourseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CourseService {
    private final JpaCourseRepository courseRepository;

    public CourseService(
            JpaCourseRepository courseRepository
    ) {
        this.courseRepository = courseRepository;
    }

    public void addCourse(
            CreateCourseRequest createCourseRequest
    ) {
        Optional<Course> course = courseRepository.findCourseByCode(createCourseRequest.code());

        if (course.isPresent()) {
            throw new CourseAlreadyExistsException(
                    "Course with code " + createCourseRequest.code() + " already exists"
            );
        } else {
            Course newCourse = new Course();
            newCourse.setName(createCourseRequest.name());
            newCourse.setCode(createCourseRequest.code());
            newCourse.setCredits(createCourseRequest.credits());
            newCourse.setMaxStudents(createCourseRequest.maxStudents());
            courseRepository.save(newCourse);
        }
    }
}
