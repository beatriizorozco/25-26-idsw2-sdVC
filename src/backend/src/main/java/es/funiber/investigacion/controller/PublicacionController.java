package es.funiber.investigacion.controller;

import es.funiber.investigacion.dto.*;
import es.funiber.investigacion.model.ArchivoPublicacion;
import es.funiber.investigacion.service.PublicacionService;
import es.funiber.investigacion.service.SesionService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {
    private final SesionService sesion;
    private final PublicacionService servicio;
    public PublicacionController(SesionService sesion, PublicacionService servicio) { this.sesion = sesion; this.servicio = servicio; }

    @GetMapping public List<PublicacionResponse> listar(@RequestParam(defaultValue = "") String criterio, Authentication auth) { return servicio.listar(usuario(auth), criterio, false); }
    @GetMapping("/me") public List<PublicacionResponse> listarPropias(@RequestParam(defaultValue = "") String criterio, Authentication auth) { return servicio.listar(usuario(auth), criterio, true); }
    @GetMapping("/{id}") public PublicacionResponse obtener(@PathVariable Long id, Authentication auth) { return servicio.obtener(usuario(auth), id, false); }
    @GetMapping("/me/{id}") public PublicacionResponse obtenerPropia(@PathVariable Long id, Authentication auth) { return servicio.obtener(usuario(auth), id, true); }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PublicacionResponse crear(@RequestPart("datos") PublicacionRequest datos, @RequestPart(value = "archivo", required = false) MultipartFile archivo, Authentication auth) {
        return servicio.crear(usuario(auth), datos, archivo);
    }
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PublicacionResponse actualizar(@PathVariable Long id, @RequestPart("datos") PublicacionRequest datos, @RequestPart(value = "archivo", required = false) MultipartFile archivo, Authentication auth) {
        return servicio.actualizar(usuario(auth), id, datos, archivo);
    }
    @PostMapping("/{id}/respuestas")
    public PublicacionResponse responder(@PathVariable Long id, @RequestBody RespuestaPublicacionRequest datos, Authentication auth) { return servicio.responder(usuario(auth), id, datos); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void retirar(@PathVariable Long id, @RequestBody MotivoRequest motivo, Authentication auth) { servicio.retirar(usuario(auth), id, motivo.motivo()); }
    @GetMapping("/{publicacionId}/archivos/{archivoId}")
    public ResponseEntity<byte[]> descargar(@PathVariable Long publicacionId, @PathVariable Long archivoId, Authentication auth) {
        ArchivoPublicacion archivo = servicio.descargar(usuario(auth), publicacionId, archivoId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(archivo.getTipoContenido()))
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(archivo.getNombre(), StandardCharsets.UTF_8).build().toString())
                .body(archivo.getContenido());
    }
    private String usuario(Authentication auth) { return sesion.obtenerSesionActual(auth).usuario(); }
}
