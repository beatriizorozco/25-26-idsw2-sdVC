package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.PerfilResponse;
import es.funiber.investigacion.dto.PerfilUpdateRequest;
import es.funiber.investigacion.dto.SolicitudEliminacionPerfilResponse;
import es.funiber.investigacion.dto.SolicitudEliminacionRequest;
import es.funiber.investigacion.service.PerfilService;
import es.funiber.investigacion.service.SesionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PerfilController {

    private final SesionService sesionService;
    private final PerfilService perfilService;

    public PerfilController(SesionService sesionService, PerfilService perfilService) {
        this.sesionService = sesionService;
        this.perfilService = perfilService;
    }

    @GetMapping("/perfil")
    public PerfilResponse obtenerPerfil(Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return perfilService.obtenerPerfilPropio(usuario);
    }

    @GetMapping("/perfiles/{perfilId}")
    public PerfilResponse obtenerPerfilPorId(
            @PathVariable Long perfilId,
            Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return perfilService.obtenerPerfilPorId(usuario, perfilId);
    }

    @PatchMapping("/perfil")
    public PerfilResponse actualizarPerfil(
            @Valid @RequestBody PerfilUpdateRequest request,
            Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return perfilService.actualizarPerfilPropio(usuario, request);
    }

    @PatchMapping("/perfiles/{perfilId}")
    public PerfilResponse actualizarPerfilPorId(
            @PathVariable Long perfilId,
            @Valid @RequestBody PerfilUpdateRequest request,
            Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return perfilService.actualizarPerfilPorId(usuario, perfilId, request);
    }

    @PostMapping("/perfil/solicitud-eliminacion")
    public SolicitudEliminacionPerfilResponse solicitarEliminacionPerfil(
            @Valid @RequestBody SolicitudEliminacionRequest request,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        SolicitudEliminacionPerfilResponse solicitud =
                perfilService.solicitarEliminacionPerfil(usuario, request);
        sesionService.cerrarSesion(servletRequest, servletResponse, authentication);
        return solicitud;
    }
}
