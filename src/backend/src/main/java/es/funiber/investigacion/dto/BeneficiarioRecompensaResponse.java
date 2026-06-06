package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Usuario;
import java.util.List;

public record BeneficiarioRecompensaResponse(
        Long id,
        String usuario,
        String nombreCompleto,
        String sede,
        boolean investigadorDocente,
        List<String> tiposPermitidos) {

    public static BeneficiarioRecompensaResponse desde(Usuario usuario, List<String> tiposPermitidos) {
        return new BeneficiarioRecompensaResponse(
                usuario.getId(),
                usuario.getNombreUsuario(),
                usuario.getNombreCompleto(),
                usuario.getSede().getEtiqueta(),
                usuario.esInvestigadorDocente(),
                tiposPermitidos);
    }
}
