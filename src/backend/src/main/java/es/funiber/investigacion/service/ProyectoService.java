package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.InvestigadorProyectoResponse;
import es.funiber.investigacion.dto.ProyectoRequest;
import es.funiber.investigacion.dto.ProyectoResponse;
import es.funiber.investigacion.exception.AccesoNoPermitidoException;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.exception.SolicitudDuplicadaException;
import es.funiber.investigacion.model.MovimientoParticipacionProyecto;
import es.funiber.investigacion.model.Proyecto;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.TipoMovimientoParticipacion;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.MovimientoParticipacionProyectoRepository;
import es.funiber.investigacion.repository.CargaTrabajoRepository;
import es.funiber.investigacion.repository.ProyectoRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;
    private final MovimientoParticipacionProyectoRepository movimientoRepository;
    private final CargaTrabajoRepository cargaTrabajoRepository;

    public ProyectoService(
            ProyectoRepository proyectoRepository,
            UsuarioRepository usuarioRepository,
            MovimientoParticipacionProyectoRepository movimientoRepository,
            CargaTrabajoRepository cargaTrabajoRepository) {
        this.proyectoRepository = proyectoRepository;
        this.usuarioRepository = usuarioRepository;
        this.movimientoRepository = movimientoRepository;
        this.cargaTrabajoRepository = cargaTrabajoRepository;
    }

    @Transactional(readOnly = true)
    public List<ProyectoResponse> listar(String nombreUsuario, boolean archivados, String criterio) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        List<Proyecto> proyectos = usuario.getRol() == Rol.COORDINADOR
                ? proyectoRepository.findByArchivadoOrderByNombreAsc(archivados)
                : proyectoRepository.findDistinctByInvestigadoresIdOrderByNombreAsc(usuario.getId());
        return filtrar(proyectos, criterio);
    }

    @Transactional(readOnly = true)
    public ProyectoResponse obtener(String nombreUsuario, Long id) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        Proyecto proyecto = buscarProyecto(id);
        if (usuario.getRol() != Rol.COORDINADOR && !proyecto.participa(usuario)) {
            throw new AccesoNoPermitidoException();
        }
        return ProyectoResponse.desde(proyecto);
    }

    @Transactional
    public ProyectoResponse crear(String nombreUsuario, ProyectoRequest request) {
        Usuario coordinador = exigirCoordinador(nombreUsuario);
        validar(request);
        if (proyectoRepository.findByCodigo(request.codigo().trim()).isPresent()) {
            throw new SolicitudDuplicadaException("Ya existe un proyecto con ese codigo.");
        }
        Proyecto proyecto = new Proyecto(request.codigo().trim(), request.nombre().trim(), request.estado());
        actualizar(proyecto, request, coordinador);
        archivarSiCompletado(proyecto, coordinador);
        return ProyectoResponse.desde(proyectoRepository.save(proyecto));
    }

    @Transactional
    public ProyectoResponse actualizar(String nombreUsuario, Long id, ProyectoRequest request) {
        Usuario coordinador = exigirCoordinador(nombreUsuario);
        Proyecto proyecto = buscarProyectoActivo(id);
        validar(request);
        proyectoRepository.findByCodigo(request.codigo().trim())
                .filter(existente -> !existente.getId().equals(id))
                .ifPresent(existente -> {
                    throw new SolicitudDuplicadaException("Ya existe un proyecto con ese codigo.");
                });
        actualizar(proyecto, request, coordinador);
        archivarSiCompletado(proyecto, coordinador);
        return ProyectoResponse.desde(proyecto);
    }

    @Transactional
    public ProyectoResponse archivar(String nombreUsuario, Long id, String motivo) {
        Usuario coordinador = exigirCoordinador(nombreUsuario);
        Proyecto proyecto = buscarProyectoActivo(id);
        proyecto.archivar(coordinador, motivo.trim());
        return ProyectoResponse.desde(proyecto);
    }

    @Transactional(readOnly = true)
    public List<InvestigadorProyectoResponse> listarCandidatos(String nombreUsuario, Long proyectoId) {
        exigirCoordinador(nombreUsuario);
        Proyecto proyecto = buscarProyectoActivo(proyectoId);
        return usuarioRepository.findByRolAndActivoTrueOrderByNombreCompletoAsc(Rol.INVESTIGADOR).stream()
                .filter(usuario -> !proyecto.participa(usuario))
                .filter(usuario -> proyecto.getSede() == null
                        || proyecto.getSede().name().equals("GLOBAL")
                        || proyecto.getSede() == usuario.getSede())
                .map(usuario -> InvestigadorProyectoResponse.desde(usuario, cargaSemanal(usuario)))
                .sorted((a, b) -> {
                    int carga = a.cargaSemanal().compareTo(b.cargaSemanal());
                    return carga != 0 ? carga : a.nombreCompleto().compareToIgnoreCase(b.nombreCompleto());
                })
                .toList();
    }

    @Transactional
    public ProyectoResponse agregarInvestigador(String nombreUsuario, Long proyectoId, Long investigadorId) {
        Usuario coordinador = exigirCoordinador(nombreUsuario);
        Proyecto proyecto = buscarProyectoActivo(proyectoId);
        Usuario investigador = buscarInvestigadorActivo(investigadorId);
        if (proyecto.participa(investigador)) {
            throw new SolicitudDuplicadaException("El investigador ya participa en el proyecto.");
        }
        if (proyecto.getSede() != null
                && !proyecto.getSede().name().equals("GLOBAL")
                && proyecto.getSede() != investigador.getSede()) {
            throw new IllegalArgumentException("El investigador no pertenece a una sede compatible con el proyecto.");
        }
        proyecto.agregarInvestigador(investigador);
        movimientoRepository.save(new MovimientoParticipacionProyecto(
                proyecto, investigador, TipoMovimientoParticipacion.ASIGNACION, coordinador, "Asignacion al proyecto"));
        return ProyectoResponse.desde(proyecto);
    }

    @Transactional
    public ProyectoResponse retirarInvestigador(
            String nombreUsuario,
            Long proyectoId,
            Long investigadorId,
            String motivo) {
        Usuario coordinador = exigirCoordinador(nombreUsuario);
        Proyecto proyecto = buscarProyectoActivo(proyectoId);
        Usuario investigador = buscarInvestigadorActivo(investigadorId);
        if (!proyecto.participa(investigador)) {
            throw new RecursoNoEncontradoException("El investigador no participa actualmente en el proyecto.");
        }
        proyecto.retirarInvestigador(investigador);
        movimientoRepository.save(new MovimientoParticipacionProyecto(
                proyecto, investigador, TipoMovimientoParticipacion.DESASIGNACION, coordinador, motivo.trim()));
        return ProyectoResponse.desde(proyecto);
    }

    private List<ProyectoResponse> filtrar(List<Proyecto> proyectos, String criterio) {
        String filtro = criterio == null ? "" : criterio.trim().toLowerCase(Locale.ROOT);
        return proyectos.stream()
                .filter(proyecto -> filtro.isBlank()
                        || contiene(proyecto.getCodigo(), filtro)
                        || contiene(proyecto.getNombre(), filtro)
                        || contiene(proyecto.getDescripcion(), filtro)
                        || contiene(proyecto.getArea(), filtro))
                .map(ProyectoResponse::desde)
                .toList();
    }

    private void actualizar(Proyecto proyecto, ProyectoRequest request, Usuario coordinador) {
        proyecto.actualizar(
                request.codigo().trim(),
                request.nombre().trim(),
                limpiar(request.descripcion()),
                limpiar(request.area()),
                request.sede(),
                request.fechaInicio(),
                request.fechaFin(),
                request.estado(),
                coordinador);
    }

    private void archivarSiCompletado(Proyecto proyecto, Usuario coordinador) {
        if (proyecto.getEstado() == es.funiber.investigacion.model.EstadoProyecto.COMPLETADO
                && !proyecto.isArchivado()) {
            proyecto.archivar(coordinador, "Archivado automaticamente al marcar el proyecto como completado.");
        }
    }

    private void validar(ProyectoRequest request) {
        if (request.fechaInicio() != null && request.fechaFin() != null
                && request.fechaFin().isBefore(request.fechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
    }

    private Proyecto buscarProyecto(Long id) {
        return proyectoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el proyecto solicitado."));
    }

    private Proyecto buscarProyectoActivo(Long id) {
        Proyecto proyecto = buscarProyecto(id);
        if (proyecto.isArchivado()) {
            throw new IllegalArgumentException("El proyecto esta archivado y solo puede consultarse.");
        }
        return proyecto;
    }

    private Usuario buscarInvestigadorActivo(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el investigador solicitado."));
        if (!usuario.isActivo() || usuario.getRol() != Rol.INVESTIGADOR) {
            throw new RecursoNoEncontradoException("No se encontro el investigador solicitado.");
        }
        return usuario;
    }

    private Usuario exigirCoordinador(String nombreUsuario) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        if (usuario.getRol() != Rol.COORDINADOR) {
            throw new AccesoNoPermitidoException();
        }
        return usuario;
    }

    private Usuario buscarUsuarioActivo(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el perfil solicitado."));
        if (!usuario.isActivo()) {
            throw new RecursoNoEncontradoException("No se encontro el perfil solicitado.");
        }
        return usuario;
    }

    private String limpiar(String valor) {
        return valor == null ? "" : valor.trim();
    }

    private BigDecimal cargaSemanal(Usuario usuario) {
        return cargaTrabajoRepository.findByUsuario(usuario)
                .map(carga -> carga.totalSemanal())
                .orElse(BigDecimal.ZERO);
    }

    private boolean contiene(String valor, String filtro) {
        return valor != null && valor.toLowerCase(Locale.ROOT).contains(filtro);
    }
}
