package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.FuenteConvocatoriaRequest;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RegistroImportadoresConvocatoria {
    private final List<ImportadorConvocatoria> importadores;
    public RegistroImportadoresConvocatoria(List<ImportadorConvocatoria> importadores) { this.importadores = importadores; }
    public ImportadorConvocatoria obtenerCompatible(FuenteConvocatoriaRequest fuente) {
        return importadores.stream().filter(i -> i.admite(fuente)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No existe un importador compatible con la fuente indicada."));
    }
}
