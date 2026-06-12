package es.funiber.investigacion.service;

import es.funiber.investigacion.model.TipoRecompensa;
import es.funiber.investigacion.model.Usuario;
import java.math.BigDecimal;

public interface ReglaTipoRecompensa {
    TipoRecompensa tipo();
    boolean permite(Usuario beneficiario);
    void validarValor(BigDecimal valor);

    default String mensajeNoPermitido() {
        return "El beneficiario no puede recibir el tipo de recompensa seleccionado.";
    }
}
