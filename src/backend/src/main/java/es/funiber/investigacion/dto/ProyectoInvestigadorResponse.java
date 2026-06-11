package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Proyecto;

public record ProyectoInvestigadorResponse(
        Long id,
        String codigo,
        String nombre,
        String sede,
        String area,
        String estado) {

    public static ProyectoInvestigadorResponse desde(Proyecto proyecto) {
        return new ProyectoInvestigadorResponse(
                proyecto.getId(),
                proyecto.getCodigo(),
                proyecto.getNombre(),
                proyecto.getSede() == null ? "" : proyecto.getSede().getEtiqueta(),
                proyecto.getArea() == null ? "" : proyecto.getArea(),
                proyecto.getEstado().name());
    }
}
