package es.funiber.investigacion.config;

import es.funiber.investigacion.model.CargaTrabajo;
import es.funiber.investigacion.model.EstadoProyecto;
import es.funiber.investigacion.model.Proyecto;
import es.funiber.investigacion.model.Recompensa;
import es.funiber.investigacion.model.Rol;
import es.funiber.investigacion.model.SedeFuniber;
import es.funiber.investigacion.model.TipoRecompensa;
import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.repository.CargaTrabajoRepository;
import es.funiber.investigacion.repository.ProyectoRepository;
import es.funiber.investigacion.repository.RecompensaRepository;
import es.funiber.investigacion.repository.UsuarioRepository;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoDataConfig {

    @Bean
    CommandLineRunner crearUsuariosDemo(
            UsuarioRepository usuarioRepository,
            CargaTrabajoRepository cargaTrabajoRepository,
            ProyectoRepository proyectoRepository,
            RecompensaRepository recompensaRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            Usuario coordinador = crearSiNoExiste(
                    usuarioRepository,
                    passwordEncoder,
                    "coordinador",
                    "coordinador123",
                    Rol.COORDINADOR,
                    "Coordinador FUNIBER",
                    "coordinador@funiber.org",
                    "Coordinacion de investigacion",
                    "Gestion academica e investigacion",
                    "Perfil coordinador con acceso a gestion global de la plataforma.",
                    SedeFuniber.GLOBAL);
            Usuario investigador = crearSiNoExiste(
                    usuarioRepository,
                    passwordEncoder,
                    "investigador",
                    "investigador123",
                    Rol.INVESTIGADOR,
                    "Investigador FUNIBER",
                    "investigador@funiber.org",
                    "Investigacion",
                    "Produccion cientifica",
                    "Perfil investigador con acceso a sus proyectos, publicaciones y entregables.",
                    SedeFuniber.BARCELONA);
            Usuario docenteSantander = crearSiNoExiste(
                    usuarioRepository,
                    passwordEncoder,
                    "docente.santander",
                    "docente123",
                    Rol.INVESTIGADOR,
                    "Docente Investigador Santander",
                    "docente.santander@funiber.org",
                    "Investigacion y docencia",
                    "Gestion academica e investigacion",
                    "Perfil investigador-docente adscrito a una sede con docencia.",
                    SedeFuniber.SANTANDER);
            Usuario investigadorBarcelona = crearSiNoExiste(
                    usuarioRepository,
                    passwordEncoder,
                    "investigador.barcelona",
                    "barcelona123",
                    Rol.INVESTIGADOR,
                    "Investigador Barcelona",
                    "investigador.barcelona@funiber.org",
                    "Investigacion",
                    "Produccion cientifica",
                    "Perfil investigador adscrito a una sede sin docencia.",
                    SedeFuniber.BARCELONA);

            crearCargaSiNoExiste(cargaTrabajoRepository, coordinador, "2.00", "18.00", "12.00", "Coordinacion general de investigacion.");
            crearCargaSiNoExiste(cargaTrabajoRepository, investigador, "0.00", "24.00", "4.00", "Sede sin docencia investigadora asignada.");
            crearCargaSiNoExiste(cargaTrabajoRepository, docenteSantander, "14.00", "12.00", "3.00", "Carga docente dentro del limite semanal.");
            crearCargaSiNoExiste(cargaTrabajoRepository, investigadorBarcelona, "0.00", "22.00", "2.00", "Investigador de sede Barcelona sin docencia asignada.");

            Proyecto proyectoSantander = crearProyectoSiNoExiste(
                    proyectoRepository,
                    "PRY-SAN-COM-01",
                    "Modelo de gestion investigadora",
                    EstadoProyecto.COMPLETADO,
                    docenteSantander);
            crearProyectoSiNoExiste(
                    proyectoRepository,
                    "PRY-SAN-COM-02",
                    "Innovacion docente aplicada",
                    EstadoProyecto.COMPLETADO,
                    docenteSantander);
            Proyecto proyectoBarcelona = crearProyectoSiNoExiste(
                    proyectoRepository,
                    "PRY-BCN-COM-01",
                    "Observatorio de produccion cientifica",
                    EstadoProyecto.COMPLETADO,
                    investigador,
                    investigadorBarcelona);
            crearProyectoLibreSiNoExiste(
                    proyectoRepository,
                    "PRY-SAN-01",
                    "Optimizacion de gestion investigadora",
                    "Gestion academica",
                    SedeFuniber.SANTANDER,
                    coordinador);
            crearProyectoLibreSiNoExiste(
                    proyectoRepository,
                    "PRY-SAN-02",
                    "Indicadores de productividad cientifica",
                    "Produccion cientifica",
                    SedeFuniber.SANTANDER,
                    coordinador);
            crearRecompensaSiNoExiste(
                    recompensaRepository,
                    proyectoSantander,
                    docenteSantander,
                    TipoRecompensa.REDUCCION_DOCENTE,
                    "Reduccion docente para el siguiente cuatrimestre",
                    "4.00");
            crearRecompensaSiNoExiste(
                    recompensaRepository,
                    proyectoBarcelona,
                    investigadorBarcelona,
                    TipoRecompensa.ECONOMICA,
                    "Reconocimiento economico por proyecto completado",
                    "450.00");
        };
    }

    private Usuario crearSiNoExiste(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            String nombreUsuario,
            String contrasena,
            Rol rol,
            String nombreCompleto,
            String email,
            String unidad,
            String lineaInvestigacion,
            String biografia,
            SedeFuniber sede) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .map(usuario -> {
                    usuario.actualizarContrasenaHash(passwordEncoder.encode(contrasena));
                    usuario.actualizarPerfil(nombreCompleto, email, unidad, lineaInvestigacion, biografia);
                    usuario.actualizarSede(sede);
                    return usuarioRepository.save(usuario);
                })
                .orElseGet(() -> {
                    Usuario usuario = new Usuario(
                            nombreUsuario,
                            passwordEncoder.encode(contrasena),
                            rol,
                            true);
                    usuario.actualizarPerfil(nombreCompleto, email, unidad, lineaInvestigacion, biografia);
                    usuario.actualizarSede(sede);
                    return usuarioRepository.save(usuario);
                });
    }

    private void crearCargaSiNoExiste(
            CargaTrabajoRepository cargaTrabajoRepository,
            Usuario usuario,
            String horasDocencia,
            String horasInvestigacion,
            String horasGestionAcademica,
            String observaciones) {
        if (cargaTrabajoRepository.findByUsuario(usuario).isEmpty()) {
            CargaTrabajo cargaTrabajo = new CargaTrabajo(usuario);
            cargaTrabajo.actualizar(
                    new BigDecimal(horasDocencia),
                    new BigDecimal(horasInvestigacion),
                    new BigDecimal(horasGestionAcademica),
                    observaciones);
            cargaTrabajoRepository.save(cargaTrabajo);
        }
    }

    private Proyecto crearProyectoSiNoExiste(
            ProyectoRepository proyectoRepository,
            String codigo,
            String nombre,
            EstadoProyecto estado,
            Usuario... investigadores) {
        Proyecto proyecto = proyectoRepository.findByCodigo(codigo)
                .orElseGet(() -> new Proyecto(codigo, nombre, estado));
        for (Usuario investigador : investigadores) {
            proyecto.agregarInvestigador(investigador);
        }
        return proyectoRepository.save(proyecto);
    }

    private void crearRecompensaSiNoExiste(
            RecompensaRepository recompensaRepository,
            Proyecto proyecto,
            Usuario beneficiario,
            TipoRecompensa tipo,
            String concepto,
            String valor) {
        if (beneficiario.isActivo()
                && !recompensaRepository.existsByProyectoAndBeneficiarioAndTipo(proyecto, beneficiario, tipo)) {
            recompensaRepository.save(new Recompensa(
                    proyecto,
                    beneficiario,
                    tipo,
                    concepto,
                    new BigDecimal(valor)));
        }
    }

    private void crearProyectoLibreSiNoExiste(
            ProyectoRepository proyectoRepository,
            String codigo,
            String nombre,
            String area,
            SedeFuniber sede,
            Usuario coordinador) {
        Proyecto proyecto = proyectoRepository.findByCodigo(codigo)
                .orElseGet(() -> new Proyecto(codigo, nombre, EstadoProyecto.EN_CURSO));
        proyecto.actualizar(codigo, nombre, "", area, sede, null, null, EstadoProyecto.EN_CURSO, coordinador);
        proyectoRepository.save(proyecto);
    }
}
