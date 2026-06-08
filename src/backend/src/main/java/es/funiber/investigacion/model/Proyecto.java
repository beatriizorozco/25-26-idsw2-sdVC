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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String codigo;

    @Column(nullable = false, length = 180)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoProyecto estado;

    @Column(length = 1000)
    private String descripcion;

    @Column(length = 120)
    private String area;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private SedeFuniber sede;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinador_id")
    private Usuario coordinador;

    @Column(nullable = false)
    private boolean archivado;

    @Column(name = "fecha_archivado")
    private LocalDateTime fechaArchivado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "archivado_por_id")
    private Usuario archivadoPor;

    @Column(name = "motivo_archivado", length = 500)
    private String motivoArchivado;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "proyecto_investigadores",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> investigadores = new LinkedHashSet<>();

    protected Proyecto() {
    }

    public Proyecto(String codigo, String nombre, EstadoProyecto estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getArea() {
        return area;
    }

    public SedeFuniber getSede() {
        return sede;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Usuario getCoordinador() {
        return coordinador;
    }

    public boolean isArchivado() {
        return archivado;
    }

    public LocalDateTime getFechaArchivado() {
        return fechaArchivado;
    }

    public Usuario getArchivadoPor() {
        return archivadoPor;
    }

    public String getMotivoArchivado() {
        return motivoArchivado;
    }

    public Set<Usuario> getInvestigadores() {
        return Set.copyOf(investigadores);
    }

    public void agregarInvestigador(Usuario investigador) {
        if (!participa(investigador)) {
            investigadores.add(investigador);
        }
    }

    public void retirarInvestigador(Usuario investigador) {
        investigadores.removeIf(usuario -> usuario.getId().equals(investigador.getId()));
    }

    public void actualizar(
            String codigo,
            String nombre,
            String descripcion,
            String area,
            SedeFuniber sede,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            EstadoProyecto estado,
            Usuario coordinador) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.area = area;
        this.sede = sede;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.coordinador = coordinador;
    }

    public void archivar(Usuario responsable, String motivo) {
        estado = EstadoProyecto.COMPLETADO;
        archivado = true;
        fechaArchivado = LocalDateTime.now();
        archivadoPor = responsable;
        motivoArchivado = motivo;
    }

    public boolean estaCompletado() {
        return estado == EstadoProyecto.COMPLETADO;
    }

    public boolean participa(Usuario investigador) {
        return investigador.getId() != null
                && investigadores.stream().anyMatch(usuario -> usuario.getId().equals(investigador.getId()));
    }
}
