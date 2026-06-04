package es.funiber.investigacion.repository;

import es.funiber.investigacion.model.CargaTrabajo;
import es.funiber.investigacion.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargaTrabajoRepository extends JpaRepository<CargaTrabajo, Long> {

    Optional<CargaTrabajo> findByUsuario(Usuario usuario);
}
