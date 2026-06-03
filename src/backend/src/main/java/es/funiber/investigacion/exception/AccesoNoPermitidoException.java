package es.funiber.investigacion.exception;

public class AccesoNoPermitidoException extends RuntimeException {

    public AccesoNoPermitidoException() {
        super("No tienes permisos para realizar esta operacion.");
    }

    public AccesoNoPermitidoException(String mensaje) {
        super(mensaje);
    }
}
