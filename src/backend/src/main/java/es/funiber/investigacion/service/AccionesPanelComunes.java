package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.AccionPanel;
import es.funiber.investigacion.model.Usuario;
import java.util.List;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AccionesPanelComunes implements ProveedorAccionesPanel {
    @Override
    public boolean aplica(Usuario usuario) {
        return true;
    }

    @Override
    public List<AccionPanel> acciones() {
        return List.of(
                new AccionPanel("perfil", "Perfil", "/perfil"),
                new AccionPanel("carga-trabajo", "Carga de trabajo", "/carga-trabajo"),
                new AccionPanel("proyectos", "Proyectos", "/proyectos"),
                new AccionPanel("investigadores", "Investigadores", "/investigadores"),
                new AccionPanel("mis-publicaciones", "Mis publicaciones", "/mis-publicaciones"),
                new AccionPanel("publicaciones", "Publicaciones", "/publicaciones"),
                new AccionPanel("recompensas", "Recompensas", "/recompensas"));
    }
}
