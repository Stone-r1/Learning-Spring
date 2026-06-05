package org.example.universityApp.domain.exceptions;

// Including this as compromise. Strict DDD would make ErrorStatus enum in domain and mapper in presentation layer.
import org.springframework.http.HttpStatus;


public abstract class DomainException extends RuntimeException {
    private final HttpStatus httpStatus;

    public DomainException(
            String message,
            HttpStatus httpStatus
    ) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
