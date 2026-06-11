package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.EntregableRequest;
import es.funiber.investigacion.dto.EntregableResponse;
import es.funiber.investigacion.dto.MotivoRequest;
import es.funiber.investigacion.model.ArchivoEntregable;
import es.funiber.investigacion.service.EntregableService;
import es.funiber.investigacion.service.SesionService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class EntregableController {

    private final SesionService sesionService;
    private final EntregableService entregableService;

    public EntregableController(SesionService sesionService, EntregableService entregableService) {
        this.sesionService = sesionService;
        this.entregableService = entregableService;
    }

    @GetMapping("/proyectos/{proyectoId}/entregables")
    public List<EntregableResponse> listar(@PathVariable Long proyectoId, Authentication authentication) {
        return entregableService.listar(usuario(authentication), proyectoId);
    }

    @GetMapping("/entregables/{id}")
    public EntregableResponse obtener(@PathVariable Long id, Authentication authentication) {
        return entregableService.obtener(usuario(authentication), id);
    }

    @PostMapping(value = "/proyectos/{proyectoId}/entregables", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EntregableResponse crear(
            @PathVariable Long proyectoId,
            @RequestPart("datos") EntregableRequest datos,
            @RequestPart(value = "archivo", required = false) MultipartFile archivo,
            Authentication authentication) {
        return entregableService.crear(usuario(authentication), proyectoId, datos, archivo);
    }

    @PatchMapping(value = "/entregables/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public EntregableResponse actualizar(
            @PathVariable Long id,
            @RequestPart("datos") EntregableRequest datos,
            @RequestPart(value = "archivo", required = false) MultipartFile archivo,
            Authentication authentication) {
        return entregableService.actualizar(usuario(authentication), id, datos, archivo);
    }

    @PatchMapping("/entregables/{id}/retirada")
    public EntregableResponse retirar(
            @PathVariable Long id,
            @org.springframework.web.bind.annotation.RequestBody MotivoRequest motivo,
            Authentication authentication) {
        return entregableService.retirar(usuario(authentication), id, motivo.motivo());
    }

    @GetMapping("/entregables/{entregableId}/archivos/{archivoId}")
    public ResponseEntity<byte[]> descargar(
            @PathVariable Long entregableId,
            @PathVariable Long archivoId,
            Authentication authentication) {
        ArchivoEntregable archivo = entregableService.descargar(usuario(authentication), entregableId, archivoId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(archivo.getTipoContenido()))
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                        .filename(archivo.getNombre(), StandardCharsets.UTF_8)
                        .build().toString())
                .body(archivo.getContenido());
    }

    private String usuario(Authentication authentication) {
        return sesionService.obtenerSesionActual(authentication).usuario();
    }
}
