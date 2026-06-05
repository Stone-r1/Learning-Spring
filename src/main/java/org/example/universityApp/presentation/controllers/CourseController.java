package org.example.universityApp.presentation.controllers;


import jakarta.validation.Valid;
import org.example.universityApp.application.course.CourseUseCase;
import org.example.universityApp.application.course.CreateCourseRequest;
import org.example.universityApp.application.course.GetCoursesResponse;
import org.example.universityApp.domain.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final CourseUseCase courseUseCase;

    public CourseController(
            CourseService courseService,
            CourseUseCase courseUseCase
    ) {
        this.courseService = courseService;
        this.courseUseCase = courseUseCase;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(
            @Valid @RequestBody CreateCourseRequest createCourseRequest
    ) {

        courseService.addCourse(courseUseCase.createCourseFromResponse(createCourseRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body("Course added successfully");
    }

    @GetMapping("/get")
    public List<GetCoursesResponse> getCourse() {
        return courseUseCase.createCourseResponseList();
    }
}
