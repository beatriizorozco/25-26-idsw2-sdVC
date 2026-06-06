package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.BeneficiarioRecompensaResponse;
import es.funiber.investigacion.dto.OpcionesCreacionRecompensaResponse;
import es.funiber.investigacion.dto.ProyectoRecompensaResponse;
import es.funiber.investigacion.dto.RecompensaEdicionResponse;
import es.funiber.investigacion.dto.RecompensaRequest;
import es.funiber.investigacion.dto.RecompensaResponse;
import es.funiber.investigacion.exception.AccesoNoPermitidoException;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.exception.SolicitudDuplicadaException;
import es.funiber.investigacion.model.EstadoProyecto;
import es.funiber.investigacion.model.Proyecto;
import es.funiber.investigacion.model.Recompensa;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.TipoRecompensa;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.ProyectoRepository;
import es.funiber.investigacion.repository.RecompensaRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecompensaService {

    private static final BigDecimal HORAS_POR_ASIGNATURA = new BigDecimal("4");
    private static final BigDecimal MAXIMA_REDUCCION_DOCENTE = new BigDecimal("16");

    private final RecompensaRepository recompensaRepository;
    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;

    public RecompensaService(
            RecompensaRepository recompensaRepository,
            ProyectoRepository proyectoRepository,
            UsuarioRepository usuarioRepository) {
        this.recompensaRepository = recompensaRepository;
        this.proyectoRepository = proyectoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<RecompensaResponse> listarGlobales(String nombreUsuario, String criterio) {
        exigirCoordinador(nombreUsuario);
        return filtrar(recompensaRepository.findAllByOrderByFechaCreacionDesc(), criterio);
    }

    @Transactional(readOnly = true)
    public List<RecompensaResponse> listarPropias(String nombreUsuario, String criterio) {
        Usuario investigador = exigirInvestigador(nombreUsuario);
        return filtrar(recompensaRepository.findByBeneficiarioOrderByFechaCreacionDesc(investigador), criterio);
    }

    @Transactional(readOnly = true)
    public RecompensaResponse obtenerGlobal(String nombreUsuario, Long id) {
        exigirCoordinador(nombreUsuario);
        return RecompensaResponse.desde(buscarRecompensa(id));
    }

    @Transactional(readOnly = true)
    public RecompensaResponse obtenerPropia(String nombreUsuario, Long id) {
        Usuario investigador = exigirInvestigador(nombreUsuario);
        Recompensa recompensa = buscarRecompensa(id);
        if (!recompensa.getBeneficiario().getId().equals(investigador.getId())) {
            throw new AccesoNoPermitidoException();
        }
        return RecompensaResponse.desde(recompensa);
    }

    @Transactional(readOnly = true)
    public OpcionesCreacionRecompensaResponse prepararCreacion(String nombreUsuario) {
        exigirCoordinador(nombreUsuario);
        return new OpcionesCreacionRecompensaResponse(
                proyectoRepository.findByEstadoOrderByNombreAsc(EstadoProyecto.COMPLETADO)
                        .stream()
                        .filter(this::tieneRecompensasPendientes)
                        .map(ProyectoRecompensaResponse::desde)
                        .toList());
    }

    @Transactional(readOnly = true)
    public List<BeneficiarioRecompensaResponse> obtenerBeneficiariosElegibles(
            String nombreUsuario,
            Long proyectoId) {
        exigirCoordinador(nombreUsuario);
        Proyecto proyecto = buscarProyectoCompletado(proyectoId);
        return proyecto.getInvestigadores().stream()
                .filter(Usuario::isActivo)
                .filter(usuario -> usuario.getRol() == Rol.INVESTIGADOR)
                .map(usuario -> mapearBeneficiarioPendiente(proyecto, usuario))
                .filter(beneficiario -> !beneficiario.tiposPermitidos().isEmpty())
                .sorted((primero, segundo) -> primero.nombreCompleto().compareToIgnoreCase(segundo.nombreCompleto()))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<String> obtenerTiposPermitidos(
            String nombreUsuario,
            Long proyectoId,
            Long investigadorId) {
        exigirCoordinador(nombreUsuario);
        Proyecto proyecto = buscarProyectoCompletado(proyectoId);
        return tiposPendientes(proyecto, buscarBeneficiarioElegible(proyecto, investigadorId));
    }

    @Transactional
    public RecompensaResponse crear(String nombreUsuario, RecompensaRequest request) {
        exigirCoordinador(nombreUsuario);
        if (request.proyectoId() == null) {
            throw new IllegalArgumentException("Debe seleccionarse un proyecto completado.");
        }
        Proyecto proyecto = buscarProyectoCompletado(request.proyectoId());
        Usuario beneficiario = buscarBeneficiarioElegible(proyecto, request.beneficiarioId());
        validarDatos(request, beneficiario);
        validarDuplicado(proyecto, beneficiario, request.tipo(), null);

        Recompensa recompensa = new Recompensa(
                proyecto,
                beneficiario,
                request.tipo(),
                request.concepto().trim(),
                request.valor());
        return RecompensaResponse.desde(guardarSinDuplicados(recompensa));
    }

    @Transactional(readOnly = true)
    public RecompensaEdicionResponse prepararEdicion(String nombreUsuario, Long id) {
        exigirCoordinador(nombreUsuario);
        Recompensa recompensa = buscarRecompensa(id);
        return new RecompensaEdicionResponse(
                RecompensaResponse.desde(recompensa),
                recompensa.getProyecto().getInvestigadores().stream()
                        .filter(Usuario::isActivo)
                        .filter(usuario -> usuario.getRol() == Rol.INVESTIGADOR)
                        .map(usuario -> mapearBeneficiarioEdicion(recompensa, usuario))
                        .filter(beneficiario -> !beneficiario.tiposPermitidos().isEmpty())
                        .toList());
    }

    @Transactional
    public RecompensaResponse actualizar(String nombreUsuario, Long id, RecompensaRequest request) {
        exigirCoordinador(nombreUsuario);
        Recompensa recompensa = buscarRecompensa(id);
        Proyecto proyecto = buscarProyectoCompletado(recompensa.getProyecto().getId());
        Usuario beneficiario = buscarBeneficiarioElegible(proyecto, request.beneficiarioId());
        validarDatos(request, beneficiario);
        validarDuplicado(proyecto, beneficiario, request.tipo(), id);
        recompensa.actualizar(beneficiario, request.tipo(), request.concepto().trim(), request.valor());
        return RecompensaResponse.desde(guardarSinDuplicados(recompensa));
    }

    @Transactional
    public void eliminar(String nombreUsuario, Long id) {
        exigirCoordinador(nombreUsuario);
        recompensaRepository.delete(buscarRecompensa(id));
    }

    private List<RecompensaResponse> filtrar(List<Recompensa> recompensas, String criterio) {
        String filtro = criterio == null ? "" : criterio.trim().toLowerCase(Locale.ROOT);
        return recompensas.stream()
                .filter(recompensa -> filtro.isBlank()
                        || contiene(recompensa.getProyecto().getCodigo(), filtro)
                        || contiene(recompensa.getProyecto().getNombre(), filtro)
                        || contiene(recompensa.getBeneficiario().getNombreCompleto(), filtro)
                        || contiene(recompensa.getBeneficiario().getNombreUsuario(), filtro)
                        || contiene(recompensa.getConcepto(), filtro)
                        || contiene(recompensa.getTipo().getEtiqueta(), filtro))
                .map(RecompensaResponse::desde)
                .toList();
    }

    private BeneficiarioRecompensaResponse mapearBeneficiarioPendiente(Proyecto proyecto, Usuario usuario) {
        return BeneficiarioRecompensaResponse.desde(usuario, tiposPendientes(proyecto, usuario));
    }

    private BeneficiarioRecompensaResponse mapearBeneficiarioEdicion(Recompensa recompensa, Usuario usuario) {
        return BeneficiarioRecompensaResponse.desde(usuario, tiposDisponiblesParaEdicion(recompensa, usuario));
    }

    private List<String> tiposPermitidos(Usuario beneficiario) {
        if (beneficiario.esInvestigadorDocente()) {
            return List.of(TipoRecompensa.ECONOMICA.name(), TipoRecompensa.REDUCCION_DOCENTE.name());
        }
        return List.of(TipoRecompensa.ECONOMICA.name());
    }

    private List<String> tiposPendientes(Proyecto proyecto, Usuario beneficiario) {
        return tiposPermitidos(beneficiario).stream()
                .filter(tipo -> !recompensaRepository.existsByProyectoAndBeneficiarioAndTipo(
                        proyecto,
                        beneficiario,
                        TipoRecompensa.valueOf(tipo)))
                .toList();
    }

    private List<String> tiposDisponiblesParaEdicion(Recompensa recompensa, Usuario beneficiario) {
        return tiposPermitidos(beneficiario).stream()
                .filter(tipo -> !recompensaRepository.existsByProyectoAndBeneficiarioAndTipoAndIdNot(
                        recompensa.getProyecto(),
                        beneficiario,
                        TipoRecompensa.valueOf(tipo),
                        recompensa.getId()))
                .toList();
    }

    private boolean tieneRecompensasPendientes(Proyecto proyecto) {
        return proyecto.getInvestigadores().stream()
                .filter(Usuario::isActivo)
                .filter(usuario -> usuario.getRol() == Rol.INVESTIGADOR)
                .anyMatch(usuario -> !tiposPendientes(proyecto, usuario).isEmpty());
    }

    private void validarDatos(RecompensaRequest request, Usuario beneficiario) {
        if (!tiposPermitidos(beneficiario).contains(request.tipo().name())) {
            throw new IllegalArgumentException(
                    "La reduccion docente solo puede concederse a investigadores-docentes.");
        }
        if (request.tipo() == TipoRecompensa.REDUCCION_DOCENTE
                && (request.valor().compareTo(MAXIMA_REDUCCION_DOCENTE) > 0
                || request.valor().remainder(HORAS_POR_ASIGNATURA).compareTo(BigDecimal.ZERO) != 0)) {
            throw new IllegalArgumentException(
                    "La reduccion docente debe corresponder a asignaturas completas de 4 horas, hasta un maximo de 16.");
        }
    }

    private void validarDuplicado(
            Proyecto proyecto,
            Usuario beneficiario,
            TipoRecompensa tipo,
            Long recompensaId) {
        boolean existe = recompensaId == null
                ? recompensaRepository.existsByProyectoAndBeneficiarioAndTipo(proyecto, beneficiario, tipo)
                : recompensaRepository.existsByProyectoAndBeneficiarioAndTipoAndIdNot(
                        proyecto, beneficiario, tipo, recompensaId);
        if (existe) {
            throw new SolicitudDuplicadaException(
                    "Ya existe una recompensa de ese tipo para el beneficiario y proyecto seleccionados.");
        }
    }

    private Recompensa guardarSinDuplicados(Recompensa recompensa) {
        try {
            return recompensaRepository.saveAndFlush(recompensa);
        } catch (DataIntegrityViolationException exception) {
            throw new SolicitudDuplicadaException(
                    "Ya existe una recompensa de ese tipo para el beneficiario y proyecto seleccionados.");
        }
    }

    private Proyecto buscarProyectoCompletado(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el proyecto solicitado."));
        if (!proyecto.estaCompletado()) {
            throw new IllegalArgumentException("Solo pueden recompensarse proyectos completados.");
        }
        return proyecto;
    }

    private Usuario buscarBeneficiarioElegible(Proyecto proyecto, Long beneficiarioId) {
        Usuario beneficiario = usuarioRepository.findById(beneficiarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el beneficiario solicitado."));
        if (!beneficiario.isActivo()
                || beneficiario.getRol() != Rol.INVESTIGADOR
                || !proyecto.participa(beneficiario)) {
            throw new IllegalArgumentException("El beneficiario no es elegible para el proyecto seleccionado.");
        }
        return beneficiario;
    }

    private Recompensa buscarRecompensa(Long id) {
        return recompensaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro la recompensa solicitada."));
    }

    private Usuario exigirCoordinador(String nombreUsuario) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        if (usuario.getRol() != Rol.COORDINADOR) {
            throw new AccesoNoPermitidoException();
        }
        return usuario;
    }

    private Usuario exigirInvestigador(String nombreUsuario) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        if (usuario.getRol() != Rol.INVESTIGADOR) {
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

    private boolean contiene(String valor, String filtro) {
        return valor != null && valor.toLowerCase(Locale.ROOT).contains(filtro);
    }
}
