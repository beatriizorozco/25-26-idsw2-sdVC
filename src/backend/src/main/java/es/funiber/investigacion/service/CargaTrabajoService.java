package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.CargaTrabajoPersonaResponse;
import es.funiber.investigacion.dto.CargaTrabajoUpdateRequest;
import es.funiber.investigacion.dto.PanelCargaTrabajoResponse;
import es.funiber.investigacion.dto.ProyectoLibreResponse;
import es.funiber.investigacion.dto.SugerenciaAsignacionResponse;
import es.funiber.investigacion.model.CargaTrabajo;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.SedeFuniber;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.CargaTrabajoRepository;
import es.funiber.investigacion.repository.ProyectoRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CargaTrabajoService {

    private static final BigDecimal MAXIMO_DOCENTE_SEMANAL = new BigDecimal("16.00");

    private final UsuarioRepository usuarioRepository;
    private final CargaTrabajoRepository cargaTrabajoRepository;
    private final AccesoUsuarioService accesoUsuarios;
    private final ProyectoRepository proyectoRepository;

    public CargaTrabajoService(
            UsuarioRepository usuarioRepository,
            CargaTrabajoRepository cargaTrabajoRepository,
            AccesoUsuarioService accesoUsuarios,
            ProyectoRepository proyectoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cargaTrabajoRepository = cargaTrabajoRepository;
        this.accesoUsuarios = accesoUsuarios;
        this.proyectoRepository = proyectoRepository;
    }

    @Transactional(readOnly = true)
    public PanelCargaTrabajoResponse obtenerPanelCoordinador(String nombreUsuario, String criterio) {
        exigirCoordinador(nombreUsuario);
        String filtro = criterio == null ? "" : criterio.trim().toLowerCase(Locale.ROOT);
        List<CargaTrabajoPersonaResponse> cargas = usuarioRepository.findByActivoTrueOrderByNombreCompletoAsc()
                .stream()
                .filter(usuario -> filtro.isBlank() || coincide(usuario, filtro))
                .map(this::mapearCarga)
                .toList();
        List<ProyectoLibreResponse> proyectosLibres = obtenerProyectosLibres();
        return new PanelCargaTrabajoResponse(
                MAXIMO_DOCENTE_SEMANAL,
                cargas,
                proyectosLibres,
                sugerirAsignaciones(proyectosLibres));
    }

    @Transactional(readOnly = true)
    public CargaTrabajoPersonaResponse obtenerCargaPropia(String nombreUsuario) {
        return mapearCarga(accesoUsuarios.buscarActivo(nombreUsuario));
    }

    @Transactional(readOnly = true)
    public CargaTrabajoPersonaResponse obtenerCargaPorPerfil(String nombreUsuario, Long perfilId) {
        exigirCoordinador(nombreUsuario);
        return mapearCarga(accesoUsuarios.buscarActivo(perfilId));
    }

    @Transactional
    public CargaTrabajoPersonaResponse actualizarCargaPropia(
            String nombreUsuario,
            CargaTrabajoUpdateRequest request) {
        Usuario usuario = accesoUsuarios.exigirRol(nombreUsuario, Rol.INVESTIGADOR);
        return actualizarCarga(usuario, request);
    }

    @Transactional
    public CargaTrabajoPersonaResponse actualizarCargaPorPerfil(
            String nombreUsuario,
            Long perfilId,
            CargaTrabajoUpdateRequest request) {
        exigirCoordinador(nombreUsuario);
        return actualizarCarga(accesoUsuarios.buscarActivo(perfilId), request);
    }

    private CargaTrabajoPersonaResponse actualizarCarga(Usuario usuario, CargaTrabajoUpdateRequest request) {
        validarCargaDocente(usuario, request);
        CargaTrabajo cargaTrabajo = obtenerOCrearCarga(usuario);
        cargaTrabajo.actualizar(
                request.horasDocencia(),
                request.horasInvestigacion(),
                request.horasGestionAcademica(),
                normalizar(request.observaciones()));
        cargaTrabajoRepository.save(cargaTrabajo);
        return mapearCarga(usuario);
    }

    private void validarCargaDocente(Usuario usuario, CargaTrabajoUpdateRequest request) {
        if (!usuario.esInvestigadorDocente()
                && request.horasDocencia().compareTo(BigDecimal.ZERO) > 0) {
            throw new IllegalArgumentException(
                    "Un investigador de una sede sin docencia no puede registrar horas docentes.");
        }
        if (usuario.esInvestigadorDocente()
                && request.horasDocencia().compareTo(MAXIMO_DOCENTE_SEMANAL) > 0) {
            throw new IllegalArgumentException(
                    "Un investigador-docente no puede superar 16 horas semanales de docencia.");
        }
    }

    private CargaTrabajoPersonaResponse mapearCarga(Usuario usuario) {
        CargaTrabajo cargaTrabajo = obtenerOCrearCarga(usuario);
        return CargaTrabajoPersonaResponse.desde(
                usuario,
                cargaTrabajo,
                MAXIMO_DOCENTE_SEMANAL);
    }

    private CargaTrabajo obtenerOCrearCarga(Usuario usuario) {
        return cargaTrabajoRepository.findByUsuario(usuario)
                .orElseGet(() -> new CargaTrabajo(usuario));
    }

    private List<SugerenciaAsignacionResponse> sugerirAsignaciones(List<ProyectoLibreResponse> proyectosLibres) {
        List<CargaTrabajoPersonaResponse> investigadoresDocentes =
                usuarioRepository.findByRolAndActivoTrueOrderByNombreCompletoAsc(Rol.INVESTIGADOR)
                        .stream()
                        .filter(Usuario::esInvestigadorDocente)
                        .map(this::mapearCarga)
                        .sorted(Comparator.comparing(CargaTrabajoPersonaResponse::totalSemanal))
                        .toList();

        return proyectosLibres.stream()
                .map(proyecto -> new SugerenciaAsignacionResponse(
                        proyecto,
                        investigadoresDocentes.stream()
                                .filter(carga -> carga.sede().equals(proyecto.sede()))
                                .limit(3)
                                .toList()))
                .toList();
    }

    private List<ProyectoLibreResponse> obtenerProyectosLibres() {
        return proyectoRepository.findByArchivadoOrderByNombreAsc(false).stream()
                .filter(proyecto -> proyecto.getInvestigadores().isEmpty())
                .map(proyecto -> new ProyectoLibreResponse(
                        proyecto.getCodigo(),
                        proyecto.getNombre(),
                        proyecto.getSede() == null ? SedeFuniber.GLOBAL.getEtiqueta() : proyecto.getSede().getEtiqueta(),
                        proyecto.getArea() == null ? "" : proyecto.getArea()))
                .toList();
    }

    private void exigirCoordinador(String nombreUsuario) {
        accesoUsuarios.exigirRol(nombreUsuario, Rol.COORDINADOR);
    }

    private boolean coincide(Usuario usuario, String filtro) {
        return contiene(usuario.getNombreUsuario(), filtro)
                || contiene(usuario.getNombreCompleto(), filtro)
                || contiene(usuario.getSede().getEtiqueta(), filtro)
                || contiene(usuario.getUnidad(), filtro)
                || contiene(usuario.getLineaInvestigacion(), filtro);
    }

    private boolean contiene(String valor, String filtro) {
        return valor != null && valor.toLowerCase(Locale.ROOT).contains(filtro);
    }

    private String normalizar(String valor) {
        return valor == null || valor.isBlank() ? "" : valor.trim();
    }
}
