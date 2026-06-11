package es.funiber.investigacion.repository;

import es.funiber.investigacion.model.ArchivoEntregable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoEntregableRepository extends JpaRepository<ArchivoEntregable, Long> {
    List<ArchivoEntregable> findByEntregableIdOrderByVersionDesc(Long entregableId);
    Optional<ArchivoEntregable> findFirstByEntregableIdOrderByVersionDesc(Long entregableId);
}
