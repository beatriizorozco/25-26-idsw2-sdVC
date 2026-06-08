package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.InvestigadorProyectoResponse;
import es.funiber.investigacion.dto.MotivoRequest;
import es.funiber.investigacion.dto.ProyectoRequest;
import es.funiber.investigacion.dto.ProyectoResponse;
import es.funiber.investigacion.service.ProyectoService;
import es.funiber.investigacion.service.SesionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final SesionService sesionService;
    private final ProyectoService proyectoService;

    public ProyectoController(SesionService sesionService, ProyectoService proyectoService) {
        this.sesionService = sesionService;
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public List<ProyectoResponse> listar(
            @RequestParam(defaultValue = "false") boolean archivados,
            @RequestParam(defaultValue = "") String criterio,
            Authentication authentication) {
        return proyectoService.listar(usuario(authentication), archivados, criterio);
    }

    @GetMapping("/{id}")
    public ProyectoResponse obtener(@PathVariable Long id, Authentication authentication) {
        return proyectoService.obtener(usuario(authentication), id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProyectoResponse crear(@Valid @RequestBody ProyectoRequest request, Authentication authentication) {
        return proyectoService.crear(usuario(authentication), request);
    }

    @PatchMapping("/{id}")
    public ProyectoResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProyectoRequest request,
            Authentication authentication) {
        return proyectoService.actualizar(usuario(authentication), id, request);
    }

    @PatchMapping("/{id}/archivado")
    public ProyectoResponse archivar(
            @PathVariable Long id,
            @Valid @RequestBody MotivoRequest request,
            Authentication authentication) {
        return proyectoService.archivar(usuario(authentication), id, request.motivo());
    }

    @GetMapping("/{id}/candidatos")
    public List<InvestigadorProyectoResponse> candidatos(@PathVariable Long id, Authentication authentication) {
        return proyectoService.listarCandidatos(usuario(authentication), id);
    }

    @PostMapping("/{id}/investigadores/{investigadorId}")
    public ProyectoResponse agregarInvestigador(
            @PathVariable Long id,
            @PathVariable Long investigadorId,
            Authentication authentication) {
        return proyectoService.agregarInvestigador(usuario(authentication), id, investigadorId);
    }

    @PatchMapping("/{id}/investigadores/{investigadorId}/desasignado")
    public ProyectoResponse retirarInvestigador(
            @PathVariable Long id,
            @PathVariable Long investigadorId,
            @Valid @RequestBody MotivoRequest request,
            Authentication authentication) {
        return proyectoService.retirarInvestigador(usuario(authentication), id, investigadorId, request.motivo());
    }

    private String usuario(Authentication authentication) {
        return sesionService.obtenerSesionActual(authentication).usuario();
    }
}
