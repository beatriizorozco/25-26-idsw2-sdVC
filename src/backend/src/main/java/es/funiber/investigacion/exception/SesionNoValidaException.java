package es.funiber.investigacion.exception;

public class SesionNoValidaException extends RuntimeException {

    public SesionNoValidaException() {
        super("La sesión no es válida");
    }
}

