package es.funiber.investigacion.repository;

import es.funiber.investigacion.model.EstadoProyecto;
import es.funiber.investigacion.model.Proyecto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    @EntityGraph(attributePaths = "investigadores")
    Optional<Proyecto> findByCodigo(String codigo);

    List<Proyecto> findByEstadoOrderByNombreAsc(EstadoProyecto estado);
}
