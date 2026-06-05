package es.funiber.investigacion.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        "UPDATE usuarios SET activo = TRUE WHERE nombre_usuario IN ('coordinador', 'investigador', 'docente.santander', 'investigador.barcelona')",
        "UPDATE cargas_trabajo SET horas_docencia = 0, horas_investigacion = 24, horas_gestion_academica = 4 WHERE usuario_id = (SELECT id FROM usuarios WHERE nombre_usuario = 'investigador')",
        "UPDATE cargas_trabajo SET horas_docencia = 14, horas_investigacion = 12, horas_gestion_academica = 3 WHERE usuario_id = (SELECT id FROM usuarios WHERE nombre_usuario = 'docente.santander')"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class CargaTrabajoIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void coordinadorPuedeAbrirOpcionesDeCargaTrabajo() throws Exception {
        HttpSession session = iniciarSesion("coordinador", "coordinador123");

        mockMvc.perform(get("/api/carga-trabajo").session((MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maximoDocenteSemanal").value(16.0))
                .andExpect(jsonPath("$.cargas[*].usuario", hasItem("docente.santander")))
                .andExpect(jsonPath("$.sugerencias[0].candidatos[*].usuario", hasItem("docente.santander")));
    }

    @Test
    void investigadorNoPuedeAbrirPanelGlobalDeCargaTrabajo() throws Exception {
        HttpSession session = iniciarSesion("investigador", "investigador123");

        mockMvc.perform(get("/api/carga-trabajo").session((MockHttpSession) session))
                .andExpect(status().isForbidden());
    }

    @Test
    void investigadorDeSedeSinDocenciaNoPuedeRegistrarHorasDocentes() throws Exception {
        HttpSession session = iniciarSesion("investigador.barcelona", "barcelona123");

        mockMvc.perform(patch("/api/carga-trabajo/me")
                        .with(csrf())
                        .session((MockHttpSession) session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "horasDocencia": 20,
                                  "horasInvestigacion": 8,
                                  "horasGestionAcademica": 2,
                                  "observaciones": "Carga declarada en sede sin docencia."
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("Un investigador de una sede sin docencia no puede registrar horas docentes."));
    }

    @Test
    void coordinadorNoPuedeRegistrarMasDeDieciseisHorasDocentes() throws Exception {
        HttpSession session = iniciarSesion("coordinador", "coordinador123");
        Long docenteId = usuarioRepository.findByNombreUsuario("docente.santander").orElseThrow().getId();

        mockMvc.perform(patch("/api/carga-trabajo/%d".formatted(docenteId))
                        .with(csrf())
                        .session((MockHttpSession) session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "horasDocencia": 18,
                                  "horasInvestigacion": 10,
                                  "horasGestionAcademica": 3,
                                  "observaciones": "Supera el limite docente semanal."
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("Un investigador-docente no puede superar 16 horas semanales de docencia."));
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
