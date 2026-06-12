package es.funiber.investigacion.model;

import java.time.LocalDate;

public record DatosConvocatoria(
        String titulo,
        String entidadConvocante,
        String area,
        String estado,
        LocalDate fechaApertura,
        LocalDate fechaCierre,
        String descripcion,
        String requisitos,
        String criteriosEvaluacion,
        String dotacion,
        String contacto,
        String referenciaExterna,
        String tipoFuente) {}
