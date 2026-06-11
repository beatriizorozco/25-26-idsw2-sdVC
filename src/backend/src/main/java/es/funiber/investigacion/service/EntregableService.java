package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.ArchivoEntregableResponse;
import es.funiber.investigacion.dto.EntregableRequest;
import es.funiber.investigacion.dto.EntregableResponse;
import es.funiber.investigacion.exception.AccesoNoPermitidoException;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.model.ArchivoEntregable;
import es.funiber.investigacion.model.Entregable;
import es.funiber.investigacion.model.Proyecto;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.ArchivoEntregableRepository;
import es.funiber.investigacion.repository.EntregableRepository;
import es.funiber.investigacion.repository.ProyectoRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EntregableService {

    private static final long TAMANO_MAXIMO = 15L * 1024 * 1024;
    private static final Set<String> ESTADOS_PERMITIDOS = Set.of("PRESENTADO", "EN_REVISION", "ACEPTADO");

    private final EntregableRepository entregableRepository;
    private final ArchivoEntregableRepository archivoRepository;
    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;

    public EntregableService(
            EntregableRepository entregableRepository,
            ArchivoEntregableRepository archivoRepository,
            ProyectoRepository proyectoRepository,
            UsuarioRepository usuarioRepository) {
        this.entregableRepository = entregableRepository;
        this.archivoRepository = archivoRepository;
        this.proyectoRepository = proyectoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<EntregableResponse> listar(String nombreUsuario, Long proyectoId) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        buscarProyectoVisible(usuario, proyectoId);
        return entregableRepository.findByProyectoIdAndRetiradoOrderByFechaCreacionDesc(proyectoId, false).stream()
                .map(entregable -> respuesta(entregable, usuario))
                .toList();
    }

    @Transactional(readOnly = true)
    public EntregableResponse obtener(String nombreUsuario, Long id) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        Entregable entregable = buscarActivo(id);
        buscarProyectoVisible(usuario, entregable.getProyecto().getId());
        return respuesta(entregable, usuario);
    }

    @Transactional
    public EntregableResponse crear(
            String nombreUsuario,
            Long proyectoId,
            EntregableRequest datos,
            MultipartFile archivo) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        Proyecto proyecto = buscarProyectoVisible(usuario, proyectoId);
        if (proyecto.isArchivado()) {
            throw new IllegalArgumentException("No se pueden crear entregables en un proyecto archivado.");
        }
        validar(datos);
        Entregable entregable = entregableRepository.save(
                new Entregable(proyecto, datos.titulo().trim(), limpiar(datos.descripcion()), usuario));
        guardarVersionOpcional(entregable, archivo, usuario);
        return respuesta(entregable, usuario);
    }

    @Transactional
    public EntregableResponse actualizar(
            String nombreUsuario,
            Long id,
            EntregableRequest datos,
            MultipartFile archivo) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        Entregable entregable = buscarActivo(id);
        Proyecto proyecto = buscarProyectoVisible(usuario, entregable.getProyecto().getId());
        exigirProyectoActivo(proyecto);
        exigirGestion(usuario, entregable);
        validar(datos);
        entregable.actualizar(
                datos.titulo().trim(),
                limpiar(datos.descripcion()),
                datos.estado() == null || datos.estado().isBlank() ? "PRESENTADO" : datos.estado().trim());
        guardarVersionOpcional(entregable, archivo, usuario);
        return respuesta(entregable, usuario);
    }

    @Transactional
    public EntregableResponse retirar(String nombreUsuario, Long id, String motivo) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        Entregable entregable = buscarActivo(id);
        Proyecto proyecto = buscarProyectoVisible(usuario, entregable.getProyecto().getId());
        exigirProyectoActivo(proyecto);
        exigirGestion(usuario, entregable);
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("Debe indicarse el motivo de retirada.");
        }
        entregable.retirar(usuario, motivo.trim());
        return respuesta(entregable, usuario);
    }

    @Transactional(readOnly = true)
    public ArchivoEntregable descargar(String nombreUsuario, Long entregableId, Long archivoId) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        Entregable entregable = buscarActivo(entregableId);
        buscarProyectoVisible(usuario, entregable.getProyecto().getId());
        ArchivoEntregable archivo = archivoRepository.findById(archivoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro la version solicitada."));
        if (!archivo.getEntregable().getId().equals(entregableId)) {
            throw new RecursoNoEncontradoException("No se encontro la version solicitada.");
        }
        return archivo;
    }

    private void guardarVersionOpcional(Entregable entregable, MultipartFile fichero, Usuario usuario) {
        if (fichero == null || fichero.isEmpty()) {
            return;
        }
        if (fichero.getSize() > TAMANO_MAXIMO) {
            throw new IllegalArgumentException("El archivo no puede superar los 15 MB.");
        }
        int version = archivoRepository.findFirstByEntregableIdOrderByVersionDesc(entregable.getId())
                .map(ArchivoEntregable::getVersion)
                .orElse(0) + 1;
        try {
            String tipo = fichero.getContentType() == null ? "application/octet-stream" : fichero.getContentType();
            archivoRepository.save(new ArchivoEntregable(
                    entregable,
                    version,
                    limpiarNombre(fichero.getOriginalFilename()),
                    tipo,
                    fichero.getBytes(),
                    usuario));
        } catch (IOException exception) {
            throw new IllegalArgumentException("No se pudo leer el archivo seleccionado.");
        }
    }

    private EntregableResponse respuesta(Entregable entregable, Usuario usuario) {
        List<ArchivoEntregableResponse> archivos = archivoRepository
                .findByEntregableIdOrderByVersionDesc(entregable.getId()).stream()
                .map(ArchivoEntregableResponse::desde)
                .toList();
        return EntregableResponse.desde(entregable, usuario.getId(), archivos);
    }

    private void exigirGestion(Usuario usuario, Entregable entregable) {
        if (usuario.getRol() != Rol.COORDINADOR && !entregable.getAutor().getId().equals(usuario.getId())) {
            throw new AccesoNoPermitidoException();
        }
    }

    private Proyecto buscarProyectoVisible(Usuario usuario, Long proyectoId) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el proyecto solicitado."));
        if (usuario.getRol() != Rol.COORDINADOR && !proyecto.participa(usuario)) {
            throw new AccesoNoPermitidoException();
        }
        return proyecto;
    }

    private Entregable buscarActivo(Long id) {
        return entregableRepository.findByIdAndRetirado(id, false)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el entregable solicitado."));
    }

    private Usuario buscarUsuarioActivo(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el perfil solicitado."));
        if (!usuario.isActivo()) {
            throw new RecursoNoEncontradoException("No se encontro el perfil solicitado.");
        }
        return usuario;
    }

    private void validar(EntregableRequest datos) {
        if (datos == null || datos.titulo() == null || datos.titulo().isBlank()) {
            throw new IllegalArgumentException("El titulo del entregable es obligatorio.");
        }
        if (datos.titulo().trim().length() > 180) {
            throw new IllegalArgumentException("El titulo no puede superar los 180 caracteres.");
        }
        if (datos.descripcion() != null && datos.descripcion().trim().length() > 1000) {
            throw new IllegalArgumentException("La descripcion no puede superar los 1000 caracteres.");
        }
        if (datos.estado() != null && !datos.estado().isBlank() && !ESTADOS_PERMITIDOS.contains(datos.estado().trim())) {
            throw new IllegalArgumentException("El estado del entregable no es valido.");
        }
    }

    private void exigirProyectoActivo(Proyecto proyecto) {
        if (proyecto.isArchivado()) {
            throw new IllegalArgumentException("No se pueden modificar entregables de un proyecto archivado.");
        }
    }

    private String limpiar(String valor) {
        return valor == null || valor.isBlank() ? null : valor.trim();
    }

    private String limpiarNombre(String original) {
        String nombre = original == null ? "archivo" : original.replace("\\", "/");
        nombre = nombre.substring(nombre.lastIndexOf('/') + 1).trim();
        return nombre.isBlank() ? "archivo" : nombre.substring(0, Math.min(nombre.length(), 240));
    }
}
