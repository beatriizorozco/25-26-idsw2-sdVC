package es.funiber.investigacion.dto;
import es.funiber.investigacion.model.RespuestaPublicacion;
import java.time.LocalDateTime;
public record RespuestaPublicacionResponse(Long id, String autor, String autorNombre, String contenido, LocalDateTime fecha) {
    public static RespuestaPublicacionResponse desde(RespuestaPublicacion respuesta) {
        return new RespuestaPublicacionResponse(respuesta.getId(), respuesta.getAutor().getNombreUsuario(),
                respuesta.getAutor().getNombreCompleto(), respuesta.getContenido(), respuesta.getFecha());
    }
}
