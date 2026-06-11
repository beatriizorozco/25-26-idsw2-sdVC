package es.funiber.investigacion.repository;

import es.funiber.investigacion.model.Publicacion;
import es.funiber.investigacion.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    List<Publicacion> findByRetiradaOrderByFechaActualizacionDesc(boolean retirada);
    List<Publicacion> findByAutorAndRetiradaOrderByFechaActualizacionDesc(Usuario autor, boolean retirada);
    Optional<Publicacion> findByIdAndRetirada(Long id, boolean retirada);
}
