package es.funiber.investigacion.dto;

import java.util.List;

public record SugerenciaAsignacionResponse(
        ProyectoLibreResponse proyecto,
        List<CargaTrabajoPersonaResponse> candidatos) {
}
