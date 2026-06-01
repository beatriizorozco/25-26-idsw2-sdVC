package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.PanelPrincipalResponse;
import es.funiber.investigacion.dto.SesionResponse;
import es.funiber.investigacion.service.PanelPrincipalService;
import es.funiber.investigacion.service.SesionService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/panel-principal")
public class PanelPrincipalController {

    private final SesionService sesionService;
    private final PanelPrincipalService panelPrincipalService;

    public PanelPrincipalController(
            SesionService sesionService,
            PanelPrincipalService panelPrincipalService) {
        this.sesionService = sesionService;
        this.panelPrincipalService = panelPrincipalService;
    }

    @GetMapping
    public PanelPrincipalResponse obtenerPanelPrincipal(Authentication authentication) {
        SesionResponse sesion = sesionService.obtenerSesionActual(authentication);
        return new PanelPrincipalResponse(
                sesion.rol(),
                panelPrincipalService.obtenerAccionesDisponibles(sesion.rol()));
    }
}

