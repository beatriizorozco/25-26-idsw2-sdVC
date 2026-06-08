package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.ArchivoProyecto;
import java.time.LocalDateTime;

public record ArchivoProyectoResponse(
        Long id,
        String nombre,
        String tipoContenido,
        long tamano,
        String subidoPor,
        LocalDateTime fechaSubida) {

    public static ArchivoProyectoResponse desde(ArchivoProyecto archivo) {
        return new ArchivoProyectoResponse(
                archivo.getId(),
                archivo.getNombre(),
                archivo.getTipoContenido(),
                archivo.getTamano(),
                archivo.getSubidoPor().getNombreCompleto(),
                archivo.getFechaSubida());
    }
}
