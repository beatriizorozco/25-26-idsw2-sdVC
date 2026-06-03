package es.funiber.investigacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PerfilUpdateRequest(
        @NotBlank @Size(max = 120) String nombreCompleto,
        @Size(max = 120) String email,
        @Size(max = 120) String unidad,
        @Size(max = 160) String lineaInvestigacion,
        @Size(max = 500) String biografia) {
}
