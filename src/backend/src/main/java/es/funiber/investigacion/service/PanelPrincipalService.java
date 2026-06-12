package es.funiber.investigacion.service;

import es.funiber.investigacion.dto.AccionPanel;
import es.funiber.investigacion.model.Usuario;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PanelPrincipalService {

    private final AccesoUsuarioService accesoUsuarios;
    private final List<ProveedorAccionesPanel> proveedores;

    public PanelPrincipalService(AccesoUsuarioService accesoUsuarios, List<ProveedorAccionesPanel> proveedores) {
        this.accesoUsuarios = accesoUsuarios;
        this.proveedores = List.copyOf(proveedores);
    }

    public List<AccionPanel> obtenerAccionesDisponibles(String nombreUsuario) {
        Usuario usuario = accesoUsuarios.buscarActivo(nombreUsuario);
        return proveedores.stream()
                .filter(proveedor -> proveedor.aplica(usuario))
                .flatMap(proveedor -> proveedor.acciones().stream())
                .toList();
    }
}

