package es.funiber.investigacion.service;

import es.funiber.investigacion.model.DatosConvocatoria;
import es.funiber.investigacion.repository.ConvocatoriaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConvocatoria {
    private final ConvocatoriaRepository repositorio;
    public ValidadorConvocatoria(ConvocatoriaRepository repositorio) { this.repositorio = repositorio; }
    public void validar(DatosConvocatoria d) {
        if (d == null || vacio(d.titulo()) || vacio(d.entidadConvocante()) || vacio(d.area()) || vacio(d.estado())
                || vacio(d.descripcion()) || vacio(d.requisitos()) || vacio(d.criteriosEvaluacion())
                || vacio(d.dotacion()) || vacio(d.contacto()) || vacio(d.referenciaExterna()) || vacio(d.tipoFuente())) {
            throw new IllegalArgumentException("La convocatoria importada contiene campos obligatorios vacios.");
        }
        if (d.fechaApertura() != null && d.fechaCierre() != null && d.fechaCierre().isBefore(d.fechaApertura())) {
            throw new IllegalArgumentException("La fecha de cierre no puede ser anterior a la fecha de apertura.");
        }
    }
    public boolean yaIncorporada(String referenciaExterna) { return repositorio.existsByReferenciaExterna(referenciaExterna); }
    private boolean vacio(String valor) { return valor == null || valor.isBlank(); }
}
