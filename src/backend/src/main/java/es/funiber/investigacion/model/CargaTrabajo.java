package es.funiber.investigacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cargas_trabajo")
public class CargaTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "horas_docencia", nullable = false, precision = 5, scale = 2)
    private BigDecimal horasDocencia;

    @Column(name = "horas_investigacion", nullable = false, precision = 5, scale = 2)
    private BigDecimal horasInvestigacion;

    @Column(name = "horas_gestion_academica", nullable = false, precision = 5, scale = 2)
    private BigDecimal horasGestionAcademica;

    @Column(length = 500)
    private String observaciones;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    protected CargaTrabajo() {
    }

    public CargaTrabajo(Usuario usuario) {
        this.usuario = usuario;
        actualizar(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, "");
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public BigDecimal getHorasDocencia() {
        return horasDocencia;
    }

    public BigDecimal getHorasInvestigacion() {
        return horasInvestigacion;
    }

    public BigDecimal getHorasGestionAcademica() {
        return horasGestionAcademica;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void actualizar(
            BigDecimal horasDocencia,
            BigDecimal horasInvestigacion,
            BigDecimal horasGestionAcademica,
            String observaciones) {
        this.horasDocencia = horasDocencia;
        this.horasInvestigacion = horasInvestigacion;
        this.horasGestionAcademica = horasGestionAcademica;
        this.observaciones = observaciones;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public BigDecimal totalSemanal() {
        return horasDocencia.add(horasInvestigacion).add(horasGestionAcademica);
    }
}
