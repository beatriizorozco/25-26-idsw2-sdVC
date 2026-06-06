package es.funiber.investigacion.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import es.funiber.investigacion.repository.ProyectoRepository;
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
        "DELETE FROM recompensas",
        "UPDATE usuarios SET activo = TRUE WHERE nombre_usuario IN ('coordinador', 'investigador', 'docente.santander', 'investigador.barcelona')"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class RecompensaIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void coordinadorPuedeCrearRecompensaEconomicaParaInvestigadorNoDocente() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        Long proyectoId = proyectoRepository.findByCodigo("PRY-BCN-COM-01").orElseThrow().getId();
        Long beneficiarioId = usuarioRepository.findByNombreUsuario("investigador").orElseThrow().getId();

        crearRecompensa(coordinador, proyectoId, beneficiarioId, "ECONOMICA", 350)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tipo").value("ECONOMICA"))
                .andExpect(jsonPath("$.beneficiario").value("investigador"));
    }

    @Test
    void reduccionDocenteSoloPuedeConcederseAInvestigadorDocente() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        Long proyectoBarcelona = proyectoRepository.findByCodigo("PRY-BCN-COM-01").orElseThrow().getId();
        Long investigador = usuarioRepository.findByNombreUsuario("investigador").orElseThrow().getId();
        Long proyectoSantander = proyectoRepository.findByCodigo("PRY-SAN-COM-01").orElseThrow().getId();
        Long docente = usuarioRepository.findByNombreUsuario("docente.santander").orElseThrow().getId();

        crearRecompensa(coordinador, proyectoBarcelona, investigador, "REDUCCION_DOCENTE", 2)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value(
                        "La reduccion docente solo puede concederse a investigadores-docentes."));

        crearRecompensa(coordinador, proyectoSantander, docente, "REDUCCION_DOCENTE", 2)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tipo").value("REDUCCION_DOCENTE"));
    }

    @Test
    void investigadorSoloPuedeConsultarSusPropiasRecompensas() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        Long proyectoId = proyectoRepository.findByCodigo("PRY-BCN-COM-01").orElseThrow().getId();
        Long beneficiarioId = usuarioRepository.findByNombreUsuario("investigador").orElseThrow().getId();
        crearRecompensa(coordinador, proyectoId, beneficiarioId, "ECONOMICA", 350)
                .andExpect(status().isCreated());

        HttpSession investigador = iniciarSesion("investigador", "investigador123");
        mockMvc.perform(get("/api/recompensas/me").session((MockHttpSession) investigador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].beneficiario", hasItem("investigador")))
                .andExpect(jsonPath("$[*].beneficiario", not(hasItem("docente.santander"))));

        mockMvc.perform(get("/api/recompensas").session((MockHttpSession) investigador))
                .andExpect(status().isForbidden());
    }

    @Test
    void coordinadorPuedeEliminarRecompensaConConfirmacionDesdeLaInterfaz() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        Long proyectoId = proyectoRepository.findByCodigo("PRY-BCN-COM-01").orElseThrow().getId();
        Long beneficiarioId = usuarioRepository.findByNombreUsuario("investigador").orElseThrow().getId();
        MvcResult creada = crearRecompensa(coordinador, proyectoId, beneficiarioId, "ECONOMICA", 350)
                .andExpect(status().isCreated())
                .andReturn();
        Number recompensaId = com.jayway.jsonpath.JsonPath.read(creada.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(delete("/api/recompensas/{id}", recompensaId)
                        .with(csrf())
                        .session((MockHttpSession) coordinador))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/recompensas/{id}", recompensaId).session((MockHttpSession) coordinador))
                .andExpect(status().isNotFound());
    }

    @Test
    void opcionesCreacionExcluyenBeneficiariosYProyectosSinTiposPendientes() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        Long proyectoId = proyectoRepository.findByCodigo("PRY-BCN-COM-01").orElseThrow().getId();
        Long investigadorId = usuarioRepository.findByNombreUsuario("investigador").orElseThrow().getId();
        Long investigadorBarcelonaId = usuarioRepository.findByNombreUsuario("investigador.barcelona").orElseThrow().getId();

        crearRecompensa(coordinador, proyectoId, investigadorId, "ECONOMICA", 350)
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/recompensas/opciones-creacion/{proyectoId}/beneficiarios", proyectoId)
                        .session((MockHttpSession) coordinador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].usuario", not(hasItem("investigador"))))
                .andExpect(jsonPath("$[*].usuario", hasItem("investigador.barcelona")));

        crearRecompensa(coordinador, proyectoId, investigadorBarcelonaId, "ECONOMICA", 450)
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/recompensas/opciones-creacion")
                        .session((MockHttpSession) coordinador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.proyectos[*].codigo", not(hasItem("PRY-BCN-COM-01"))));
    }

    @Test
    void coordinadorPuedeEditarUnaRecompensa() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        Long proyectoId = proyectoRepository.findByCodigo("PRY-BCN-COM-01").orElseThrow().getId();
        Long beneficiarioId = usuarioRepository.findByNombreUsuario("investigador").orElseThrow().getId();
        MvcResult creada = crearRecompensa(coordinador, proyectoId, beneficiarioId, "ECONOMICA", 350)
                .andExpect(status().isCreated())
                .andReturn();
        Number recompensaId = com.jayway.jsonpath.JsonPath.read(creada.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(patch("/api/recompensas/{id}", recompensaId)
                        .with(csrf())
                        .session((MockHttpSession) coordinador)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "proyectoId": %d,
                                  "beneficiarioId": %d,
                                  "tipo": "ECONOMICA",
                                  "concepto": "Reconocimiento actualizado",
                                  "valor": 500
                                }
                                """.formatted(proyectoId, beneficiarioId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.concepto").value("Reconocimiento actualizado"))
                .andExpect(jsonPath("$.valor").value(500));
    }

    @Test
    void prepararEdicionMantieneTipoActualYExcluyeOtrosDuplicados() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        Long proyectoId = proyectoRepository.findByCodigo("PRY-SAN-COM-01").orElseThrow().getId();
        Long docenteId = usuarioRepository.findByNombreUsuario("docente.santander").orElseThrow().getId();
        MvcResult economica = crearRecompensa(coordinador, proyectoId, docenteId, "ECONOMICA", 500)
                .andExpect(status().isCreated())
                .andReturn();
        crearRecompensa(coordinador, proyectoId, docenteId, "REDUCCION_DOCENTE", 2)
                .andExpect(status().isCreated());
        Number recompensaId = com.jayway.jsonpath.JsonPath.read(economica.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(get("/api/recompensas/{id}/edicion", recompensaId)
                        .session((MockHttpSession) coordinador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.beneficiarios[0].tiposPermitidos", hasItem("ECONOMICA")))
                .andExpect(jsonPath("$.beneficiarios[0].tiposPermitidos", not(hasItem("REDUCCION_DOCENTE"))));
    }

    @Test
    void noPermiteRecompensasDuplicadasNiBeneficiariosAjenosAlProyecto() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        Long proyectoId = proyectoRepository.findByCodigo("PRY-BCN-COM-01").orElseThrow().getId();
        Long investigadorId = usuarioRepository.findByNombreUsuario("investigador").orElseThrow().getId();
        Long docenteId = usuarioRepository.findByNombreUsuario("docente.santander").orElseThrow().getId();

        crearRecompensa(coordinador, proyectoId, investigadorId, "ECONOMICA", 350)
                .andExpect(status().isCreated());
        crearRecompensa(coordinador, proyectoId, investigadorId, "ECONOMICA", 400)
                .andExpect(status().isConflict());
        crearRecompensa(coordinador, proyectoId, docenteId, "ECONOMICA", 400)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El beneficiario no es elegible para el proyecto seleccionado."));
    }

    @Test
    void investigadorNoPuedeAbrirUnaRecompensaAjena() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        Long proyectoId = proyectoRepository.findByCodigo("PRY-BCN-COM-01").orElseThrow().getId();
        Long beneficiarioId = usuarioRepository.findByNombreUsuario("investigador").orElseThrow().getId();
        MvcResult creada = crearRecompensa(coordinador, proyectoId, beneficiarioId, "ECONOMICA", 350)
                .andExpect(status().isCreated())
                .andReturn();
        Number recompensaId = com.jayway.jsonpath.JsonPath.read(creada.getResponse().getContentAsString(), "$.id");
        HttpSession investigadorBarcelona = iniciarSesion("investigador.barcelona", "barcelona123");

        mockMvc.perform(get("/api/recompensas/me/{id}", recompensaId)
                        .session((MockHttpSession) investigadorBarcelona))
                .andExpect(status().isForbidden());
    }

    private org.springframework.test.web.servlet.ResultActions crearRecompensa(
            HttpSession sesion,
            Long proyectoId,
            Long beneficiarioId,
            String tipo,
            int valor) throws Exception {
        return mockMvc.perform(post("/api/recompensas")
                .with(csrf())
                .session((MockHttpSession) sesion)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "proyectoId": %d,
                          "beneficiarioId": %d,
                          "tipo": "%s",
                          "concepto": "Reconocimiento por proyecto completado",
                          "valor": %d
                        }
                        """.formatted(proyectoId, beneficiarioId, tipo, valor)));
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
