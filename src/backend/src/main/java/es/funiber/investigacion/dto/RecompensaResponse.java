package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Recompensa;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RecompensaResponse(
        Long id,
        Long proyectoId,
        String proyectoCodigo,
        String proyectoNombre,
        Long beneficiarioId,
        String beneficiario,
        String beneficiarioNombre,
        String tipo,
        String tipoEtiqueta,
        String concepto,
        BigDecimal valor,
        LocalDateTime fechaCreacion) {

    public static RecompensaResponse desde(Recompensa recompensa) {
        return new RecompensaResponse(
                recompensa.getId(),
                recompensa.getProyecto().getId(),
                recompensa.getProyecto().getCodigo(),
                recompensa.getProyecto().getNombre(),
                recompensa.getBeneficiario().getId(),
                recompensa.getBeneficiario().getNombreUsuario(),
                recompensa.getBeneficiario().getNombreCompleto(),
                recompensa.getTipo().name(),
                recompensa.getTipo().getEtiqueta(),
                recompensa.getConcepto(),
                recompensa.getValor(),
                recompensa.getFechaCreacion());
    }
}
