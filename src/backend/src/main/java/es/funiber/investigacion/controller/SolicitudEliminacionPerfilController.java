package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.EliminacionPerfilResponse;
import es.funiber.investigacion.dto.SolicitudEliminacionPerfilResponse;
import es.funiber.investigacion.service.PerfilService;
import es.funiber.investigacion.service.SesionService;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solicitudes-eliminacion-perfil")
public class SolicitudEliminacionPerfilController {

    private final SesionService sesionService;
    private final PerfilService perfilService;

    public SolicitudEliminacionPerfilController(
            SesionService sesionService,
            PerfilService perfilService) {
        this.sesionService = sesionService;
        this.perfilService = perfilService;
    }

    @GetMapping
    public List<SolicitudEliminacionPerfilResponse> listarSolicitudes(
            @RequestParam(required = false) String criterio,
            Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return perfilService.listarSolicitudesPendientes(usuario, criterio);
    }

    @GetMapping("/{solicitudId}")
    public SolicitudEliminacionPerfilResponse obtenerSolicitud(
            @PathVariable Long solicitudId,
            Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return perfilService.obtenerSolicitud(usuario, solicitudId);
    }

    @DeleteMapping("/{solicitudId}/perfil")
    public EliminacionPerfilResponse eliminarPerfil(
            @PathVariable Long solicitudId,
            Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return perfilService.eliminarPerfilDesdeSolicitud(usuario, solicitudId);
    }
}
