package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.CargaTrabajo;
import es.funiber.investigacion.model.Usuario;
import java.math.BigDecimal;

public record CargaTrabajoPersonaResponse(
        Long perfilId,
        String usuario,
        String nombreCompleto,
        String rol,
        String sede,
        boolean investigadorDocente,
        BigDecimal horasDocencia,
        BigDecimal horasInvestigacion,
        BigDecimal horasGestionAcademica,
        BigDecimal totalSemanal,
        BigDecimal margenDocente,
        String observaciones) {

    public static CargaTrabajoPersonaResponse desde(
            Usuario usuario,
            CargaTrabajo cargaTrabajo,
            BigDecimal maximoDocente) {
        boolean investigadorDocente = usuario.esInvestigadorDocente();
        BigDecimal margenDocente = investigadorDocente
                ? maximoDocente.subtract(cargaTrabajo.getHorasDocencia()).max(BigDecimal.ZERO)
                : BigDecimal.ZERO;

        return new CargaTrabajoPersonaResponse(
                usuario.getId(),
                usuario.getNombreUsuario(),
                usuario.getNombreCompleto(),
                usuario.getRol().name(),
                usuario.getSede().getEtiqueta(),
                investigadorDocente,
                cargaTrabajo.getHorasDocencia(),
                cargaTrabajo.getHorasInvestigacion(),
                cargaTrabajo.getHorasGestionAcademica(),
                cargaTrabajo.totalSemanal(),
                margenDocente,
                cargaTrabajo.getObservaciones());
    }
}
