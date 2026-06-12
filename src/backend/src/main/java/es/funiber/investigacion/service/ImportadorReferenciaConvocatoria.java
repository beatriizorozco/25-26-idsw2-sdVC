package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.FuenteConvocatoriaRequest;
import es.funiber.investigacion.model.DatosConvocatoria;
import java.time.LocalDate;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(100)
public class ImportadorReferenciaConvocatoria implements ImportadorConvocatoria {
    @Override public boolean admite(FuenteConvocatoriaRequest f) { return f != null && f.referencia() != null && !f.referencia().isBlank(); }
    @Override public DatosConvocatoria extraer(FuenteConvocatoriaRequest f) {
        String referencia = f.referencia().trim();
        String base = referencia.replace('\\', '/');
        base = base.substring(base.lastIndexOf('/') + 1).replaceAll("\\.[^.]+$", "").replace('-', ' ').replace('_', ' ').trim();
        String titulo = base.isBlank() ? "Convocatoria importada" : Character.toUpperCase(base.charAt(0)) + base.substring(1);
        String tipo = f.tipo() == null || f.tipo().isBlank() ? "ENLACE" : f.tipo().trim().toUpperCase();
        return new DatosConvocatoria(titulo, "Entidad externa pendiente de verificacion", "General", "ABIERTA",
                LocalDate.now(), null, "Informacion importada desde " + referencia + ".",
                "Consultar la fuente original.", "Consultar la fuente original.", "Segun convocatoria",
                "Consultar la fuente original.", referencia, tipo);
    }
}
