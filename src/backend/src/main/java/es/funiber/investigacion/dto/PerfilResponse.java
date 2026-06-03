package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.Usuario;

public record PerfilResponse(
        Long id,
        String usuario,
        Rol rol,
        String nombreCompleto,
        String email,
        String unidad,
        String lineaInvestigacion,
        String biografia) {

    public static PerfilResponse desde(Usuario usuario) {
        return new PerfilResponse(
                usuario.getId(),
                usuario.getNombreUsuario(),
                usuario.getRol(),
                valor(usuario.getNombreCompleto(), usuario.getNombreUsuario()),
                valor(usuario.getEmail(), ""),
                valor(usuario.getUnidad(), ""),
                valor(usuario.getLineaInvestigacion(), ""),
                valor(usuario.getBiografia(), ""));
    }

    private static String valor(String valor, String alternativa) {
        return valor == null || valor.isBlank() ? alternativa : valor;
    }
}
