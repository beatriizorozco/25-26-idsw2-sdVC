package es.funiber.investigacion.dto;
import es.funiber.investigacion.model.Publicacion;
import java.time.LocalDateTime;
import java.util.List;
public record PublicacionResponse(
        Long id, String titulo, String contenido, Long autorId, String autor, String autorNombre,
        LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, boolean propia,
        List<RespuestaPublicacionResponse> respuestas, List<ArchivoPublicacionResponse> archivos) {
    public static PublicacionResponse desde(Publicacion publicacion, Long usuarioId,
            List<RespuestaPublicacionResponse> respuestas, List<ArchivoPublicacionResponse> archivos) {
        return new PublicacionResponse(publicacion.getId(), publicacion.getTitulo(), publicacion.getContenido(),
                publicacion.getAutor().getId(), publicacion.getAutor().getNombreUsuario(), publicacion.getAutor().getNombreCompleto(),
                publicacion.getFechaCreacion(), publicacion.getFechaActualizacion(), publicacion.getAutor().getId().equals(usuarioId),
                respuestas, archivos);
    }
}
