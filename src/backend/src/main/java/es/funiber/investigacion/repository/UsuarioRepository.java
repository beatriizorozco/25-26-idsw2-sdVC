package es.funiber.investigacion.repository;

import es.funiber.investigacion.model.Usuario;
import es.funiber.investigacion.model.Rol;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    boolean existsByEmailIgnoreCase(String email);

    long countByRolAndActivoTrue(Rol rol);

    List<Usuario> findByActivoTrueOrderByNombreCompletoAsc();

    List<Usuario> findByRolAndActivoTrueOrderByNombreCompletoAsc(Rol rol);
}

