package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.CargaTrabajoPersonaResponse;
import es.funiber.investigacion.dto.CargaTrabajoUpdateRequest;
import es.funiber.investigacion.dto.PanelCargaTrabajoResponse;
import es.funiber.investigacion.service.CargaTrabajoService;
import es.funiber.investigacion.service.SesionService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carga-trabajo")
public class CargaTrabajoController {

    private final SesionService sesionService;
    private final CargaTrabajoService cargaTrabajoService;

    public CargaTrabajoController(SesionService sesionService, CargaTrabajoService cargaTrabajoService) {
        this.sesionService = sesionService;
        this.cargaTrabajoService = cargaTrabajoService;
    }

    @GetMapping
    public PanelCargaTrabajoResponse obtenerPanel(
            @RequestParam(defaultValue = "") String criterio,
            Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return cargaTrabajoService.obtenerPanelCoordinador(usuario, criterio);
    }

    @GetMapping("/me")
    public CargaTrabajoPersonaResponse obtenerCargaPropia(Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return cargaTrabajoService.obtenerCargaPropia(usuario);
    }

    @PatchMapping("/me")
    public CargaTrabajoPersonaResponse actualizarCargaPropia(
            @Valid @RequestBody CargaTrabajoUpdateRequest request,
            Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return cargaTrabajoService.actualizarCargaPropia(usuario, request);
    }

    @GetMapping("/{perfilId}")
    public CargaTrabajoPersonaResponse obtenerCargaPorPerfil(
            @PathVariable Long perfilId,
            Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return cargaTrabajoService.obtenerCargaPorPerfil(usuario, perfilId);
    }

    @PatchMapping("/{perfilId}")
    public CargaTrabajoPersonaResponse actualizarCargaPorPerfil(
            @PathVariable Long perfilId,
            @Valid @RequestBody CargaTrabajoUpdateRequest request,
            Authentication authentication) {
        String usuario = sesionService.obtenerSesionActual(authentication).usuario();
        return cargaTrabajoService.actualizarCargaPorPerfil(usuario, perfilId, request);
    }
}
