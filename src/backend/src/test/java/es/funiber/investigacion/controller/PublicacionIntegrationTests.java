package es.funiber.investigacion.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import org.springframework.test.web.servlet.*;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest @AutoConfigureMockMvc @ActiveProfiles("test") @Transactional
@Sql(statements = {
        "UPDATE usuarios SET activo = TRUE WHERE nombre_usuario IN ('coordinador', 'investigador.barcelona', 'docente.santander')"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PublicacionIntegrationTests {
    @Autowired MockMvc mockMvc;

    @Test void investigadorCreaRespondeEditaYRetiraSuPublicacion() throws Exception {
        HttpSession sesion = login("investigador.barcelona", "barcelona123");
        MvcResult creada = guardar(sesion, "/api/publicaciones", "POST", "Hallazgo inicial")
                .andExpect(status().isCreated()).andExpect(jsonPath("$.propia").value(true)).andReturn();
        Number id = com.jayway.jsonpath.JsonPath.read(creada.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(post("/api/publicaciones/{id}/respuestas", id).session((MockHttpSession) sesion).with(csrf())
                .contentType(MediaType.APPLICATION_JSON).content("{\"contenido\":\"Interesante resultado\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.respuestas[0].contenido").value("Interesante resultado"));
        guardar(sesion, "/api/publicaciones/" + id, "PATCH", "Hallazgo revisado").andExpect(status().isOk());
        mockMvc.perform(delete("/api/publicaciones/{id}", id).session((MockHttpSession) sesion).with(csrf())
                .contentType(MediaType.APPLICATION_JSON).content("{\"motivo\":\"Contenido sustituido\"}")).andExpect(status().isNoContent());
        mockMvc.perform(get("/api/publicaciones/me").session((MockHttpSession) sesion))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].titulo", not(hasItem("Hallazgo revisado"))));
    }

    @Test void investigadorNoEditaPublicacionAjenaPeroCoordinadorSi() throws Exception {
        HttpSession autor = login("investigador.barcelona", "barcelona123");
        HttpSession otro = login("docente.santander", "docente123");
        HttpSession coordinador = login("coordinador", "coordinador123");
        MvcResult creada = guardar(autor, "/api/publicaciones", "POST", "Publicacion protegida").andReturn();
        Number id = com.jayway.jsonpath.JsonPath.read(creada.getResponse().getContentAsString(), "$.id");
        guardar(otro, "/api/publicaciones/" + id, "PATCH", "Cambio ajeno").andExpect(status().isForbidden());
        guardar(coordinador, "/api/publicaciones/" + id, "PATCH", "Cambio coordinado").andExpect(status().isOk());
    }

    private ResultActions guardar(HttpSession sesion, String ruta, String metodo, String titulo) throws Exception {
        MockMultipartFile datos = new MockMultipartFile("datos", "", MediaType.APPLICATION_JSON_VALUE,
                ("{\"titulo\":\"" + titulo + "\",\"contenido\":\"Contenido de prueba funcional\"}").getBytes());
        return mockMvc.perform(multipart(ruta).file(datos).file(new MockMultipartFile("archivo", "nota.txt", "text/plain", "contenido".getBytes()))
                .with(request -> { request.setMethod(metodo); return request; }).with(csrf()).session((MockHttpSession) sesion));
    }
    private HttpSession login(String usuario, String clave) throws Exception {
        return mockMvc.perform(post("/api/auth/login").with(csrf()).contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuario\":\"" + usuario + "\",\"contrasena\":\"" + clave + "\"}"))
                .andExpect(status().isOk()).andReturn().getRequest().getSession(false);
    }
}
