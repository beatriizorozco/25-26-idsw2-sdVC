package es.funiber.investigacion.service;

import es.funiber.investigacion.model.TipoRecompensa;
import es.funiber.investigacion.model.Usuario;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class ReglaRecompensaEconomica implements ReglaTipoRecompensa {
    @Override
    public TipoRecompensa tipo() {
        return TipoRecompensa.ECONOMICA;
    }

    @Override
    public boolean permite(Usuario beneficiario) {
        return true;
    }

    @Override
    public void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El importe economico debe ser mayor que cero.");
        }
    }
}
