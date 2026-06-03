package es.funiber.investigacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SolicitudEliminacionRequest(
        @NotBlank @Size(max = 500) String motivo) {
}
