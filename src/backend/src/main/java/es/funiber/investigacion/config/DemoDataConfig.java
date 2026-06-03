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
            crearSiNoExiste(
                    usuarioRepository,
                    passwordEncoder,
                    "coordinador",
                    "coordinador123",
                    Rol.COORDINADOR,
                    "Coordinador FUNIBER",
                    "coordinador@funiber.org",
                    "Coordinación de investigación",
                    "Gestión académica e investigación",
                    "Perfil coordinador con acceso a gestión global de la plataforma.");
            crearSiNoExiste(
                    usuarioRepository,
                    passwordEncoder,
                    "investigador",
                    "investigador123",
                    Rol.INVESTIGADOR,
                    "Investigador FUNIBER",
                    "investigador@funiber.org",
                    "Investigación",
                    "Producción científica",
                    "Perfil investigador con acceso a sus proyectos, publicaciones y entregables.");
        };
    }

    private void crearSiNoExiste(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            String nombreUsuario,
            String contrasena,
            Rol rol,
            String nombreCompleto,
            String email,
            String unidad,
            String lineaInvestigacion,
            String biografia) {
        if (usuarioRepository.findByNombreUsuario(nombreUsuario).isEmpty()) {
            Usuario usuario = new Usuario(
                    nombreUsuario,
                    passwordEncoder.encode(contrasena),
                    rol,
                    true);
            usuario.actualizarPerfil(nombreCompleto, email, unidad, lineaInvestigacion, biografia);
            usuarioRepository.save(usuario);
        }
    }
}

