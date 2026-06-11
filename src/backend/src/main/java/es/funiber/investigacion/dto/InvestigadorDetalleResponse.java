package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Usuario;
import java.util.List;

public record InvestigadorDetalleResponse(
        Long id,
        String usuario,
        String nombreCompleto,
        String email,
        String sede,
        String unidad,
        String lineaInvestigacion,
        String biografia,
        boolean investigadorDocente,
        List<ProyectoInvestigadorResponse> proyectos) {

    public static InvestigadorDetalleResponse desde(Usuario usuario, List<ProyectoInvestigadorResponse> proyectos) {
        return new InvestigadorDetalleResponse(
                usuario.getId(),
                usuario.getNombreUsuario(),
                valor(usuario.getNombreCompleto(), usuario.getNombreUsuario()),
                valor(usuario.getEmail(), ""),
                usuario.getSede().getEtiqueta(),
                valor(usuario.getUnidad(), ""),
                valor(usuario.getLineaInvestigacion(), ""),
                valor(usuario.getBiografia(), ""),
                usuario.esInvestigadorDocente(),
                proyectos);
    }

    private static String valor(String valor, String alternativa) {
        return valor == null || valor.isBlank() ? alternativa : valor;
    }
}
