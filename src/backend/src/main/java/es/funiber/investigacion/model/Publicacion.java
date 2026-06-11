package es.funiber.investigacion.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publicaciones")
public class Publicacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 180)
    private String titulo;
    @Column(nullable = false, length = 4000)
    private String contenido;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "autor_id")
    private Usuario autor;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
    @Column(nullable = false)
    private boolean retirada;
    @Column(name = "fecha_retirada")
    private LocalDateTime fechaRetirada;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "retirado_por_id")
    private Usuario retiradoPor;
    @Column(name = "motivo_retirada", length = 500)
    private String motivoRetirada;

    protected Publicacion() {}

    public Publicacion(String titulo, String contenido, Usuario autor) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.autor = autor;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = fechaCreacion;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getContenido() { return contenido; }
    public Usuario getAutor() { return autor; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public boolean isRetirada() { return retirada; }

    public void actualizar(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void retirar(Usuario responsable, String motivo) {
        retirada = true;
        fechaRetirada = LocalDateTime.now();
        retiradoPor = responsable;
        motivoRetirada = motivo;
    }
}
