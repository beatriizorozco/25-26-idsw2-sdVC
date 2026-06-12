package es.funiber.investigacion.service;

import es.funiber.investigacion.exception.AccesoNoPermitidoException;
import es.funiber.investigacion.exception.RecursoNoEncontradoException;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AccesoUsuarioService {

    private final UsuarioRepository usuarios;

    public AccesoUsuarioService(UsuarioRepository usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario buscarActivo(String nombreUsuario) {
        return usuarios.findByNombreUsuario(nombreUsuario)
                .filter(Usuario::isActivo)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el perfil solicitado."));
    }

    public Usuario buscarActivo(Long id) {
        return usuarios.findById(id)
                .filter(Usuario::isActivo)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontro el perfil solicitado."));
    }

    public Usuario buscarInvestigadorActivo(Long id) {
        Usuario usuario = buscarActivo(id);
        if (usuario.getRol() != Rol.INVESTIGADOR) {
            throw new RecursoNoEncontradoException("No se encontro el investigador solicitado.");
        }
        return usuario;
    }

    public Usuario exigirRol(String nombreUsuario, Rol rol) {
        Usuario usuario = buscarActivo(nombreUsuario);
        if (usuario.getRol() != rol) {
            throw new AccesoNoPermitidoException();
        }
        return usuario;
    }
}
