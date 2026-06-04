package es.funiber.investigacion.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CargaTrabajoUpdateRequest(
        @NotNull @DecimalMin("0.0") BigDecimal horasDocencia,
        @NotNull @DecimalMin("0.0") BigDecimal horasInvestigacion,
        @NotNull @DecimalMin("0.0") BigDecimal horasGestionAcademica,
        @Size(max = 500) String observaciones) {
}
