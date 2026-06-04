package org.example.universityApp.presentation.response.exceptionHandlers;


import org.example.universityApp.domain.exceptions.EnrollmentAlreadyExistsException;
import org.example.universityApp.presentation.response.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@RestControllerAdvice
public class EnrollmentExceptionsHandler {

    @ExceptionHandler(EnrollmentAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleEnrollmentAlreadyExistsException(
            EnrollmentAlreadyExistsException e,
            WebRequest webRequest
    ) {
        return new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                new Date(),
                e.getMessage(),
                webRequest.getDescription(false)
        );
    }
}
