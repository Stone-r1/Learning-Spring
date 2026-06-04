package org.example.universityApp.domain.exceptions;

public class EnrollmentAlreadyExistsException extends RuntimeException {
    public EnrollmentAlreadyExistsException(
            String message
    ) {
        super(message);
    }
}
