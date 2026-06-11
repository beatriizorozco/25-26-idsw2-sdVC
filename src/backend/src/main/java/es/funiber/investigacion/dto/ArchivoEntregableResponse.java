package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.ArchivoEntregable;
import java.time.LocalDateTime;

public record ArchivoEntregableResponse(
        Long id,
        int version,
        String nombre,
        String tipoContenido,
        long tamano,
        String subidoPor,
        LocalDateTime fechaSubida) {

    public static ArchivoEntregableResponse desde(ArchivoEntregable archivo) {
        return new ArchivoEntregableResponse(
                archivo.getId(),
                archivo.getVersion(),
                archivo.getNombre(),
                archivo.getTipoContenido(),
                archivo.getTamano(),
                archivo.getSubidoPor().getNombreUsuario(),
                archivo.getFechaSubida());
    }
}
