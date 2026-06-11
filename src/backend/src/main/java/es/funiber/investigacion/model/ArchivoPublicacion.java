package es.funiber.investigacion.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "archivos_publicacion")
public class ArchivoPublicacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;
    @Column(nullable = false, length = 240)
    private String nombre;
    @Column(name = "tipo_contenido", nullable = false, length = 160)
    private String tipoContenido;
    @Column(nullable = false)
    private long tamano;
    @JdbcTypeCode(SqlTypes.VARBINARY) @Column(nullable = false)
    private byte[] contenido;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "subido_por_id")
    private Usuario subidoPor;
    @Column(name = "fecha_subida", nullable = false)
    private LocalDateTime fechaSubida;

    protected ArchivoPublicacion() {}
    public ArchivoPublicacion(Publicacion publicacion, String nombre, String tipoContenido, byte[] contenido, Usuario subidoPor) {
        this.publicacion = publicacion; this.nombre = nombre; this.tipoContenido = tipoContenido;
        this.contenido = contenido; this.tamano = contenido.length; this.subidoPor = subidoPor; this.fechaSubida = LocalDateTime.now();
    }
    public Long getId() { return id; }
    public Publicacion getPublicacion() { return publicacion; }
    public String getNombre() { return nombre; }
    public String getTipoContenido() { return tipoContenido; }
    public long getTamano() { return tamano; }
    public byte[] getContenido() { return contenido; }
    public Usuario getSubidoPor() { return subidoPor; }
    public LocalDateTime getFechaSubida() { return fechaSubida; }
}
