package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.ErrorResponse;
import es.funiber.investigacion.exception.CredencialesIncorrectasException;
import es.funiber.investigacion.exception.SesionNoValidaException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CredencialesIncorrectasException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse credencialesIncorrectas(CredencialesIncorrectasException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(SesionNoValidaException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse sesionNoValida(SesionNoValidaException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}

