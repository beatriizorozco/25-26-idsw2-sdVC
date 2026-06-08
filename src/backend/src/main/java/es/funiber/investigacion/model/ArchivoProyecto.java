package es.funiber.investigacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "archivos_proyecto")
public class ArchivoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @Column(nullable = false, length = 240)
    private String nombre;

    @Column(name = "tipo_contenido", nullable = false, length = 160)
    private String tipoContenido;

    @Column(nullable = false)
    private long tamano;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subido_por_id", nullable = false)
    private Usuario subidoPor;

    @Column(name = "fecha_subida", nullable = false)
    private LocalDateTime fechaSubida;

    @JdbcTypeCode(SqlTypes.VARBINARY)
    @Column(nullable = false)
    private byte[] contenido;

    protected ArchivoProyecto() {
    }

    public ArchivoProyecto(
            Proyecto proyecto,
            String nombre,
            String tipoContenido,
            Usuario subidoPor,
            byte[] contenido) {
        this.proyecto = proyecto;
        this.nombre = nombre;
        this.tipoContenido = tipoContenido;
        this.subidoPor = subidoPor;
        this.contenido = contenido;
        this.tamano = contenido.length;
        this.fechaSubida = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Proyecto getProyecto() { return proyecto; }
    public String getNombre() { return nombre; }
    public String getTipoContenido() { return tipoContenido; }
    public long getTamano() { return tamano; }
    public Usuario getSubidoPor() { return subidoPor; }
    public LocalDateTime getFechaSubida() { return fechaSubida; }
    public byte[] getContenido() { return contenido; }
}
