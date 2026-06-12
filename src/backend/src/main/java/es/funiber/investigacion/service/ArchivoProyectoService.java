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
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArchivoProyectoService {

    private final ArchivoProyectoRepository archivoRepository;
    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AccesoUsuarioService accesoUsuarios;
    private final ProcesadorArchivoService procesadorArchivos;

    public ArchivoProyectoService(
            ArchivoProyectoRepository archivoRepository,
            ProyectoRepository proyectoRepository,
            UsuarioRepository usuarioRepository,
            AccesoUsuarioService accesoUsuarios,
            ProcesadorArchivoService procesadorArchivos) {
        this.archivoRepository = archivoRepository;
        this.proyectoRepository = proyectoRepository;
        this.usuarioRepository = usuarioRepository;
        this.accesoUsuarios = accesoUsuarios;
        this.procesadorArchivos = procesadorArchivos;
    }

    @Transactional(readOnly = true)
    public List<ArchivoProyectoResponse> listar(String nombreUsuario, Long proyectoId) {
        Usuario usuario = accesoUsuarios.buscarActivo(nombreUsuario);
        Proyecto proyecto = buscarProyectoVisible(usuario, proyectoId);
        return archivoRepository.findByProyectoOrderByFechaSubidaDesc(proyecto).stream()
                .map(ArchivoProyectoResponse::desde)
                .toList();
    }

    @Transactional
    public ArchivoProyectoResponse subir(String nombreUsuario, Long proyectoId, MultipartFile fichero) {
        Usuario usuario = accesoUsuarios.buscarActivo(nombreUsuario);
        Proyecto proyecto = buscarProyectoVisible(usuario, proyectoId);
        ContenidoArchivo contenido = procesadorArchivos.procesarObligatorio(fichero);
        return ArchivoProyectoResponse.desde(archivoRepository.save(
                new ArchivoProyecto(proyecto, contenido.nombre(), contenido.tipo(), usuario, contenido.bytes())));
    }

    @Transactional(readOnly = true)
    public ArchivoProyecto descargar(String nombreUsuario, Long proyectoId, Long archivoId) {
        Usuario usuario = accesoUsuarios.buscarActivo(nombreUsuario);
        Proyecto proyecto = buscarProyectoVisible(usuario, proyectoId);
        ArchivoProyecto archivo = buscarArchivo(archivoId);
        if (!archivo.getProyecto().getId().equals(proyecto.getId())) {
            throw new RecursoNoEncontradoException("No se encontro el archivo solicitado.");
        }
        return archivo;
    }

    @Transactional
    public void eliminar(String nombreUsuario, Long proyectoId, Long archivoId) {
        Usuario usuario = accesoUsuarios.exigirRol(nombreUsuario, Rol.COORDINADOR);
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

}
