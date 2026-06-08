package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Usuario;
import java.math.BigDecimal;

public record InvestigadorProyectoResponse(
        Long id,
        String usuario,
        String nombreCompleto,
        String sede,
        boolean investigadorDocente,
        BigDecimal cargaSemanal) {

    public static InvestigadorProyectoResponse desde(Usuario usuario) {
        return desde(usuario, null);
    }

    public static InvestigadorProyectoResponse desde(Usuario usuario, BigDecimal cargaSemanal) {
        return new InvestigadorProyectoResponse(
                usuario.getId(),
                usuario.getNombreUsuario(),
                usuario.getNombreCompleto(),
                usuario.getSede().getEtiqueta(),
                usuario.esInvestigadorDocente(),
                cargaSemanal);
    }
}
