package es.funiber.investigacion.repository;

import es.funiber.investigacion.model.MovimientoParticipacionProyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoParticipacionProyectoRepository
        extends JpaRepository<MovimientoParticipacionProyecto, Long> {
}
