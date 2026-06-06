package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.TipoRecompensa;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record RecompensaRequest(
        Long proyectoId,
        @NotNull Long beneficiarioId,
        @NotNull TipoRecompensa tipo,
        @NotBlank @Size(max = 240) String concepto,
        @NotNull @DecimalMin(value = "0.01") BigDecimal valor) {
}
