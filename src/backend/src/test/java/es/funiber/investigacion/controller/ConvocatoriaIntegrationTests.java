package es.funiber.investigacion.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.*;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest @AutoConfigureMockMvc @ActiveProfiles("test") @Transactional
class ConvocatoriaIntegrationTests {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;

    @Test void coordinadorConsultaFiltraYAbreConvocatorias() throws Exception {
        HttpSession sesion = login("coordinador", "coordinador123");
        MvcResult lista = mockMvc.perform(get("/api/convocatorias?area=movilidad").session((MockHttpSession) sesion))
                .andExpect(status().isOk()).andExpect(jsonPath("$[*].area", hasItem("Movilidad"))).andReturn();
        Number id = com.jayway.jsonpath.JsonPath.read(lista.getResponse().getContentAsString(), "$[0].id");
        mockMvc.perform(get("/api/convocatorias/{id}", id).session((MockHttpSession) sesion))
                .andExpect(status().isOk()).andExpect(jsonPath("$.titulo").value("Programa iberoamericano de movilidad investigadora"));
    }

    @Test void coordinadorPrevisualizaYConfirmaSinPersistirAlCancelar() throws Exception {
        HttpSession sesion = login("coordinador", "coordinador123");
        String fuente = "{\"tipo\":\"ENLACE\",\"referencia\":\"https://ejemplo.org/convocatoria-nueva\"}";
        MvcResult preview = mockMvc.perform(post("/api/convocatorias/importaciones/previsualizacion").with(csrf())
                .session((MockHttpSession) sesion).contentType(MediaType.APPLICATION_JSON).content(fuente))
                .andExpect(status().isOk()).andExpect(jsonPath("$.datos.titulo").value("Convocatoria nueva")).andReturn();
        mockMvc.perform(get("/api/convocatorias").param("texto", "convocatoria nueva").session((MockHttpSession) sesion))
                .andExpect(jsonPath("$.length()").value(0));
        Map<String, Object> datosPreview = com.jayway.jsonpath.JsonPath.read(preview.getResponse().getContentAsString(), "$.datos");
        String datos = mapper.writeValueAsString(datosPreview);
        mockMvc.perform(post("/api/convocatorias/importaciones").with(csrf()).session((MockHttpSession) sesion)
                .contentType(MediaType.APPLICATION_JSON).content("{\"datos\":" + datos + "}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.referenciaExterna").value("https://ejemplo.org/convocatoria-nueva"));
        mockMvc.perform(post("/api/convocatorias/importaciones").with(csrf()).session((MockHttpSession) sesion)
                .contentType(MediaType.APPLICATION_JSON).content("{\"datos\":" + datos + "}"))
                .andExpect(status().isCreated());
        mockMvc.perform(get("/api/convocatorias").param("texto", "convocatoria nueva").session((MockHttpSession) sesion))
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test void investigadorNoPuedeConsultarNiImportarConvocatorias() throws Exception {
        HttpSession sesion = login("investigador.barcelona", "barcelona123");
        mockMvc.perform(get("/api/convocatorias").session((MockHttpSession) sesion)).andExpect(status().isForbidden());
        mockMvc.perform(post("/api/convocatorias/importaciones/previsualizacion").with(csrf()).session((MockHttpSession) sesion)
                .contentType(MediaType.APPLICATION_JSON).content("{\"tipo\":\"ENLACE\",\"referencia\":\"https://ejemplo.org/no\"}"))
                .andExpect(status().isForbidden());
    }

    private HttpSession login(String usuario, String clave) throws Exception {
        return mockMvc.perform(post("/api/auth/login").with(csrf()).contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuario\":\"" + usuario + "\",\"contrasena\":\"" + clave + "\"}"))
                .andExpect(status().isOk()).andReturn().getRequest().getSession(false);
    }
}
