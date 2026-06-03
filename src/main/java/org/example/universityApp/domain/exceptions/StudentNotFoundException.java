package org.example.universityApp.domain.exceptions;


public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(
            String message
    ) {
        super(message);
    }
}
