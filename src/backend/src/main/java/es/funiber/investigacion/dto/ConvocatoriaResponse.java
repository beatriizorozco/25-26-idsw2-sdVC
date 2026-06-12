package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Convocatoria;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ConvocatoriaResponse(
        Long id, String titulo, String entidadConvocante, String area, String estado,
        LocalDate fechaApertura, LocalDate fechaCierre, String descripcion, String requisitos,
        String criteriosEvaluacion, String dotacion, String contacto, String referenciaExterna,
        String tipoFuente, boolean incorporada, LocalDateTime fechaImportacion) {
    public static ConvocatoriaResponse desde(Convocatoria c) {
        return new ConvocatoriaResponse(c.getId(), c.getTitulo(), c.getEntidadConvocante(), c.getArea(),
                c.getEstado(), c.getFechaApertura(), c.getFechaCierre(), c.getDescripcion(), c.getRequisitos(),
                c.getCriteriosEvaluacion(), c.getDotacion(), c.getContacto(), c.getReferenciaExterna(),
                c.getTipoFuente(), c.isIncorporada(), c.getFechaImportacion());
    }
}
