package es.funiber.investigacion.exception;

public class SolicitudDuplicadaException extends RuntimeException {

    public SolicitudDuplicadaException() {
        super("Ya existe una solicitud de eliminación pendiente para este perfil.");
    }
}
