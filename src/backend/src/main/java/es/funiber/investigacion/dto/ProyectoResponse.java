package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Proyecto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ProyectoResponse(
        Long id,
        String codigo,
        String nombre,
        String descripcion,
        String area,
        String sede,
        String estado,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        boolean archivado,
        LocalDateTime fechaArchivado,
        String motivoArchivado,
        List<InvestigadorProyectoResponse> investigadores) {

    public static ProyectoResponse desde(Proyecto proyecto) {
        return new ProyectoResponse(
                proyecto.getId(),
                proyecto.getCodigo(),
                proyecto.getNombre(),
                proyecto.getDescripcion(),
                proyecto.getArea(),
                proyecto.getSede() == null ? "" : proyecto.getSede().getEtiqueta(),
                proyecto.getEstado().name(),
                proyecto.getFechaInicio(),
                proyecto.getFechaFin(),
                proyecto.isArchivado(),
                proyecto.getFechaArchivado(),
                proyecto.getMotivoArchivado(),
                proyecto.getInvestigadores().stream()
                        .map(InvestigadorProyectoResponse::desde)
                        .sorted((a, b) -> a.nombreCompleto().compareToIgnoreCase(b.nombreCompleto()))
                        .toList());
    }
}
