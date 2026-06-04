package org.example.universityApp.presentation.response.exceptionHandlers;


import org.example.universityApp.domain.exceptions.CourseAlreadyExistsException;
import org.example.universityApp.domain.exceptions.CourseNotFoundException;
import org.example.universityApp.presentation.response.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@RestControllerAdvice
public class CourseExceptionsHandler {

    @ExceptionHandler(CourseAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleStudentNotFoundException(
            CourseAlreadyExistsException courseAlreadyExistsException,
            WebRequest webRequest
    ) {
        return new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                new Date(),
                courseAlreadyExistsException.getMessage(),
                webRequest.getDescription(false)
        );
    }

    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleStudentNotFoundException(
            CourseNotFoundException courseNotFoundException,
            WebRequest webRequest
    ) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                courseNotFoundException.getMessage(),
                webRequest.getDescription(false)
        );
    }
}
