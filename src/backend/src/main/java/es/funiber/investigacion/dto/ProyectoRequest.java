package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.EstadoProyecto;
import es.funiber.investigacion.model.SedeFuniber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record ProyectoRequest(
        @NotBlank @Size(max = 40) String codigo,
        @NotBlank @Size(max = 180) String nombre,
        @Size(max = 1000) String descripcion,
        @Size(max = 120) String area,
        @NotNull SedeFuniber sede,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        @NotNull EstadoProyecto estado) {
}
