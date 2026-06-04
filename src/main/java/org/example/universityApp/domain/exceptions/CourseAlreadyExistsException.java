package org.example.universityApp.domain.exceptions;

public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException(
            String message
    ) {
        super(message);
    }
}
