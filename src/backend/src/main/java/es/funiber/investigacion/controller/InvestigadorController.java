package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.InvestigadorCreateRequest;
import es.funiber.investigacion.dto.InvestigadorDetalleResponse;
import es.funiber.investigacion.dto.InvestigadorResumenResponse;
import es.funiber.investigacion.service.InvestigadorService;
import es.funiber.investigacion.service.SesionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/investigadores")
public class InvestigadorController {

    private final SesionService sesionService;
    private final InvestigadorService investigadorService;

    public InvestigadorController(SesionService sesionService, InvestigadorService investigadorService) {
        this.sesionService = sesionService;
        this.investigadorService = investigadorService;
    }

    @GetMapping
    public List<InvestigadorResumenResponse> listar(
            @RequestParam(defaultValue = "") String criterio,
            @RequestParam(required = false) Long proyectoId,
            Authentication authentication) {
        return investigadorService.listar(usuario(authentication), criterio, proyectoId);
    }

    @GetMapping("/{id}")
    public InvestigadorDetalleResponse obtener(
            @PathVariable Long id,
            @RequestParam(required = false) Long proyectoId,
            Authentication authentication) {
        return investigadorService.obtener(usuario(authentication), id, proyectoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvestigadorDetalleResponse crear(
            @Valid @RequestBody InvestigadorCreateRequest request,
            Authentication authentication) {
        return investigadorService.crear(usuario(authentication), request);
    }

    private String usuario(Authentication authentication) {
        return sesionService.obtenerSesionActual(authentication).usuario();
    }
}
