package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.AccionPanel;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.Usuario;
import java.util.List;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class AccionesPanelCoordinador implements ProveedorAccionesPanel {
    @Override
    public boolean aplica(Usuario usuario) {
        return usuario.getRol() == Rol.COORDINADOR;
    }

    @Override
    public List<AccionPanel> acciones() {
        return List.of(new AccionPanel("convocatorias", "Convocatorias", "/convocatorias"));
    }
}
