package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.FuenteConvocatoriaRequest;
import es.funiber.investigacion.model.DatosConvocatoria;

public interface ImportadorConvocatoria {
    boolean admite(FuenteConvocatoriaRequest fuente);
    DatosConvocatoria extraer(FuenteConvocatoriaRequest fuente);
}
