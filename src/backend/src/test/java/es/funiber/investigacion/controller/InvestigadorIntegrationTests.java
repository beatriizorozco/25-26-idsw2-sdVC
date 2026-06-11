package es.funiber.investigacion.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.servlet.http.HttpSession;
import java.util.List;
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
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Sql(statements = {
        "UPDATE usuarios SET activo = TRUE WHERE nombre_usuario IN ('coordinador', 'investigador', 'docente.santander', 'investigador.barcelona')"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class InvestigadorIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void coordinadorPuedeListarYCrearInvestigadores() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");

        mockMvc.perform(get("/api/investigadores").session((MockHttpSession) coordinador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].usuario", hasItem("investigador")))
                .andExpect(jsonPath("$[*].usuario", hasItem("docente.santander")));

        mockMvc.perform(post("/api/investigadores")
                        .with(csrf())
                        .session((MockHttpSession) coordinador)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "usuario": "investigador.lima",
                                  "nombreCompleto": "Investigador Lima",
                                  "email": "investigador.lima@funiber.org",
                                  "sede": "LIMA",
                                  "unidad": "Investigacion",
                                  "lineaInvestigacion": "Transferencia educativa",
                                  "biografia": "Perfil creado desde pruebas de integracion."
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.usuario").value("investigador.lima"))
                .andExpect(jsonPath("$.sede").value("Lima"));
    }

    @Test
    void investigadorPuedeConsultarDirectorioGlobalYDetalleCompartido() throws Exception {
        HttpSession investigador = iniciarSesion("investigador", "investigador123");

        mockMvc.perform(get("/api/investigadores").session((MockHttpSession) investigador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].usuario", hasItem("investigador")))
                .andExpect(jsonPath("$[*].usuario", hasItem("investigador.barcelona")));

        MvcResult lista = mockMvc.perform(get("/api/investigadores").session((MockHttpSession) investigador))
                .andExpect(status().isOk())
                .andReturn();
        List<Integer> ids = com.jayway.jsonpath.JsonPath.read(
                lista.getResponse().getContentAsString(),
                "$[?(@.usuario == 'investigador.barcelona')].id");
        Number investigadorBarcelonaId = ids.get(0);

        mockMvc.perform(get("/api/investigadores/{id}", investigadorBarcelonaId)
                        .session((MockHttpSession) investigador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuario").value("investigador.barcelona"))
                .andExpect(jsonPath("$.proyectos[*].codigo", hasItem("PRY-BCN-COM-01")))
                .andExpect(jsonPath("$.proyectos[*].codigo", not(hasItem("PRY-SAN-COM-01"))));
    }

    @Test
    void investigadorNoPuedeCrearYSoloConsultaParticipantesDeProyectoVisible() throws Exception {
        HttpSession investigador = iniciarSesion("investigador.barcelona", "barcelona123");

        mockMvc.perform(post("/api/investigadores")
                        .with(csrf())
                        .session((MockHttpSession) investigador)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "usuario": "investigador.bloqueado",
                                  "nombreCompleto": "Investigador Bloqueado",
                                  "email": "investigador.bloqueado@funiber.org",
                                  "sede": "BARCELONA",
                                  "unidad": "Investigacion",
                                  "lineaInvestigacion": "Linea",
                                  "biografia": "No deberia crearse."
                                }
                                """))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/api/investigadores?proyectoId=1").session((MockHttpSession) investigador))
                .andExpect(status().isNotFound());
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
                .andReturn();
        return result.getRequest().getSession(false);
    }
}
