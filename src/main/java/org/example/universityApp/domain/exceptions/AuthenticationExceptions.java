package org.example.universityApp.domain.exceptions;

import org.springframework.http.HttpStatus;


public class AuthenticationExceptions {

    public static class InvalidCredentialsException extends DomainException {
        public InvalidCredentialsException(
                String message
        ) {
            super(message, HttpStatus.UNAUTHORIZED);
        }
    }

    public static class UserNotFoundException extends DomainException {
        public UserNotFoundException(
                String message
        ) {
            super(message, HttpStatus.NOT_FOUND);
        }
    }

    public static class UserAlreadyExistsException extends DomainException {
        public UserAlreadyExistsException(
                String message
        ) {
            super(message, HttpStatus.CONFLICT);
        }
    }
}
