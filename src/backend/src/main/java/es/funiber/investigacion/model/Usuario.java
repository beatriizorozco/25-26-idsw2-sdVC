package es.funiber.investigacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, unique = true, length = 80)
    private String nombreUsuario;

    @Column(name = "contrasena_hash", nullable = false)
    private String contrasenaHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private SedeFuniber sede;

    @Column(nullable = false)
    private boolean activo;

    @Column(name = "nombre_completo", length = 120)
    private String nombreCompleto;

    @Column(length = 120)
    private String email;

    @Column(length = 120)
    private String unidad;

    @Column(name = "linea_investigacion", length = 160)
    private String lineaInvestigacion;

    @Column(length = 500)
    private String biografia;

    protected Usuario() {
    }

    public Usuario(String nombreUsuario, String contrasenaHash, Rol rol, boolean activo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenaHash = contrasenaHash;
        this.rol = rol;
        this.activo = activo;
        this.sede = rol == Rol.COORDINADOR ? SedeFuniber.GLOBAL : SedeFuniber.BARCELONA;
        this.nombreCompleto = nombreUsuario;
    }

    public Long getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public Rol getRol() {
        return rol;
    }

    public SedeFuniber getSede() {
        return sede;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getUnidad() {
        return unidad;
    }

    public String getLineaInvestigacion() {
        return lineaInvestigacion;
    }

    public String getBiografia() {
        return biografia;
    }

    public void actualizarPerfil(
            String nombreCompleto,
            String email,
            String unidad,
            String lineaInvestigacion,
            String biografia) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.unidad = unidad;
        this.lineaInvestigacion = lineaInvestigacion;
        this.biografia = biografia;
    }

    public void actualizarSede(SedeFuniber sede) {
        this.sede = sede;
    }

    public void actualizarContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public void reactivar() {
        this.activo = true;
    }

    public boolean esInvestigadorDocente() {
        return rol == Rol.INVESTIGADOR && sede.esInvestigadorDocente();
    }

    public void desactivar() {
        this.activo = false;
    }
}

