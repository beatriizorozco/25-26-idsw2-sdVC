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
import jakarta.persistence.Table;
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

    public Set<Usuario> getInvestigadores() {
        return Set.copyOf(investigadores);
    }

    public void agregarInvestigador(Usuario investigador) {
        if (!participa(investigador)) {
            investigadores.add(investigador);
        }
    }

    public boolean estaCompletado() {
        return estado == EstadoProyecto.COMPLETADO;
    }

    public boolean participa(Usuario investigador) {
        return investigador.getId() != null
                && investigadores.stream().anyMatch(usuario -> usuario.getId().equals(investigador.getId()));
    }
}
