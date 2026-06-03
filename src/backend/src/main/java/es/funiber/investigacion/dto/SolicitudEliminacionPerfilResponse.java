package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.EstadoSolicitudEliminacion;
import es.funiber.investigacion.model.SolicitudEliminacionPerfil;
import java.time.LocalDateTime;

public record SolicitudEliminacionPerfilResponse(
        Long id,
        Long perfilId,
        String usuario,
        String nombreCompleto,
        String motivo,
        EstadoSolicitudEliminacion estado,
        LocalDateTime fechaCreacion) {

    public static SolicitudEliminacionPerfilResponse desde(SolicitudEliminacionPerfil solicitud) {
        return new SolicitudEliminacionPerfilResponse(
                solicitud.getId(),
                solicitud.getUsuario().getId(),
                solicitud.getUsuario().getNombreUsuario(),
                solicitud.getUsuario().getNombreCompleto(),
                solicitud.getMotivo(),
                solicitud.getEstado(),
                solicitud.getFechaCreacion());
    }
}
