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
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "recompensas",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_recompensa_proyecto_beneficiario_tipo",
                columnNames = {"proyecto_id", "beneficiario_id", "tipo"}))
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiario_id", nullable = false)
    private Usuario beneficiario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoRecompensa tipo;

    @Column(nullable = false, length = 240)
    private String concepto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    protected Recompensa() {
    }

    public Recompensa(
            Proyecto proyecto,
            Usuario beneficiario,
            TipoRecompensa tipo,
            String concepto,
            BigDecimal valor) {
        this.proyecto = proyecto;
        this.fechaCreacion = LocalDateTime.now();
        actualizar(beneficiario, tipo, concepto, valor);
    }

    public Long getId() {
        return id;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public Usuario getBeneficiario() {
        return beneficiario;
    }

    public TipoRecompensa getTipo() {
        return tipo;
    }

    public String getConcepto() {
        return concepto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void actualizar(
            Usuario beneficiario,
            TipoRecompensa tipo,
            String concepto,
            BigDecimal valor) {
        this.beneficiario = beneficiario;
        this.tipo = tipo;
        this.concepto = concepto;
        this.valor = valor;
    }
}
