package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.AccionPanel;
import es.funiber.investigacion.model.Usuario;
import java.util.List;

public interface ProveedorAccionesPanel {
    boolean aplica(Usuario usuario);
    List<AccionPanel> acciones();
}
