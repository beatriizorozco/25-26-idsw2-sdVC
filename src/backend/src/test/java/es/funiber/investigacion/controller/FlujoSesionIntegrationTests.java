package es.funiber.investigacion.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FlujoSesionIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void exponeTokenCsrfParaLaSpa() throws Exception {
        mockMvc.perform(get("/api/auth/csrf"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headerName").isNotEmpty())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void permiteOrigenLocalDelNavegadorIntegrado() throws Exception {
        mockMvc.perform(get("/api/auth/csrf")
                        .header("Origin", "http://127.0.0.1:5173"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://127.0.0.1:5173"));
    }

    @Test
    void rechazaCredencialesIncorrectas() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "usuario": "coordinador",
                                  "contrasena": "incorrecta"
                                }
                                """))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.mensaje").value("Credenciales incorrectas"));
    }

    @Test
    void permiteReintentarTrasCredencialesIncorrectas() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "usuario": "coordinador",
                                  "contrasena": "incorrecta"
                                }
                                """))
                .andExpect(status().isUnauthorized());

        iniciarSesion("coordinador", "coordinador123");
    }

    @Test
    void coordinadorRecibeConvocatoriasEnSuPanel() throws Exception {
        HttpSession session = iniciarSesion("coordinador", "coordinador123");

        mockMvc.perform(get("/api/panel-principal").session((org.springframework.mock.web.MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rol").value("COORDINADOR"))
                .andExpect(jsonPath("$.acciones[*].codigo", hasItem("convocatorias")));
    }

    @Test
    void investigadorNoRecibeConvocatoriasEnSuPanel() throws Exception {
        HttpSession session = iniciarSesion("investigador", "investigador123");

        mockMvc.perform(get("/api/panel-principal").session((org.springframework.mock.web.MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rol").value("INVESTIGADOR"))
                .andExpect(jsonPath("$.acciones[*].codigo", not(hasItem("convocatorias"))));
    }

    @Test
    void investigadorNoDocentePuedeConsultarRecompensasEconomicasEnSuPanel() throws Exception {
        HttpSession session = iniciarSesion("investigador.barcelona", "barcelona123");

        mockMvc.perform(get("/api/panel-principal").session((org.springframework.mock.web.MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rol").value("INVESTIGADOR"))
                .andExpect(jsonPath("$.acciones[*].codigo", hasItem("recompensas")));
    }

    @Test
    void investigadorDocenteRecibeRecompensasEnSuPanel() throws Exception {
        HttpSession session = iniciarSesion("docente.santander", "docente123");

        mockMvc.perform(get("/api/panel-principal").session((org.springframework.mock.web.MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rol").value("INVESTIGADOR"))
                .andExpect(jsonPath("$.acciones[*].codigo", hasItem("recompensas")));
    }

    @Test
    void cerrarSesionImpideVolverAConsultarElPanel() throws Exception {
        org.springframework.mock.web.MockHttpSession session =
                (org.springframework.mock.web.MockHttpSession) iniciarSesion("coordinador", "coordinador123");

        mockMvc.perform(post("/api/auth/logout")
                        .with(csrf())
                        .session(session))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/panel-principal").session(session))
                .andExpect(status().isUnauthorized());
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
