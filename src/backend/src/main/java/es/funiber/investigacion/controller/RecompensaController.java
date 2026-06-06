package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.BeneficiarioRecompensaResponse;
import es.funiber.investigacion.dto.OpcionesCreacionRecompensaResponse;
import es.funiber.investigacion.dto.RecompensaEdicionResponse;
import es.funiber.investigacion.dto.RecompensaRequest;
import es.funiber.investigacion.dto.RecompensaResponse;
import es.funiber.investigacion.service.RecompensaService;
import es.funiber.investigacion.service.SesionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/recompensas")
public class RecompensaController {

    private final SesionService sesionService;
    private final RecompensaService recompensaService;

    public RecompensaController(SesionService sesionService, RecompensaService recompensaService) {
        this.sesionService = sesionService;
        this.recompensaService = recompensaService;
    }

    @GetMapping
    public List<RecompensaResponse> listarGlobales(
            @RequestParam(defaultValue = "") String criterio,
            Authentication authentication) {
        return recompensaService.listarGlobales(usuario(authentication), criterio);
    }

    @GetMapping("/me")
    public List<RecompensaResponse> listarPropias(
            @RequestParam(defaultValue = "") String criterio,
            Authentication authentication) {
        return recompensaService.listarPropias(usuario(authentication), criterio);
    }

    @GetMapping("/me/{id}")
    public RecompensaResponse obtenerPropia(@PathVariable Long id, Authentication authentication) {
        return recompensaService.obtenerPropia(usuario(authentication), id);
    }

    @GetMapping("/opciones-creacion")
    public OpcionesCreacionRecompensaResponse prepararCreacion(Authentication authentication) {
        return recompensaService.prepararCreacion(usuario(authentication));
    }

    @GetMapping("/opciones-creacion/{proyectoId}/beneficiarios")
    public List<BeneficiarioRecompensaResponse> obtenerBeneficiarios(
            @PathVariable Long proyectoId,
            Authentication authentication) {
        return recompensaService.obtenerBeneficiariosElegibles(usuario(authentication), proyectoId);
    }

    @GetMapping("/opciones-creacion/{proyectoId}/beneficiarios/{investigadorId}/tipos")
    public List<String> obtenerTipos(
            @PathVariable Long proyectoId,
            @PathVariable Long investigadorId,
            Authentication authentication) {
        return recompensaService.obtenerTiposPermitidos(usuario(authentication), proyectoId, investigadorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecompensaResponse crear(
            @Valid @RequestBody RecompensaRequest request,
            Authentication authentication) {
        return recompensaService.crear(usuario(authentication), request);
    }

    @GetMapping("/{id}/edicion")
    public RecompensaEdicionResponse prepararEdicion(@PathVariable Long id, Authentication authentication) {
        return recompensaService.prepararEdicion(usuario(authentication), id);
    }

    @GetMapping("/{id}")
    public RecompensaResponse obtenerGlobal(@PathVariable Long id, Authentication authentication) {
        return recompensaService.obtenerGlobal(usuario(authentication), id);
    }

    @PatchMapping("/{id}")
    public RecompensaResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody RecompensaRequest request,
            Authentication authentication) {
        return recompensaService.actualizar(usuario(authentication), id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id, Authentication authentication) {
        recompensaService.eliminar(usuario(authentication), id);
    }

    private String usuario(Authentication authentication) {
        return sesionService.obtenerSesionActual(authentication).usuario();
    }
}
