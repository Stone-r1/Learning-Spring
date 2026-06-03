package org.example.universityApp.domain.exceptions;


public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException(
            String message
    ) {
        super(message);
    }
}
