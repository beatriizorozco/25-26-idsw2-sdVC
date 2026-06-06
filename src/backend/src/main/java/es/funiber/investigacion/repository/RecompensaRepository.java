package es.funiber.investigacion.repository;

import es.funiber.investigacion.model.Proyecto;
import es.funiber.investigacion.model.Recompensa;
import es.funiber.investigacion.model.TipoRecompensa;
import es.funiber.investigacion.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {

    List<Recompensa> findAllByOrderByFechaCreacionDesc();

    List<Recompensa> findByBeneficiarioOrderByFechaCreacionDesc(Usuario beneficiario);

    boolean existsByProyectoAndBeneficiarioAndTipo(
            Proyecto proyecto,
            Usuario beneficiario,
            TipoRecompensa tipo);

    boolean existsByProyectoAndBeneficiarioAndTipoAndIdNot(
            Proyecto proyecto,
            Usuario beneficiario,
            TipoRecompensa tipo,
            Long id);
}
