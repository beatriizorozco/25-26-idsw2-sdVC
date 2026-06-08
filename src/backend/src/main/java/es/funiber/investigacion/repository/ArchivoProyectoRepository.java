package es.funiber.investigacion.repository;

import es.funiber.investigacion.model.ArchivoProyecto;
import es.funiber.investigacion.model.Proyecto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoProyectoRepository extends JpaRepository<ArchivoProyecto, Long> {
    List<ArchivoProyecto> findByProyectoOrderByFechaSubidaDesc(Proyecto proyecto);
}
