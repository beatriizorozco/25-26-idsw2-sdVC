package es.funiber.investigacion.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import es.funiber.investigacion.model.EstadoSolicitudEliminacion;
import es.funiber.investigacion.repository.SolicitudEliminacionPerfilRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(statements = {
        "DELETE FROM solicitudes_eliminacion_perfil",
        "UPDATE usuarios SET activo = TRUE WHERE nombre_usuario IN ('coordinador', 'investigador')"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PerfilIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SolicitudEliminacionPerfilRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void coordinadorPuedeActualizarSuPerfilPropio() throws Exception {
        HttpSession session = iniciarSesion("coordinador", "coordinador123");

        mockMvc.perform(patch("/api/perfil")
                        .with(csrf())
                        .session((MockHttpSession) session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nombreCompleto": "Coordinadora de Investigacion",
                                  "email": "coordinadora@funiber.org",
                                  "unidad": "Gestion academica",
                                  "lineaInvestigacion": "Gestion de proyectos",
                                  "biografia": "Perfil actualizado desde pruebas."
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreCompleto").value("Coordinadora de Investigacion"))
                .andExpect(jsonPath("$.email").value("coordinadora@funiber.org"));
    }

    @Test
    void investigadorSolicitaEliminacionConCreatedYCierraSesion() throws Exception {
        MockHttpSession session = (MockHttpSession) iniciarSesion("investigador", "investigador123");

        mockMvc.perform(post("/api/perfil/solicitud-eliminacion")
                        .with(csrf())
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "motivo": "Deseo cerrar mi perfil."
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.usuario").value("investigador"))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));

        mockMvc.perform(get("/api/perfil").session(session))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void investigadorNoPuedeListarSolicitudesDeEliminacion() throws Exception {
        HttpSession session = iniciarSesion("investigador", "investigador123");

        mockMvc.perform(get("/api/solicitudes-eliminacion-perfil")
                        .session((MockHttpSession) session))
                .andExpect(status().isForbidden());
    }

    @Test
    void coordinadorPuedeListarSolicitudesPendientes() throws Exception {
        registrarSolicitudInvestigador();
        HttpSession session = iniciarSesion("coordinador", "coordinador123");

        mockMvc.perform(get("/api/solicitudes-eliminacion-perfil")
                        .session((MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].usuario", hasItem("investigador")));
    }

    @Test
    void noPermiteEliminarElUnicoCoordinadorActivo() throws Exception {
        registrarSolicitudCoordinador();
        Long solicitudId = solicitudRepository
                .findByEstadoOrderByFechaCreacionDesc(EstadoSolicitudEliminacion.PENDIENTE)
                .get(0)
                .getId();
        HttpSession session = iniciarSesion("coordinador", "coordinador123");

        mockMvc.perform(delete("/api/solicitudes-eliminacion-perfil/%d/perfil".formatted(solicitudId))
                        .with(csrf())
                        .session((MockHttpSession) session))
                .andExpect(status().isForbidden());

        assertTrue(usuarioRepository.findByNombreUsuario("coordinador").orElseThrow().isActivo());
    }

    @Test
    void coordinadorPuedeEliminarPerfilDeInvestigadorDesdeSolicitud() throws Exception {
        registrarSolicitudInvestigador();
        Long solicitudId = solicitudRepository
                .findByEstadoOrderByFechaCreacionDesc(EstadoSolicitudEliminacion.PENDIENTE)
                .get(0)
                .getId();
        HttpSession session = iniciarSesion("coordinador", "coordinador123");

        mockMvc.perform(delete("/api/solicitudes-eliminacion-perfil/%d/perfil".formatted(solicitudId))
                        .with(csrf())
                        .session((MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("RESUELTA"));

        assertFalse(usuarioRepository.findByNombreUsuario("investigador").orElseThrow().isActivo());
    }

    @Test
    void solicitudesResueltasNoAparecenEnListadoPendiente() throws Exception {
        registrarSolicitudInvestigador();
        Long solicitudId = solicitudRepository
                .findByEstadoOrderByFechaCreacionDesc(EstadoSolicitudEliminacion.PENDIENTE)
                .get(0)
                .getId();
        HttpSession session = iniciarSesion("coordinador", "coordinador123");

        mockMvc.perform(delete("/api/solicitudes-eliminacion-perfil/%d/perfil".formatted(solicitudId))
                        .with(csrf())
                        .session((MockHttpSession) session))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/solicitudes-eliminacion-perfil")
                        .session((MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].usuario", not(hasItem("investigador"))));
    }

    private void registrarSolicitudInvestigador() throws Exception {
        MockHttpSession session = (MockHttpSession) iniciarSesion("investigador", "investigador123");
        mockMvc.perform(post("/api/perfil/solicitud-eliminacion")
                        .with(csrf())
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "motivo": "Solicitud de baja de investigador."
                                }
                                """))
                .andExpect(status().isCreated());
    }

    private void registrarSolicitudCoordinador() throws Exception {
        MockHttpSession session = (MockHttpSession) iniciarSesion("coordinador", "coordinador123");
        mockMvc.perform(post("/api/perfil/solicitud-eliminacion")
                        .with(csrf())
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "motivo": "Solicitud de baja de coordinador."
                                }
                                """))
                .andExpect(status().isCreated());
    }

    private HttpSession iniciarSesion(String usuario, String contrasena) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "usuario": "%s",
                                  "contrasena": "%s"
                                }
                                """.formatted(usuario, contrasena)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuario").value(usuario))
                .andReturn();

        return result.getRequest().getSession(false);
    }
}
