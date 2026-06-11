package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Usuario;
import java.math.BigDecimal;

public record InvestigadorResumenResponse(
        Long id,
        String usuario,
        String nombreCompleto,
        String sede,
        String unidad,
        String lineaInvestigacion,
        boolean investigadorDocente,
        BigDecimal cargaSemanal) {

    public static InvestigadorResumenResponse desde(Usuario usuario, BigDecimal cargaSemanal) {
        return new InvestigadorResumenResponse(
                usuario.getId(),
                usuario.getNombreUsuario(),
                valor(usuario.getNombreCompleto(), usuario.getNombreUsuario()),
                usuario.getSede().getEtiqueta(),
                valor(usuario.getUnidad(), ""),
                valor(usuario.getLineaInvestigacion(), ""),
                usuario.esInvestigadorDocente(),
                cargaSemanal);
    }

    private static String valor(String valor, String alternativa) {
        return valor == null || valor.isBlank() ? alternativa : valor;
    }
}
