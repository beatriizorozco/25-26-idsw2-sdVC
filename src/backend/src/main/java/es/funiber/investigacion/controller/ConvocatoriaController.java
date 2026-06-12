package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.*;
import es.funiber.investigacion.service.ConsultaConvocatoriaService;
import es.funiber.investigacion.service.ImportacionConvocatoriaService;
import es.funiber.investigacion.service.SesionService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/convocatorias")
public class ConvocatoriaController {
    private final SesionService sesion;
    private final ConsultaConvocatoriaService consulta;
    private final ImportacionConvocatoriaService importacion;
    public ConvocatoriaController(SesionService sesion, ConsultaConvocatoriaService consulta, ImportacionConvocatoriaService importacion) {
        this.sesion = sesion; this.consulta = consulta; this.importacion = importacion;
    }
    @GetMapping public List<ConvocatoriaResponse> listar(@RequestParam(defaultValue = "") String texto,
            @RequestParam(defaultValue = "") String area, @RequestParam(defaultValue = "") String estado, Authentication auth) {
        return consulta.listar(usuario(auth), texto, area, estado);
    }
    @GetMapping("/{id}") public ConvocatoriaResponse obtener(@PathVariable Long id, Authentication auth) { return consulta.obtener(usuario(auth), id); }
    @PostMapping("/importaciones/previsualizacion")
    public PrevisualizacionConvocatoriaResponse previsualizar(@RequestBody FuenteConvocatoriaRequest fuente, Authentication auth) {
        return importacion.previsualizar(usuario(auth), fuente);
    }
    @PostMapping("/importaciones") @ResponseStatus(HttpStatus.CREATED)
    public ConvocatoriaResponse confirmar(@RequestBody ConfirmarImportacionConvocatoriaRequest request, Authentication auth) {
        return importacion.confirmar(usuario(auth), request);
    }
    private String usuario(Authentication auth) { return sesion.obtenerSesionActual(auth).usuario(); }
}
