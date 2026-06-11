package es.funiber.investigacion.repository;

import es.funiber.investigacion.model.Entregable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregableRepository extends JpaRepository<Entregable, Long> {
    List<Entregable> findByProyectoIdAndRetiradoOrderByFechaCreacionDesc(Long proyectoId, boolean retirado);
    Optional<Entregable> findByIdAndRetirado(Long id, boolean retirado);
}
