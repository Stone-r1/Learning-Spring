package org.example.universityApp.application.course;

import org.example.universityApp.domain.models.entities.Course;
import org.example.universityApp.domain.services.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseUseCase {
    private final CourseService courseService;

    public CourseUseCase(
            CourseService courseService
    ) {
        this.courseService = courseService;
    }

    public Course createCourseFromResponse(
            CreateCourseRequest request
    ) {
        Course course = new Course();
        course.setName(request.name());
        course.setCode(request.code());
        course.setCredits(request.credits());
        course.setMaxStudents(request.maxStudents());
        return courseService.addCourse(course);
    }

    public List<GetCoursesResponse> createCourseResponseList(
    ) {
        return courseService.getAllCourses().stream()
                .map(course -> new GetCoursesResponse(
                        course.getName(),
                        course.getCode(),
                        course.getCredits(),
                        course.getMaxStudents()
                )).toList();
    }
}
