package com.basis.sge.exceptions;

import org.springframework.http.HttpStatus;

// Esta classe vai indicar que um Recurso não foi encontrado
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message, HttpStatus notFound) {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
