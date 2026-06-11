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

@Entity
@Table(name = "entregables")
public class Entregable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    @Column(nullable = false, length = 180)
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    @Column(nullable = false, length = 30)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private boolean retirado;

    @Column(name = "fecha_retirada")
    private LocalDateTime fechaRetirada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retirado_por_id")
    private Usuario retiradoPor;

    @Column(name = "motivo_retirada", length = 500)
    private String motivoRetirada;

    protected Entregable() {
    }

    public Entregable(Proyecto proyecto, String titulo, String descripcion, Usuario autor) {
        this.proyecto = proyecto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autor = autor;
        this.estado = "PRESENTADO";
        this.fechaCreacion = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Proyecto getProyecto() { return proyecto; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getEstado() { return estado; }
    public Usuario getAutor() { return autor; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public boolean isRetirado() { return retirado; }
    public LocalDateTime getFechaRetirada() { return fechaRetirada; }
    public String getMotivoRetirada() { return motivoRetirada; }

    public void actualizar(String titulo, String descripcion, String estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public void retirar(Usuario responsable, String motivo) {
        retirado = true;
        fechaRetirada = LocalDateTime.now();
        retiradoPor = responsable;
        motivoRetirada = motivo;
    }
}
