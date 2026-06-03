package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.EliminacionPerfilResponse;
import es.funiber.investigacion.dto.PerfilResponse;
import es.funiber.investigacion.dto.PerfilUpdateRequest;
import es.funiber.investigacion.dto.SolicitudEliminacionPerfilResponse;
import es.funiber.investigacion.dto.SolicitudEliminacionRequest;
import es.funiber.investigacion.exception.AccesoNoPermitidoException;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.exception.SolicitudDuplicadaException;
import es.funiber.investigacion.model.EstadoSolicitudEliminacion;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.SolicitudEliminacionPerfil;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.SolicitudEliminacionPerfilRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilService {

    private final UsuarioRepository usuarioRepository;
    private final SolicitudEliminacionPerfilRepository solicitudRepository;

    public PerfilService(
            UsuarioRepository usuarioRepository,
            SolicitudEliminacionPerfilRepository solicitudRepository) {
        this.usuarioRepository = usuarioRepository;
        this.solicitudRepository = solicitudRepository;
    }

    @Transactional(readOnly = true)
    public PerfilResponse obtenerPerfilPropio(String nombreUsuario) {
        return PerfilResponse.desde(buscarUsuarioActivo(nombreUsuario));
    }

    @Transactional(readOnly = true)
    public PerfilResponse obtenerPerfilPorId(String nombreUsuario, Long perfilId) {
        Usuario solicitante = buscarUsuarioActivo(nombreUsuario);
        if (solicitante.getRol() != Rol.COORDINADOR && !solicitante.getId().equals(perfilId)) {
            throw new AccesoNoPermitidoException();
        }
        return PerfilResponse.desde(buscarUsuarioActivo(perfilId));
    }

    @Transactional
    public PerfilResponse actualizarPerfilPropio(String nombreUsuario, PerfilUpdateRequest request) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        return actualizar(usuario, request);
    }

    @Transactional
    public PerfilResponse actualizarPerfilPorId(String nombreUsuario, Long perfilId, PerfilUpdateRequest request) {
        Usuario solicitante = buscarUsuarioActivo(nombreUsuario);
        if (solicitante.getRol() != Rol.COORDINADOR && !solicitante.getId().equals(perfilId)) {
            throw new AccesoNoPermitidoException();
        }
        return actualizar(buscarUsuarioActivo(perfilId), request);
    }

    @Transactional
    public SolicitudEliminacionPerfilResponse solicitarEliminacionPerfil(
            String nombreUsuario,
            SolicitudEliminacionRequest request) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        if (solicitudRepository.existsByUsuarioAndEstado(usuario, EstadoSolicitudEliminacion.PENDIENTE)) {
            throw new SolicitudDuplicadaException();
        }
        SolicitudEliminacionPerfil solicitud =
                solicitudRepository.save(new SolicitudEliminacionPerfil(usuario, request.motivo().trim()));
        return SolicitudEliminacionPerfilResponse.desde(solicitud);
    }

    @Transactional(readOnly = true)
    public List<SolicitudEliminacionPerfilResponse> listarSolicitudesPendientes(
            String nombreUsuario,
            String criterio) {
        exigirCoordinador(nombreUsuario);
        String filtro = criterio == null ? "" : criterio.trim().toLowerCase(Locale.ROOT);
        return solicitudRepository.findByEstadoOrderByFechaCreacionDesc(EstadoSolicitudEliminacion.PENDIENTE)
                .stream()
                .filter(solicitud -> filtro.isBlank() || coincide(solicitud, filtro))
                .map(SolicitudEliminacionPerfilResponse::desde)
                .toList();
    }

    @Transactional(readOnly = true)
    public SolicitudEliminacionPerfilResponse obtenerSolicitud(String nombreUsuario, Long solicitudId) {
        exigirCoordinador(nombreUsuario);
        return SolicitudEliminacionPerfilResponse.desde(buscarSolicitudPendiente(solicitudId));
    }

    @Transactional
    public EliminacionPerfilResponse eliminarPerfilDesdeSolicitud(String nombreUsuario, Long solicitudId) {
        exigirCoordinador(nombreUsuario);
        SolicitudEliminacionPerfil solicitud = buscarSolicitudPendiente(solicitudId);
        Usuario perfil = solicitud.getUsuario();
        perfil.desactivar();
        solicitud.marcarResuelta();
        usuarioRepository.save(perfil);
        solicitudRepository.save(solicitud);
        return new EliminacionPerfilResponse(solicitud.getId(), perfil.getId(), "RESUELTA");
    }

    private PerfilResponse actualizar(Usuario usuario, PerfilUpdateRequest request) {
        usuario.actualizarPerfil(
                request.nombreCompleto().trim(),
                normalizar(request.email()),
                normalizar(request.unidad()),
                normalizar(request.lineaInvestigacion()),
                normalizar(request.biografia()));
        return PerfilResponse.desde(usuarioRepository.save(usuario));
    }

    private void exigirCoordinador(String nombreUsuario) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        if (usuario.getRol() != Rol.COORDINADOR) {
            throw new AccesoNoPermitidoException();
        }
    }

    private Usuario buscarUsuarioActivo(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el perfil solicitado."));
        if (!usuario.isActivo()) {
            throw new RecursoNoEncontradoException("No se encontró el perfil solicitado.");
        }
        return usuario;
    }

    private Usuario buscarUsuarioActivo(Long perfilId) {
        Usuario usuario = usuarioRepository.findById(perfilId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el perfil solicitado."));
        if (!usuario.isActivo()) {
            throw new RecursoNoEncontradoException("No se encontró el perfil solicitado.");
        }
        return usuario;
    }

    private SolicitudEliminacionPerfil buscarSolicitudPendiente(Long solicitudId) {
        SolicitudEliminacionPerfil solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró la solicitud solicitada."));
        if (solicitud.getEstado() != EstadoSolicitudEliminacion.PENDIENTE) {
            throw new RecursoNoEncontradoException("No se encontró la solicitud solicitada.");
        }
        return solicitud;
    }

    private boolean coincide(SolicitudEliminacionPerfil solicitud, String filtro) {
        Usuario usuario = solicitud.getUsuario();
        return contiene(usuario.getNombreUsuario(), filtro)
                || contiene(usuario.getNombreCompleto(), filtro)
                || contiene(solicitud.getMotivo(), filtro);
    }

    private boolean contiene(String valor, String filtro) {
        return valor != null && valor.toLowerCase(Locale.ROOT).contains(filtro);
    }

    private String normalizar(String valor) {
        return valor == null || valor.isBlank() ? "" : valor.trim();
    }
}
