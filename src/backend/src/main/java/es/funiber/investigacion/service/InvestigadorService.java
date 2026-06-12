package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.InvestigadorCreateRequest;
import es.funiber.investigacion.dto.InvestigadorDetalleResponse;
import es.funiber.investigacion.dto.InvestigadorResumenResponse;
import es.funiber.investigacion.dto.ProyectoInvestigadorResponse;
import es.funiber.investigacion.exception.AccesoNoPermitidoException;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.exception.SolicitudDuplicadaException;
import es.funiber.investigacion.model.CargaTrabajo;
import es.funiber.investigacion.model.Proyecto;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.CargaTrabajoRepository;
import es.funiber.investigacion.repository.ProyectoRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvestigadorService {

    private final UsuarioRepository usuarioRepository;
    private final ProyectoRepository proyectoRepository;
    private final CargaTrabajoRepository cargaTrabajoRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccesoUsuarioService accesoUsuarios;

    public InvestigadorService(
            UsuarioRepository usuarioRepository,
            ProyectoRepository proyectoRepository,
            CargaTrabajoRepository cargaTrabajoRepository,
            PasswordEncoder passwordEncoder,
            AccesoUsuarioService accesoUsuarios) {
        this.usuarioRepository = usuarioRepository;
        this.proyectoRepository = proyectoRepository;
        this.cargaTrabajoRepository = cargaTrabajoRepository;
        this.passwordEncoder = passwordEncoder;
        this.accesoUsuarios = accesoUsuarios;
    }

    @Transactional(readOnly = true)
    public List<InvestigadorResumenResponse> listar(String nombreUsuario, String criterio, Long proyectoId) {
        Usuario solicitante = accesoUsuarios.buscarActivo(nombreUsuario);
        List<Usuario> investigadores = proyectoId == null
                ? listarDirectorioGlobal(solicitante)
                : listarParticipantes(solicitante, proyectoId);
        String filtro = criterio == null ? "" : criterio.trim().toLowerCase(Locale.ROOT);
        return investigadores.stream()
                .filter(usuario -> coincide(usuario, filtro))
                .map(usuario -> InvestigadorResumenResponse.desde(usuario, cargaSemanal(usuario)))
                .toList();
    }

    @Transactional(readOnly = true)
    public InvestigadorDetalleResponse obtener(String nombreUsuario, Long investigadorId, Long proyectoId) {
        Usuario solicitante = accesoUsuarios.buscarActivo(nombreUsuario);
        Usuario investigador = accesoUsuarios.buscarInvestigadorActivo(investigadorId);
        if (solicitante.getRol() == Rol.INVESTIGADOR && proyectoId != null) {
            Proyecto proyecto = buscarProyectoVisibleParaInvestigador(solicitante, proyectoId);
            if (!proyecto.participa(investigador)) {
                throw new RecursoNoEncontradoException("No se encontro el investigador solicitado.");
            }
        }
        List<ProyectoInvestigadorResponse> proyectos = proyectosVisibles(solicitante, investigador, proyectoId);
        return InvestigadorDetalleResponse.desde(investigador, proyectos);
    }

    @Transactional
    public InvestigadorDetalleResponse crear(String nombreUsuario, InvestigadorCreateRequest request) {
        exigirCoordinador(nombreUsuario);
        String usuario = request.usuario().trim();
        String emailNormalizado = request.email().trim().toLowerCase(Locale.ROOT);
        if (usuarioRepository.findByNombreUsuario(usuario).isPresent()) {
            throw new SolicitudDuplicadaException("Ya existe un investigador con ese nombre de usuario.");
        }
        if (usuarioRepository.existsByEmailIgnoreCase(emailNormalizado)) {
            throw new SolicitudDuplicadaException("Ya existe un investigador con ese correo.");
        }

        Usuario investigador = new Usuario(
                usuario,
                passwordEncoder.encode(generarContrasenaTemporal(usuario)),
                Rol.INVESTIGADOR,
                true);
        investigador.actualizarPerfil(
                request.nombreCompleto().trim(),
                emailNormalizado,
                limpiar(request.unidad()),
                limpiar(request.lineaInvestigacion()),
                limpiar(request.biografia()));
        investigador.actualizarSede(request.sede());
        Usuario guardado = usuarioRepository.save(investigador);
        cargaTrabajoRepository.save(new CargaTrabajo(guardado));
        return InvestigadorDetalleResponse.desde(guardado, List.of());
    }

    private List<Usuario> listarDirectorioGlobal(Usuario solicitante) {
        if (solicitante.getRol() != Rol.COORDINADOR && solicitante.getRol() != Rol.INVESTIGADOR) {
            throw new AccesoNoPermitidoException();
        }
        return usuarioRepository.findByRolAndActivoTrueOrderByNombreCompletoAsc(Rol.INVESTIGADOR);
    }

    private List<Usuario> listarParticipantes(Usuario solicitante, Long proyectoId) {
        Proyecto proyecto = solicitante.getRol() == Rol.COORDINADOR
                ? buscarProyecto(proyectoId)
                : buscarProyectoVisibleParaInvestigador(solicitante, proyectoId);
        return proyecto.getInvestigadores().stream()
                .filter(Usuario::isActivo)
                .sorted((a, b) -> nombre(a).compareToIgnoreCase(nombre(b)))
                .toList();
    }

    private List<ProyectoInvestigadorResponse> proyectosVisibles(
            Usuario solicitante,
            Usuario investigador,
            Long proyectoId) {
        if (solicitante.getRol() == Rol.COORDINADOR) {
            return proyectoRepository.findDistinctByInvestigadoresIdOrderByNombreAsc(investigador.getId()).stream()
                    .map(ProyectoInvestigadorResponse::desde)
                    .toList();
        }

        List<Proyecto> proyectosSolicitante = proyectoRepository.findDistinctByInvestigadoresIdOrderByNombreAsc(solicitante.getId());
        return proyectosSolicitante.stream()
                .filter(proyecto -> proyecto.participa(investigador))
                .filter(proyecto -> proyectoId == null || proyecto.getId().equals(proyectoId))
                .map(ProyectoInvestigadorResponse::desde)
                .toList();
    }

    private Proyecto buscarProyectoVisibleParaInvestigador(Usuario solicitante, Long proyectoId) {
        Proyecto proyecto = buscarProyecto(proyectoId);
        if (!proyecto.participa(solicitante)) {
            throw new RecursoNoEncontradoException("No se encontro el proyecto solicitado.");
        }
        return proyecto;
    }

    private Proyecto buscarProyecto(Long proyectoId) {
        return proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el proyecto solicitado."));
    }

    private void exigirCoordinador(String nombreUsuario) {
        accesoUsuarios.exigirRol(nombreUsuario, Rol.COORDINADOR);
    }

    private BigDecimal cargaSemanal(Usuario usuario) {
        return cargaTrabajoRepository.findByUsuario(usuario)
                .map(CargaTrabajo::totalSemanal)
                .orElse(BigDecimal.ZERO);
    }

    private boolean coincide(Usuario usuario, String filtro) {
        return filtro.isBlank()
                || contiene(usuario.getNombreUsuario(), filtro)
                || contiene(usuario.getNombreCompleto(), filtro)
                || contiene(usuario.getUnidad(), filtro)
                || contiene(usuario.getLineaInvestigacion(), filtro)
                || contiene(usuario.getSede().getEtiqueta(), filtro);
    }

    private boolean contiene(String valor, String filtro) {
        return valor != null && valor.toLowerCase(Locale.ROOT).contains(filtro);
    }

    private String limpiar(String valor) {
        return valor == null ? "" : valor.trim();
    }

    private String generarContrasenaTemporal(String usuario) {
        return usuario + "123";
    }

    private String nombre(Usuario usuario) {
        return usuario.getNombreCompleto() == null || usuario.getNombreCompleto().isBlank()
                ? usuario.getNombreUsuario()
                : usuario.getNombreCompleto();
    }
}
