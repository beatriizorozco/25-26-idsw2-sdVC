package es.funiber.investigacion.dto;

import java.math.BigDecimal;
import java.util.List;

public record PanelCargaTrabajoResponse(
        BigDecimal maximoDocenteSemanal,
        List<CargaTrabajoPersonaResponse> cargas,
        List<ProyectoLibreResponse> proyectosLibres,
        List<SugerenciaAsignacionResponse> sugerencias) {
}
