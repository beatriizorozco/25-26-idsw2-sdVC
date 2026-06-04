package es.funiber.investigacion.model;

public enum SedeFuniber {
    GLOBAL("Global", false),
    SANTANDER("Santander", true),
    BARCELONA("Barcelona", false),
    LIMA("Lima", false),
    BOGOTA("Bogota", false),
    MEXICO("Mexico", false);

    private final String etiqueta;
    private final boolean investigadorDocente;

    SedeFuniber(String etiqueta, boolean investigadorDocente) {
        this.etiqueta = etiqueta;
        this.investigadorDocente = investigadorDocente;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public boolean esInvestigadorDocente() {
        return investigadorDocente;
    }
}
