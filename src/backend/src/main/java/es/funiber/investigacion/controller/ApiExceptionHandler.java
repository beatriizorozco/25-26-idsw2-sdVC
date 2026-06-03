package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.ErrorResponse;
import es.funiber.investigacion.exception.AccesoNoPermitidoException;
import es.funiber.investigacion.exception.CredencialesIncorrectasException;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.exception.SesionNoValidaException;
import es.funiber.investigacion.exception.SolicitudDuplicadaException;
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

    @ExceptionHandler(AccesoNoPermitidoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse accesoNoPermitido(AccesoNoPermitidoException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse recursoNoEncontrado(RecursoNoEncontradoException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(SolicitudDuplicadaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse solicitudDuplicada(SolicitudDuplicadaException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}

