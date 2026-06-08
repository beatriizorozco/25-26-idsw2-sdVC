package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.ArchivoProyectoResponse;
import es.funiber.investigacion.model.ArchivoProyecto;
import es.funiber.investigacion.service.ArchivoProyectoService;
import es.funiber.investigacion.service.SesionService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/proyectos/{proyectoId}/archivos")
public class ArchivoProyectoController {

    private final SesionService sesionService;
    private final ArchivoProyectoService archivoService;

    public ArchivoProyectoController(SesionService sesionService, ArchivoProyectoService archivoService) {
        this.sesionService = sesionService;
        this.archivoService = archivoService;
    }

    @GetMapping
    public List<ArchivoProyectoResponse> listar(@PathVariable Long proyectoId, Authentication authentication) {
        return archivoService.listar(usuario(authentication), proyectoId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ArchivoProyectoResponse subir(
            @PathVariable Long proyectoId,
            @RequestPart("archivo") MultipartFile archivo,
            Authentication authentication) {
        return archivoService.subir(usuario(authentication), proyectoId, archivo);
    }

    @GetMapping("/{archivoId}")
    public ResponseEntity<byte[]> descargar(
            @PathVariable Long proyectoId,
            @PathVariable Long archivoId,
            Authentication authentication) {
        ArchivoProyecto archivo = archivoService.descargar(usuario(authentication), proyectoId, archivoId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(archivo.getTipoContenido()))
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                        .filename(archivo.getNombre(), StandardCharsets.UTF_8)
                        .build()
                        .toString())
                .body(archivo.getContenido());
    }

    @DeleteMapping("/{archivoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(
            @PathVariable Long proyectoId,
            @PathVariable Long archivoId,
            Authentication authentication) {
        archivoService.eliminar(usuario(authentication), proyectoId, archivoId);
    }

    private String usuario(Authentication authentication) {
        return sesionService.obtenerSesionActual(authentication).usuario();
    }
}
