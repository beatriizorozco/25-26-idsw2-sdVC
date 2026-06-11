package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Entregable;
import java.time.LocalDateTime;
import java.util.List;

public record EntregableResponse(
        Long id,
        Long proyectoId,
        String titulo,
        String descripcion,
        String estado,
        Long autorId,
        String autor,
        String autorNombre,
        LocalDateTime fechaCreacion,
        boolean propio,
        List<ArchivoEntregableResponse> archivos) {

    public static EntregableResponse desde(
            Entregable entregable,
            Long usuarioActualId,
            List<ArchivoEntregableResponse> archivos) {
        return new EntregableResponse(
                entregable.getId(),
                entregable.getProyecto().getId(),
                entregable.getTitulo(),
                entregable.getDescripcion(),
                entregable.getEstado(),
                entregable.getAutor().getId(),
                entregable.getAutor().getNombreUsuario(),
                entregable.getAutor().getNombreCompleto(),
                entregable.getFechaCreacion(),
                entregable.getAutor().getId().equals(usuarioActualId),
                archivos);
    }
}
