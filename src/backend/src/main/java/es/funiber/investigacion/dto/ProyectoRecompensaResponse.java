package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Proyecto;

public record ProyectoRecompensaResponse(Long id, String codigo, String nombre) {

    public static ProyectoRecompensaResponse desde(Proyecto proyecto) {
        return new ProyectoRecompensaResponse(proyecto.getId(), proyecto.getCodigo(), proyecto.getNombre());
    }
}
