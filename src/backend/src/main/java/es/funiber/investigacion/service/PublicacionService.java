package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.*;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.model.*;
import es.funiber.investigacion.repository.*;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PublicacionService {
    private final PublicacionRepository publicaciones;
    private final RespuestaPublicacionRepository respuestas;
    private final ArchivoPublicacionRepository archivos;
    private final PoliticaPublicacion politica;
    private final AccesoUsuarioService accesoUsuarios;
    private final ProcesadorArchivoService procesadorArchivos;

    public PublicacionService(PublicacionRepository publicaciones, RespuestaPublicacionRepository respuestas,
            ArchivoPublicacionRepository archivos, PoliticaPublicacion politica, AccesoUsuarioService accesoUsuarios,
            ProcesadorArchivoService procesadorArchivos) {
        this.publicaciones = publicaciones; this.respuestas = respuestas; this.archivos = archivos;
        this.politica = politica; this.accesoUsuarios = accesoUsuarios; this.procesadorArchivos = procesadorArchivos;
    }

    @Transactional(readOnly = true)
    public List<PublicacionResponse> listar(String nombreUsuario, String criterio, boolean propias) {
        Usuario actor = accesoUsuarios.buscarActivo(nombreUsuario);
        List<Publicacion> lista = propias
                ? publicaciones.findByAutorAndRetiradaOrderByFechaActualizacionDesc(actor, false)
                : publicaciones.findByRetiradaOrderByFechaActualizacionDesc(false);
        String filtro = criterio == null ? "" : criterio.trim().toLowerCase();
        return lista.stream().filter(p -> filtro.isBlank() || p.getTitulo().toLowerCase().contains(filtro)
                || p.getContenido().toLowerCase().contains(filtro)
                || p.getAutor().getNombreCompleto().toLowerCase().contains(filtro))
                .map(p -> resumen(p, actor)).toList();
    }

    @Transactional(readOnly = true)
    public PublicacionResponse obtener(String nombreUsuario, Long id, boolean propia) {
        Usuario actor = accesoUsuarios.buscarActivo(nombreUsuario);
        Publicacion publicacion = activa(id);
        if (propia) politica.exigirAutoria(actor, publicacion);
        return detalle(publicacion, actor);
    }

    @Transactional
    public PublicacionResponse crear(String nombreUsuario, PublicacionRequest datos, MultipartFile archivo) {
        Usuario actor = accesoUsuarios.buscarActivo(nombreUsuario); validar(datos);
        Publicacion publicacion = publicaciones.save(new Publicacion(datos.titulo().trim(), datos.contenido().trim(), actor));
        guardarArchivo(publicacion, archivo, actor);
        return detalle(publicacion, actor);
    }

    @Transactional
    public PublicacionResponse actualizar(String nombreUsuario, Long id, PublicacionRequest datos, MultipartFile archivo) {
        Usuario actor = accesoUsuarios.buscarActivo(nombreUsuario); Publicacion publicacion = activa(id);
        politica.exigirGestion(actor, publicacion); validar(datos);
        publicacion.actualizar(datos.titulo().trim(), datos.contenido().trim());
        guardarArchivo(publicacion, archivo, actor);
        return detalle(publicacion, actor);
    }

    @Transactional
    public PublicacionResponse responder(String nombreUsuario, Long id, RespuestaPublicacionRequest datos) {
        Usuario actor = accesoUsuarios.buscarActivo(nombreUsuario); Publicacion publicacion = activa(id);
        if (datos == null || datos.contenido() == null || datos.contenido().isBlank()) {
            throw new IllegalArgumentException("La respuesta no puede estar vacia.");
        }
        if (datos.contenido().trim().length() > 1500) throw new IllegalArgumentException("La respuesta es demasiado larga.");
        respuestas.save(new RespuestaPublicacion(publicacion, actor, datos.contenido().trim()));
        return detalle(publicacion, actor);
    }

    @Transactional
    public void retirar(String nombreUsuario, Long id, String motivo) {
        Usuario actor = accesoUsuarios.buscarActivo(nombreUsuario); Publicacion publicacion = activa(id);
        politica.exigirGestion(actor, publicacion);
        if (motivo == null || motivo.isBlank()) throw new IllegalArgumentException("Debe indicarse el motivo de retirada.");
        publicacion.retirar(actor, motivo.trim());
    }

    @Transactional(readOnly = true)
    public ArchivoPublicacion descargar(String nombreUsuario, Long publicacionId, Long archivoId) {
        accesoUsuarios.buscarActivo(nombreUsuario); activa(publicacionId);
        ArchivoPublicacion archivo = archivos.findById(archivoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el archivo solicitado."));
        if (!archivo.getPublicacion().getId().equals(publicacionId)) throw new RecursoNoEncontradoException("No se encontro el archivo solicitado.");
        return archivo;
    }

    private PublicacionResponse resumen(Publicacion p, Usuario actor) { return PublicacionResponse.desde(p, actor.getId(), List.of(), List.of()); }
    private PublicacionResponse detalle(Publicacion p, Usuario actor) {
        return PublicacionResponse.desde(p, actor.getId(),
                respuestas.findByPublicacionIdOrderByFecha(p.getId()).stream().map(RespuestaPublicacionResponse::desde).toList(),
                archivos.findByPublicacionIdOrderByFechaSubidaDesc(p.getId()).stream().map(ArchivoPublicacionResponse::desde).toList());
    }
    private Publicacion activa(Long id) { return publicaciones.findByIdAndRetirada(id, false).orElseThrow(() -> new RecursoNoEncontradoException("No se encontro la publicacion solicitada.")); }
    private void validar(PublicacionRequest d) {
        if (d == null || d.titulo() == null || d.titulo().isBlank() || d.contenido() == null || d.contenido().isBlank()) throw new IllegalArgumentException("Titulo y contenido son obligatorios.");
        if (d.titulo().trim().length() > 180 || d.contenido().trim().length() > 4000) throw new IllegalArgumentException("La publicacion supera la longitud permitida.");
    }
    private void guardarArchivo(Publicacion p, MultipartFile f, Usuario actor) {
        if (f == null || f.isEmpty()) return;
        ContenidoArchivo contenido = procesadorArchivos.procesar(f);
        archivos.save(new ArchivoPublicacion(
                p, contenido.nombre(), contenido.tipo(), contenido.bytes(), actor));
    }
}
