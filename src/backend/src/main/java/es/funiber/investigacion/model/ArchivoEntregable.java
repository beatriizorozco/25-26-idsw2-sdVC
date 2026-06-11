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
@Table(name = "archivos_entregable")
public class ArchivoEntregable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entregable_id", nullable = false)
    private Entregable entregable;

    @Column(nullable = false)
    private int version;

    @Column(nullable = false, length = 240)
    private String nombre;

    @Column(name = "tipo_contenido", nullable = false, length = 160)
    private String tipoContenido;

    @Column(nullable = false)
    private long tamano;

    @JdbcTypeCode(SqlTypes.VARBINARY)
    @Column(nullable = false)
    private byte[] contenido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subido_por_id", nullable = false)
    private Usuario subidoPor;

    @Column(name = "fecha_subida", nullable = false)
    private LocalDateTime fechaSubida;

    protected ArchivoEntregable() {
    }

    public ArchivoEntregable(
            Entregable entregable,
            int version,
            String nombre,
            String tipoContenido,
            byte[] contenido,
            Usuario subidoPor) {
        this.entregable = entregable;
        this.version = version;
        this.nombre = nombre;
        this.tipoContenido = tipoContenido;
        this.contenido = contenido;
        this.tamano = contenido.length;
        this.subidoPor = subidoPor;
        this.fechaSubida = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Entregable getEntregable() { return entregable; }
    public int getVersion() { return version; }
    public String getNombre() { return nombre; }
    public String getTipoContenido() { return tipoContenido; }
    public long getTamano() { return tamano; }
    public byte[] getContenido() { return contenido; }
    public Usuario getSubidoPor() { return subidoPor; }
    public LocalDateTime getFechaSubida() { return fechaSubida; }
}
