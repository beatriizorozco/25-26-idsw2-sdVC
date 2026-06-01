package es.funiber.investigacion.dto;

import es.funiber.investigacion.model.Rol;
import java.util.List;

public record PanelPrincipalResponse(Rol rol, List<AccionPanel> acciones) {
}

