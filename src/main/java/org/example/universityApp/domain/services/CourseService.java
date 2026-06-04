package org.example.universityApp.domain.services;


import org.example.universityApp.application.course.Course;
import org.example.universityApp.application.course.CreateCourseRequest;
import org.example.universityApp.application.course.GetCoursesResponse;
import org.example.universityApp.domain.exceptions.CourseAlreadyExistsException;
import org.example.universityApp.infrastructure.persistence.JpaCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseService {
    private final JpaCourseRepository courseRepository;

    public CourseService(
            JpaCourseRepository courseRepository
    ) {
        this.courseRepository = courseRepository;
    }

    private GetCoursesResponse toResponse(Course course) {
        return new GetCoursesResponse(
                course.getName(),
                course.getCode(),
                course.getCredits(),
                course.getMaxStudents()
        );
    }

    private List<GetCoursesResponse> toResponse(
            List<Course> createCourseRequest
    ) {
        return createCourseRequest.stream()
                .map(this::toResponse)
                .toList();
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

    public List<GetCoursesResponse> getAllCourses() {
        return toResponse(courseRepository.findAll());
    }
}
