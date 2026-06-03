package org.example.universityApp.presentation.response.exceptionHandlers;


import org.example.universityApp.presentation.response.exceptions.StudentNotFoundException;
import org.example.universityApp.presentation.response.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@RestControllerAdvice
public class StudentExceptionsHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage studentNotFoundException(
            StudentNotFoundException studentNotFoundException,
            WebRequest webRequest
    ) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                studentNotFoundException.getMessage(),
                webRequest.getDescription(false)
        );
    }
}
