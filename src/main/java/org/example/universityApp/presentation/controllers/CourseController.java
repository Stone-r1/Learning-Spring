package org.example.universityApp.presentation.controllers;


import jakarta.validation.Valid;
import org.example.universityApp.application.course.CreateCourseRequest;
import org.example.universityApp.domain.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(
            CourseService courseService
    ) {
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(
            @Valid @RequestBody CreateCourseRequest createCourseRequest
    ) {
        courseService.addCourse(createCourseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course added successfully");
    }
}
