package es.funiber.investigacion.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "convocatorias")
public class Convocatoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 220) private String titulo;
    @Column(name = "entidad_convocante", nullable = false, length = 180) private String entidadConvocante;
    @Column(nullable = false, length = 160) private String area;
    @Column(nullable = false, length = 40) private String estado;
    @Column(name = "fecha_apertura") private LocalDate fechaApertura;
    @Column(name = "fecha_cierre") private LocalDate fechaCierre;
    @Column(nullable = false, length = 4000) private String descripcion;
    @Column(nullable = false, length = 4000) private String requisitos;
    @Column(name = "criterios_evaluacion", nullable = false, length = 3000) private String criteriosEvaluacion;
    @Column(nullable = false, length = 180) private String dotacion;
    @Column(nullable = false, length = 240) private String contacto;
    @Column(name = "referencia_externa", nullable = false, unique = true, length = 500) private String referenciaExterna;
    @Column(name = "tipo_fuente", nullable = false, length = 30) private String tipoFuente;
    @Column(nullable = false) private boolean incorporada;
    @Column(name = "fecha_importacion", nullable = false) private LocalDateTime fechaImportacion;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "importada_por_id") private Usuario importadaPor;

    protected Convocatoria() {}

    public Convocatoria(DatosConvocatoria datos, Usuario actor) {
        actualizarImportacion(datos, actor);
    }

    public void actualizar(DatosConvocatoria d) {
        titulo = d.titulo(); entidadConvocante = d.entidadConvocante(); area = d.area(); estado = d.estado();
        fechaApertura = d.fechaApertura(); fechaCierre = d.fechaCierre(); descripcion = d.descripcion();
        requisitos = d.requisitos(); criteriosEvaluacion = d.criteriosEvaluacion(); dotacion = d.dotacion();
        contacto = d.contacto(); referenciaExterna = d.referenciaExterna(); tipoFuente = d.tipoFuente();
    }

    public void actualizarImportacion(DatosConvocatoria datos, Usuario actor) {
        actualizar(datos);
        incorporada = true;
        fechaImportacion = LocalDateTime.now();
        importadaPor = actor;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getEntidadConvocante() { return entidadConvocante; }
    public String getArea() { return area; }
    public String getEstado() { return estado; }
    public LocalDate getFechaApertura() { return fechaApertura; }
    public LocalDate getFechaCierre() { return fechaCierre; }
    public String getDescripcion() { return descripcion; }
    public String getRequisitos() { return requisitos; }
    public String getCriteriosEvaluacion() { return criteriosEvaluacion; }
    public String getDotacion() { return dotacion; }
    public String getContacto() { return contacto; }
    public String getReferenciaExterna() { return referenciaExterna; }
    public String getTipoFuente() { return tipoFuente; }
    public boolean isIncorporada() { return incorporada; }
    public LocalDateTime getFechaImportacion() { return fechaImportacion; }
}
