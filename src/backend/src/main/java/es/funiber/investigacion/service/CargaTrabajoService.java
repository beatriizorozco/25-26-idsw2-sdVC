package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.CargaTrabajoPersonaResponse;
import es.funiber.investigacion.dto.CargaTrabajoUpdateRequest;
import es.funiber.investigacion.dto.PanelCargaTrabajoResponse;
import es.funiber.investigacion.dto.ProyectoLibreResponse;
import es.funiber.investigacion.dto.SugerenciaAsignacionResponse;
import es.funiber.investigacion.exception.AccesoNoPermitidoException;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.model.CargaTrabajo;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.SedeFuniber;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.CargaTrabajoRepository;
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

    public CargaTrabajoService(
            UsuarioRepository usuarioRepository,
            CargaTrabajoRepository cargaTrabajoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cargaTrabajoRepository = cargaTrabajoRepository;
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
        List<ProyectoLibreResponse> proyectosLibres = proyectosLibresDemo();
        return new PanelCargaTrabajoResponse(
                MAXIMO_DOCENTE_SEMANAL,
                cargas,
                proyectosLibres,
                sugerirAsignaciones(proyectosLibres));
    }

    @Transactional(readOnly = true)
    public CargaTrabajoPersonaResponse obtenerCargaPropia(String nombreUsuario) {
        return mapearCarga(buscarUsuarioActivo(nombreUsuario));
    }

    @Transactional(readOnly = true)
    public CargaTrabajoPersonaResponse obtenerCargaPorPerfil(String nombreUsuario, Long perfilId) {
        exigirCoordinador(nombreUsuario);
        return mapearCarga(buscarUsuarioActivo(perfilId));
    }

    @Transactional
    public CargaTrabajoPersonaResponse actualizarCargaPropia(
            String nombreUsuario,
            CargaTrabajoUpdateRequest request) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        if (usuario.getRol() != Rol.INVESTIGADOR) {
            throw new AccesoNoPermitidoException("Solo un investigador puede actualizar su carga propia.");
        }
        return actualizarCarga(usuario, request);
    }

    @Transactional
    public CargaTrabajoPersonaResponse actualizarCargaPorPerfil(
            String nombreUsuario,
            Long perfilId,
            CargaTrabajoUpdateRequest request) {
        exigirCoordinador(nombreUsuario);
        return actualizarCarga(buscarUsuarioActivo(perfilId), request);
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

    private List<ProyectoLibreResponse> proyectosLibresDemo() {
        return List.of(
                new ProyectoLibreResponse(
                        "PRY-SAN-01",
                        "Optimizacion de gestion investigadora",
                        SedeFuniber.SANTANDER.getEtiqueta(),
                        "Gestion academica"),
                new ProyectoLibreResponse(
                        "PRY-SAN-02",
                        "Indicadores de productividad cientifica",
                        SedeFuniber.SANTANDER.getEtiqueta(),
                        "Produccion cientifica"));
    }

    private void exigirCoordinador(String nombreUsuario) {
        Usuario usuario = buscarUsuarioActivo(nombreUsuario);
        if (usuario.getRol() != Rol.COORDINADOR) {
            throw new AccesoNoPermitidoException();
        }
    }

    private Usuario buscarUsuarioActivo(String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el perfil solicitado."));
        if (!usuario.isActivo()) {
            throw new RecursoNoEncontradoException("No se encontro el perfil solicitado.");
        }
        return usuario;
    }

    private Usuario buscarUsuarioActivo(Long perfilId) {
        Usuario usuario = usuarioRepository.findById(perfilId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el perfil solicitado."));
        if (!usuario.isActivo()) {
            throw new RecursoNoEncontradoException("No se encontro el perfil solicitado.");
        }
        return usuario;
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
