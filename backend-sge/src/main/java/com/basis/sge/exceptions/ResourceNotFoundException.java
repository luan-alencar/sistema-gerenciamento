package com.basis.sge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends RuntimeException {

    private HttpStatus notFound;

    public ResourceNotFoundException(String message, HttpStatus notFound) {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
