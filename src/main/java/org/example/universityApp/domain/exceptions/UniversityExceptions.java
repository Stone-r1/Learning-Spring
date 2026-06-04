package org.example.universityApp.domain.exceptions;

import org.springframework.http.HttpStatus;

public class UniversityExceptions {
    public static class StudentNotFoundException extends DomainException {
        public StudentNotFoundException(
                String message
        ) {
            super(message, HttpStatus.NOT_FOUND);
        }
    }

    public static class StudentAlreadyExistsException extends DomainException {
        public StudentAlreadyExistsException(
                String message
        ) {
            super(message, HttpStatus.CONFLICT);
        }
    }

    public static class CourseNotFoundException extends DomainException {
        public CourseNotFoundException(
                String message
        ) {
            super(message, HttpStatus.NOT_FOUND);
        }
    }

    public static class CourseAlreadyExistsException extends DomainException {
        public CourseAlreadyExistsException(
                String message
        ) {
            super(message, HttpStatus.CONFLICT);
        }
    }

    public static class EnrollmentAlreadyExistsException extends DomainException {
        public EnrollmentAlreadyExistsException(
                String message
        ) {
            super(message, HttpStatus.CONFLICT);
        }
    }
}
