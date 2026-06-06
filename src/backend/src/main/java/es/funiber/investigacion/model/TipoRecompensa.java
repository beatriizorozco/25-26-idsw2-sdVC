package es.funiber.investigacion.model;

public enum TipoRecompensa {
    ECONOMICA("Economica"),
    REDUCCION_DOCENTE("Reduccion docente");

    private final String etiqueta;

    TipoRecompensa(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
