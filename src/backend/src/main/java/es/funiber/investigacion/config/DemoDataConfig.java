package es.funiber.investigacion.config;

import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoDataConfig {

    @Bean
    CommandLineRunner crearUsuariosDemo(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            crearSiNoExiste(usuarioRepository, passwordEncoder, "coordinador", "coordinador123", Rol.COORDINADOR);
            crearSiNoExiste(usuarioRepository, passwordEncoder, "investigador", "investigador123", Rol.INVESTIGADOR);
        };
    }

    private void crearSiNoExiste(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            String nombreUsuario,
            String contrasena,
            Rol rol) {
        if (usuarioRepository.findByNombreUsuario(nombreUsuario).isEmpty()) {
            usuarioRepository.save(new Usuario(
                    nombreUsuario,
                    passwordEncoder.encode(contrasena),
                    rol,
                    true));
        }
    }
}

