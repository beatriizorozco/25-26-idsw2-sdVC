package es.funiber.investigacion.dto;
import es.funiber.investigacion.model.ArchivoPublicacion;
import java.time.LocalDateTime;
public record ArchivoPublicacionResponse(Long id, String nombre, String tipoContenido, long tamano, String subidoPor, LocalDateTime fechaSubida) {
    public static ArchivoPublicacionResponse desde(ArchivoPublicacion archivo) {
        return new ArchivoPublicacionResponse(archivo.getId(), archivo.getNombre(), archivo.getTipoContenido(), archivo.getTamano(),
                archivo.getSubidoPor().getNombreCompleto(), archivo.getFechaSubida());
    }
}
