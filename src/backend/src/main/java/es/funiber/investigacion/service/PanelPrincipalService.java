package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.AccionPanel;
import es.funiber.investigacion.model.Rol;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PanelPrincipalService {

    public List<AccionPanel> obtenerAccionesDisponibles(Rol rol) {
        List<AccionPanel> acciones = new ArrayList<>();
        acciones.add(new AccionPanel("perfil", "Perfil", "/perfil"));
        acciones.add(new AccionPanel("carga-trabajo", "Carga de trabajo", "/carga-trabajo"));
        acciones.add(new AccionPanel("proyectos", "Proyectos", "/proyectos"));
        acciones.add(new AccionPanel("investigadores", "Investigadores", "/investigadores"));
        acciones.add(new AccionPanel("mis-publicaciones", "Mis publicaciones", "/mis-publicaciones"));
        acciones.add(new AccionPanel("publicaciones", "Publicaciones", "/publicaciones"));

        if (rol == Rol.COORDINADOR) {
            acciones.add(new AccionPanel("convocatorias", "Convocatorias", "/convocatorias"));
        }

        acciones.add(new AccionPanel("recompensas", "Recompensas", "/recompensas"));
        return List.copyOf(acciones);
    }
}

