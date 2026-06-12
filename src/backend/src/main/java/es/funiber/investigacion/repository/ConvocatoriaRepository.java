package es.funiber.investigacion.repository;

import es.funiber.investigacion.model.Convocatoria;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvocatoriaRepository extends JpaRepository<Convocatoria, Long> {
    List<Convocatoria> findAllByOrderByFechaCierreAsc();
    Optional<Convocatoria> findByReferenciaExterna(String referenciaExterna);
    boolean existsByReferenciaExterna(String referenciaExterna);
}
