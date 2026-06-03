package es.funiber.investigacion.repository;

import es.funiber.investigacion.model.EstadoSolicitudEliminacion;
import es.funiber.investigacion.model.SolicitudEliminacionPerfil;
import es.funiber.investigacion.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudEliminacionPerfilRepository extends JpaRepository<SolicitudEliminacionPerfil, Long> {

    boolean existsByUsuarioAndEstado(Usuario usuario, EstadoSolicitudEliminacion estado);

    List<SolicitudEliminacionPerfil> findByEstadoOrderByFechaCreacionDesc(EstadoSolicitudEliminacion estado);
}
