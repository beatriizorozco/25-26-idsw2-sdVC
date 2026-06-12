package es.funiber.investigacion.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.funiber.investigacion.dto.DatosConvocatoriaRequest;
import es.funiber.investigacion.dto.FuenteConvocatoriaRequest;
import es.funiber.investigacion.model.DatosConvocatoria;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ImportadorJsonConvocatoria implements ImportadorConvocatoria {
    private final ObjectMapper mapper;
    public ImportadorJsonConvocatoria(ObjectMapper mapper) { this.mapper = mapper; }
    @Override public boolean admite(FuenteConvocatoriaRequest f) {
        return f != null && f.contenido() != null && f.contenido().trim().startsWith("{");
    }
    @Override public DatosConvocatoria extraer(FuenteConvocatoriaRequest f) {
        try {
            DatosConvocatoriaRequest datos = mapper.readValue(f.contenido(), DatosConvocatoriaRequest.class);
            String referencia = texto(datos.referenciaExterna(), f.referencia());
            String tipo = texto(datos.tipoFuente(), f.tipo());
            return new DatosConvocatoria(datos.titulo(), datos.entidadConvocante(), datos.area(), datos.estado(),
                    datos.fechaApertura(), datos.fechaCierre(), datos.descripcion(), datos.requisitos(),
                    datos.criteriosEvaluacion(), datos.dotacion(), datos.contacto(), referencia, tipo);
        } catch (Exception e) {
            throw new IllegalArgumentException("El contenido JSON de la convocatoria no es valido.");
        }
    }
    private String texto(String preferido, String respaldo) { return preferido == null || preferido.isBlank() ? respaldo : preferido; }
}
