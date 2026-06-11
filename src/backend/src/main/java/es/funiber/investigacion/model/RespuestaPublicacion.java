package es.funiber.investigacion.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas_publicacion")
public class RespuestaPublicacion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "publicacion_id")
    private Publicacion publicacion;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "autor_id")
    private Usuario autor;
    @Column(nullable = false, length = 1500)
    private String contenido;
    @Column(nullable = false)
    private LocalDateTime fecha;

    protected RespuestaPublicacion() {}
    public RespuestaPublicacion(Publicacion publicacion, Usuario autor, String contenido) {
        this.publicacion = publicacion;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = LocalDateTime.now();
    }
    public Long getId() { return id; }
    public Usuario getAutor() { return autor; }
    public String getContenido() { return contenido; }
    public LocalDateTime getFecha() { return fecha; }
}
