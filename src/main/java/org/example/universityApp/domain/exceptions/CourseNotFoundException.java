package org.example.universityApp.domain.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(
            String message
    ) {
        super(message);
    }
}
