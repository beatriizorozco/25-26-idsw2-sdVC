package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.SedeFuniber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InvestigadorCreateRequest(
        @NotBlank @Size(max = 80) String usuario,
        @NotBlank @Size(max = 120) String nombreCompleto,
        @NotBlank @Email @Size(max = 120) String email,
        @NotNull SedeFuniber sede,
        @Size(max = 120) String unidad,
        @Size(max = 160) String lineaInvestigacion,
        @Size(max = 500) String biografia) {
}
