package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.DatosConvocatoria;
import java.time.LocalDate;

public record DatosConvocatoriaRequest(
        String titulo, String entidadConvocante, String area, String estado,
        LocalDate fechaApertura, LocalDate fechaCierre, String descripcion,
        String requisitos, String criteriosEvaluacion, String dotacion, String contacto,
        String referenciaExterna, String tipoFuente) {
    public DatosConvocatoria aModelo() {
        return new DatosConvocatoria(titulo, entidadConvocante, area, estado, fechaApertura, fechaCierre,
                descripcion, requisitos, criteriosEvaluacion, dotacion, contacto, referenciaExterna, tipoFuente);
    }
    public static DatosConvocatoriaRequest desde(DatosConvocatoria d) {
        return new DatosConvocatoriaRequest(d.titulo(), d.entidadConvocante(), d.area(), d.estado(),
                d.fechaApertura(), d.fechaCierre(), d.descripcion(), d.requisitos(), d.criteriosEvaluacion(),
                d.dotacion(), d.contacto(), d.referenciaExterna(), d.tipoFuente());
    }
}
