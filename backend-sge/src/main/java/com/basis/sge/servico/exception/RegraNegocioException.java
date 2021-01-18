package com.basis.sge.servico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Erro de negócio.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegraNegocioException extends RuntimeException {

    private String msg;
    private int request;

    public RegraNegocioException(final String message) {
        this(message, null);
    }

    public RegraNegocioException(final String message, final Throwable cause) {
        super(message, cause);
    }
}