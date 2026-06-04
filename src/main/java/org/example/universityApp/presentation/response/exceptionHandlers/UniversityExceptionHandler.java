package org.example.universityApp.presentation.response.exceptionHandlers;


import org.example.universityApp.domain.exceptions.DomainException;
import org.example.universityApp.presentation.response.models.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


import java.util.Date;


@RestControllerAdvice
public class UniversityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorMessage> handleDomainException(
            DomainException ex,
            WebRequest request
    ) {
        ErrorMessage error = new ErrorMessage(
                ex.getHttpStatus().value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }
}
