package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.ArchivoProyectoResponse;
import es.funiber.investigacion.exception.AccesoNoPermitidoException;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.model.ArchivoProyecto;
import es.funiber.investigacion.model.Proyecto;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.ArchivoProyectoRepository;
import es.funiber.investigacion.repository.ProyectoRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArchivoProyectoService {

    private static final long TAMANO_MAXIMO = 15L * 1024 * 1024;

    private final ArchivoProyectoRepository archivoRepository;
    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;

    public ArchivoProyectoService(
            ArchivoProyectoRepository archivoRepository,
            ProyectoRepository proyectoRepository,
            UsuarioRepository usuarioRepository) {
        this.archivoRepository = archivoRepository;
        this.proyectoRepository = proyectoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<ArchivoProyectoResponse> listar(String nombreUsuario, Long proyectoId) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        Proyecto proyecto = buscarProyectoVisible(usuario, proyectoId);
        return archivoRepository.findByProyectoOrderByFechaSubidaDesc(proyecto).stream()
                .map(ArchivoProyectoResponse::desde)
                .toList();
    }

    @Transactional
    public ArchivoProyectoResponse subir(String nombreUsuario, Long proyectoId, MultipartFile fichero) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        Proyecto proyecto = buscarProyectoVisible(usuario, proyectoId);
        if (fichero.isEmpty()) {
            throw new IllegalArgumentException("Debe seleccionarse un archivo.");
        }
        if (fichero.getSize() > TAMANO_MAXIMO) {
            throw new IllegalArgumentException("El archivo no puede superar los 15 MB.");
        }
        try {
            String nombre = limpiarNombre(fichero.getOriginalFilename());
            String tipo = fichero.getContentType() == null ? "application/octet-stream" : fichero.getContentType();
            return ArchivoProyectoResponse.desde(archivoRepository.save(
                    new ArchivoProyecto(proyecto, nombre, tipo, usuario, fichero.getBytes())));
        } catch (IOException exception) {
            throw new IllegalArgumentException("No se pudo leer el archivo seleccionado.");
        }
    }

    @Transactional(readOnly = true)
    public ArchivoProyecto descargar(String nombreUsuario, Long proyectoId, Long archivoId) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        Proyecto proyecto = buscarProyectoVisible(usuario, proyectoId);
        ArchivoProyecto archivo = buscarArchivo(archivoId);
        if (!archivo.getProyecto().getId().equals(proyecto.getId())) {
            throw new RecursoNoEncontradoException("No se encontro el archivo solicitado.");
        }
        return archivo;
    }

    @Transactional
    public void eliminar(String nombreUsuario, Long proyectoId, Long archivoId) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        if (usuario.getRol() != Rol.COORDINADOR) {
            throw new AccesoNoPermitidoException();
        }
        Proyecto proyecto = buscarProyecto(proyectoId);
        ArchivoProyecto archivo = buscarArchivo(archivoId);
        if (!archivo.getProyecto().getId().equals(proyecto.getId())) {
            throw new RecursoNoEncontradoException("No se encontro el archivo solicitado.");
        }
        archivoRepository.delete(archivo);
    }

    private Proyecto buscarProyectoVisible(Usuario usuario, Long proyectoId) {
        Proyecto proyecto = buscarProyecto(proyectoId);
        if (usuario.getRol() != Rol.COORDINADOR && !proyecto.participa(usuario)) {
            throw new AccesoNoPermitidoException();
        }
        return proyecto;
    }

    private Proyecto buscarProyecto(Long id) {
        return proyectoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el proyecto solicitado."));
    }

    private ArchivoProyecto buscarArchivo(Long id) {
        return archivoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el archivo solicitado."));
    }

    private Usuario buscarUsuarioActivo(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el perfil solicitado."));
        if (!usuario.isActivo()) {
            throw new RecursoNoEncontradoException("No se encontro el perfil solicitado.");
        }
        return usuario;
    }

    private String limpiarNombre(String nombreOriginal) {
        String nombre = nombreOriginal == null ? "archivo" : nombreOriginal.replace("\\", "/");
        nombre = nombre.substring(nombre.lastIndexOf('/') + 1).trim();
        return nombre.isBlank() ? "archivo" : nombre.substring(0, Math.min(nombre.length(), 240));
    }
}
