package es.funiber.investigacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_participacion_proyecto")
public class MovimientoParticipacionProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "investigador_id", nullable = false)
    private Usuario investigador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoMovimientoParticipacion tipo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "responsable_id", nullable = false)
    private Usuario responsable;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 500)
    private String motivo;

    protected MovimientoParticipacionProyecto() {
    }

    public MovimientoParticipacionProyecto(
            Proyecto proyecto,
            Usuario investigador,
            TipoMovimientoParticipacion tipo,
            Usuario responsable,
            String motivo) {
        this.proyecto = proyecto;
        this.investigador = investigador;
        this.tipo = tipo;
        this.responsable = responsable;
        this.motivo = motivo;
        this.fecha = LocalDateTime.now();
    }
}
