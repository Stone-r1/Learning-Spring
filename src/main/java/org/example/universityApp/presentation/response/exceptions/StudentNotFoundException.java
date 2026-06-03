package org.example.universityApp.presentation.response.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(
            String message
    ) {
        super(message);
    }
}
