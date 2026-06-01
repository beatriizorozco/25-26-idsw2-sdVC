package es.funiber.investigacion.service;

import es.funiber.investigacion.exception.CredencialesIncorrectasException;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario autenticar(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(CredencialesIncorrectasException::new);

        if (!usuario.isActivo() || !passwordEncoder.matches(contrasena, usuario.getContrasenaHash())) {
            throw new CredencialesIncorrectasException();
        }

        return usuario;
    }
}

