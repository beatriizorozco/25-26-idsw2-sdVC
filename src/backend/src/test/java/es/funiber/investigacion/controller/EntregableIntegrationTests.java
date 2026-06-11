package es.funiber.investigacion.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import es.funiber.investigacion.repository.ProyectoRepository;
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
        "UPDATE usuarios SET activo = TRUE WHERE nombre_usuario IN ('coordinador', 'investigador.barcelona')"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class EntregableIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Test
    void participanteCreaVersionaYRetiraSuEntregable() throws Exception {
        HttpSession investigador = iniciarSesion("investigador.barcelona", "barcelona123");
        Long proyectoId = proyectoRepository.findByCodigo("PRY-BCN-COM-01").orElseThrow().getId();

        MvcResult creado = crearEntregable(investigador, proyectoId, "Informe inicial", "v1.txt")
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.propio").value(true))
                .andExpect(jsonPath("$.archivos[0].version").value(1))
                .andReturn();
        Number id = com.jayway.jsonpath.JsonPath.read(creado.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(multipart("/api/entregables/{id}", id)
                        .file(datos("Informe actualizado"))
                        .file(archivo("v2.txt"))
                        .with(request -> { request.setMethod("PATCH"); return request; })
                        .with(csrf())
                        .session((MockHttpSession) investigador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.archivos[0].version").value(2));

        mockMvc.perform(patch("/api/entregables/{id}/retirada", id)
                        .with(csrf())
                        .session((MockHttpSession) investigador)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"motivo\":\"Version sustituida\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/proyectos/{id}/entregables", proyectoId)
                        .session((MockHttpSession) investigador))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].titulo", not(hasItem("Informe actualizado"))));
    }

    @Test
    void participanteNoPuedeEditarEntregableAjeno() throws Exception {
        HttpSession coordinador = iniciarSesion("coordinador", "coordinador123");
        HttpSession investigador = iniciarSesion("investigador.barcelona", "barcelona123");
        Long proyectoId = proyectoRepository.findByCodigo("PRY-BCN-COM-01").orElseThrow().getId();
        MvcResult creado = crearEntregable(coordinador, proyectoId, "Entregable coordinado", "coord.txt")
                .andExpect(status().isCreated())
                .andReturn();
        Number id = com.jayway.jsonpath.JsonPath.read(creado.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(multipart("/api/entregables/{id}", id)
                        .file(datos("Cambio no permitido"))
                        .with(request -> { request.setMethod("PATCH"); return request; })
                        .with(csrf())
                        .session((MockHttpSession) investigador))
                .andExpect(status().isForbidden());
    }

    private org.springframework.test.web.servlet.ResultActions crearEntregable(
            HttpSession sesion,
            Long proyectoId,
            String titulo,
            String nombreArchivo) throws Exception {
        return mockMvc.perform(multipart("/api/proyectos/{id}/entregables", proyectoId)
                .file(datos(titulo))
                .file(archivo(nombreArchivo))
                .with(csrf())
                .session((MockHttpSession) sesion));
    }

    private MockMultipartFile datos(String titulo) {
        return new MockMultipartFile(
                "datos",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                ("{\"titulo\":\"" + titulo + "\",\"descripcion\":\"Prueba funcional\",\"estado\":\"PRESENTADO\"}")
                        .getBytes());
    }

    private MockMultipartFile archivo(String nombre) {
        return new MockMultipartFile("archivo", nombre, "text/plain", "contenido".getBytes());
    }

    private HttpSession iniciarSesion(String usuario, String contrasena) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"usuario":"%s","contrasena":"%s"}
                                """.formatted(usuario, contrasena)))
                .andExpect(status().isOk())
                .andReturn();
        return result.getRequest().getSession(false);
    }
}
