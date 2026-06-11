package es.funiber.investigacion.repository;
import es.funiber.investigacion.model.RespuestaPublicacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RespuestaPublicacionRepository extends JpaRepository<RespuestaPublicacion, Long> {
    List<RespuestaPublicacion> findByPublicacionIdOrderByFecha(Long publicacionId);
}
