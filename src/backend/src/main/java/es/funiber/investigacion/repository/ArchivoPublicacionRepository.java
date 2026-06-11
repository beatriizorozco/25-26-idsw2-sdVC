package es.funiber.investigacion.repository;
import es.funiber.investigacion.model.ArchivoPublicacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ArchivoPublicacionRepository extends JpaRepository<ArchivoPublicacion, Long> {
    List<ArchivoPublicacion> findByPublicacionIdOrderByFechaSubidaDesc(Long publicacionId);
}
