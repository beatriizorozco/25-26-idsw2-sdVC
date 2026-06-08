package es.funiber.investigacion.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import es.funiber.investigacion.repository.MovimientoParticipacionProyectoRepository;
import es.funiber.investigacion.repository.ProyectoRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
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
class ProyectoIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MovimientoParticipacionProyectoRepository movimientoRepository;

    @Test
    void coordinadorPuedeCrearEditarYArchivarSinBorrarProyecto() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        MvcResult creado = crearProyecto(coordinador, "PRY-TEST-01", "Proyecto de prueba")
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.archivado").value(false))
                .andReturn();
        Number id = com.jayway.jsonpath.JsonPath.read(creado.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(patch("/api/proyectos/{id}", id)
                        .with(csrf())
                        .session((MockHttpSession) coordinador)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(proyectoJson("PRY-TEST-01", "Proyecto actualizado")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Proyecto actualizado"))
                .andExpect(jsonPath("$.estado").value("EN_CURSO"));

        mockMvc.perform(patch("/api/proyectos/{id}/archivado", id)
                        .with(csrf())
                        .session((MockHttpSession) coordinador)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motivo\":\"Proyecto finalizado y archivado\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.archivado").value(true))
                .andExpect(jsonPath("$.estado").value("COMPLETADO"));

        mockMvc.perform(get("/api/proyectos").session((MockHttpSession) coordinador))
                .andExpect(jsonPath("$[*].codigo", not(hasItem("PRY-TEST-01"))));
        mockMvc.perform(get("/api/proyectos?archivados=true").session((MockHttpSession) coordinador))
                .andExpect(jsonPath("$[*].codigo", hasItem("PRY-TEST-01")));
        assertTrue(proyectoRepository.findByCodigo("PRY-TEST-01").isPresent());
    }

    @Test
    void asignacionYDesasignacionConservanMovimientosHistoricos() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        MvcResult proyectoCreado = crearProyecto(coordinador, "PRY-TEST-EQUIPO", "Proyecto para equipo")
                .andExpect(status().isCreated())
                .andReturn();
        Number proyectoId = com.jayway.jsonpath.JsonPath.read(
                proyectoCreado.getResponse().getContentAsString(), "$.id");
        Long investigadorId = usuarioRepository.findByNombreUsuario("docente.santander").orElseThrow().getId();
        long movimientosIniciales = movimientoRepository.count();

        mockMvc.perform(post("/api/proyectos/{id}/investigadores/{investigadorId}", proyectoId, investigadorId)
                        .with(csrf())
                        .session((MockHttpSession) coordinador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.investigadores[*].usuario", hasItem("docente.santander")));

        mockMvc.perform(patch("/api/proyectos/{id}/investigadores/{investigadorId}/desasignado", proyectoId, investigadorId)
                        .with(csrf())
                        .session((MockHttpSession) coordinador)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motivo\":\"Cambio de disponibilidad\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.investigadores[*].usuario", not(hasItem("docente.santander"))));

        assertEquals(movimientosIniciales + 2, movimientoRepository.count());
    }

    @Test
    void investigadorSoloConsultaProyectosPropiosActivos() throws Exception {
        HttpSession investigador = iniciarSesion("investigador.barcelona", "barcelona123");
        Long proyectoAjeno = proyectoRepository.findByCodigo("PRY-SAN-COM-01").orElseThrow().getId();

        mockMvc.perform(get("/api/proyectos").session((MockHttpSession) investigador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].codigo", hasItem("PRY-BCN-COM-01")))
                .andExpect(jsonPath("$[*].codigo", not(hasItem("PRY-SAN-COM-01"))));

        mockMvc.perform(get("/api/proyectos/{id}", proyectoAjeno).session((MockHttpSession) investigador))
                .andExpect(status().isForbidden());
    }

    @Test
    void investigadorNoPuedeGestionarProyectos() throws Exception {
        HttpSession investigador = iniciarSesion("investigador", "investigador123");
        crearProyecto(investigador, "PRY-NO-01", "No autorizado")
                .andExpect(status().isForbidden());
    }

    @Test
    void marcarProyectoComoCompletadoLoArchivaAutomaticamente() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        MvcResult creado = crearProyecto(coordinador, "PRY-COMPLETAR-01", "Proyecto a completar")
                .andExpect(status().isCreated())
                .andReturn();
        Number id = com.jayway.jsonpath.JsonPath.read(creado.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(patch("/api/proyectos/{id}", id)
                        .with(csrf())
                        .session((MockHttpSession) coordinador)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(proyectoCompletadoJson("PRY-COMPLETAR-01", "Proyecto a completar")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("COMPLETADO"))
                .andExpect(jsonPath("$.archivado").value(true));
    }

    @Test
    void participantesPuedenSubirYVerArchivosPeroSoloCoordinadorPuedeEliminar() throws Exception {
        HttpSession investigador = iniciarSesion("investigador.barcelona", "barcelona123");
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        Long proyectoId = proyectoRepository.findByCodigo("PRY-BCN-COM-01").orElseThrow().getId();
        MockMultipartFile archivo = new MockMultipartFile(
                "archivo",
                "informe-final.txt",
                "text/plain",
                "Contenido del informe".getBytes());

        MvcResult subida = mockMvc.perform(multipart("/api/proyectos/{id}/archivos", proyectoId)
                        .file(archivo)
                        .with(csrf())
                        .session((MockHttpSession) investigador))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("informe-final.txt"))
                .andReturn();
        Number archivoId = com.jayway.jsonpath.JsonPath.read(subida.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(get("/api/proyectos/{id}/archivos", proyectoId)
                        .session((MockHttpSession) investigador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].nombre", hasItem("informe-final.txt")));

        mockMvc.perform(delete("/api/proyectos/{id}/archivos/{archivoId}", proyectoId, archivoId)
                        .with(csrf())
                        .session((MockHttpSession) investigador))
                .andExpect(status().isForbidden());

        mockMvc.perform(delete("/api/proyectos/{id}/archivos/{archivoId}", proyectoId, archivoId)
                        .with(csrf())
                        .session((MockHttpSession) coordinador))
                .andExpect(status().isNoContent());
    }

    private org.springframework.test.web.servlet.ResultActions crearProyecto(
            HttpSession sesion,
            String codigo,
            String nombre) throws Exception {
        return mockMvc.perform(post("/api/proyectos")
                .with(csrf())
                .session((MockHttpSession) sesion)
                .contentType(MediaType.APPLICATION_JSON)
                .content(proyectoJson(codigo, nombre)));
    }

    private String proyectoJson(String codigo, String nombre) {
        return """
                {
                  "codigo": "%s",
                  "nombre": "%s",
                  "descripcion": "Proyecto funcional del bloque 5",
                  "area": "Gestion investigadora",
                  "sede": "SANTANDER",
                  "estado": "EN_CURSO",
                  "fechaInicio": "2026-06-01",
                  "fechaFin": "2026-12-15"
                }
                """.formatted(codigo, nombre);
    }

    private String proyectoCompletadoJson(String codigo, String nombre) {
        return proyectoJson(codigo, nombre).replace("\"EN_CURSO\"", "\"COMPLETADO\"");
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
