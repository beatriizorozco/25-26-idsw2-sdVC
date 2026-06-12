package es.funiber.investigacion.service;

import es.funiber.investigacion.model.TipoRecompensa;
import es.funiber.investigacion.model.Usuario;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class ReglaReduccionDocente implements ReglaTipoRecompensa {

    private static final BigDecimal HORAS_POR_ASIGNATURA = new BigDecimal("4");
    private static final BigDecimal MAXIMA_REDUCCION = new BigDecimal("16");

    @Override
    public TipoRecompensa tipo() {
        return TipoRecompensa.REDUCCION_DOCENTE;
    }

    @Override
    public boolean permite(Usuario beneficiario) {
        return beneficiario.esInvestigadorDocente();
    }

    @Override
    public String mensajeNoPermitido() {
        return "La reduccion docente solo puede concederse a investigadores-docentes.";
    }

    @Override
    public void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0
                || valor.compareTo(MAXIMA_REDUCCION) > 0
                || valor.remainder(HORAS_POR_ASIGNATURA).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException(
                    "La reduccion docente debe corresponder a asignaturas completas de 4 horas, hasta un maximo de 16.");
        }
    }
}
