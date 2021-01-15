package com.basis.sge.exceptions.exceptionhandler;

import com.basis.sge.exceptions.Problem;
import com.basis.sge.exceptions.ResourceNotFoundException;
import com.basis.sge.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    // Interface utilizada para resolução de mensagens
    private MessageSource messageSource;

    // Caso o Recurso não seja encontraddo
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var problema = new Problem(status.value(), ex.getMessage(), OffsetDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    // Caso não exista um usuario
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var problema = new Problem(status.value(), ex.getMessage(), OffsetDateTime.now());
        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        var campos = new ArrayList<Problem.Campo>();

        // faco uma interacao buscando todos os erros
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            // LocaleContextHolder e p pegar a regiao que estamos
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            // e adiciono como campos na lista
            campos.add(new Problem.Campo(nome, mensagem));
        }

        // dps nossa lista e atribuada ao erro
        var problema = new Problem();
        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos estao invalidos. " + "Faça o preenchimento correto e tente novamente");
        problema.setDataHora(OffsetDateTime.now());
        problema.setCampos(campos);
        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }
}