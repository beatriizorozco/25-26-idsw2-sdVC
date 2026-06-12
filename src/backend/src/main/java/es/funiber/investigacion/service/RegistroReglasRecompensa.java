package es.funiber.investigacion.service;

import es.funiber.investigacion.model.TipoRecompensa;
import es.funiber.investigacion.model.Usuario;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RegistroReglasRecompensa {

    private final List<ReglaTipoRecompensa> reglas;

    public RegistroReglasRecompensa(List<ReglaTipoRecompensa> reglas) {
        this.reglas = List.copyOf(reglas);
    }

    public List<TipoRecompensa> tiposPermitidos(Usuario beneficiario) {
        return reglas.stream().filter(regla -> regla.permite(beneficiario)).map(ReglaTipoRecompensa::tipo).toList();
    }

    public void validar(TipoRecompensa tipo, Usuario beneficiario, java.math.BigDecimal valor) {
        ReglaTipoRecompensa regla = reglas.stream()
                .filter(candidata -> candidata.tipo() == tipo)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("El tipo de recompensa no esta soportado."));
        if (!regla.permite(beneficiario)) {
            throw new IllegalArgumentException(regla.mensajeNoPermitido());
        }
        regla.validarValor(valor);
    }
}
