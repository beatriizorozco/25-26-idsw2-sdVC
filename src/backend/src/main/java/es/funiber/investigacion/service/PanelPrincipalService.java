package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.AccionPanel;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PanelPrincipalService {

    private final UsuarioRepository usuarioRepository;

    public PanelPrincipalService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<AccionPanel> obtenerAccionesDisponibles(String nombreUsuario, Rol rol) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario).orElse(null);
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

        if (rol == Rol.COORDINADOR || (usuario != null && usuario.getRol() == Rol.INVESTIGADOR)) {
            acciones.add(new AccionPanel("recompensas", "Recompensas", "/recompensas"));
        }
        return List.copyOf(acciones);
    }
}

