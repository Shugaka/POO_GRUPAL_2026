// Tipo enumerado: reemplaza al String "tipo" de Evento (evita errores de tipeo como "VIAJEE").
public enum TipoEvento {
    VIAJE("Viaje"),
    MISION("Mision"),
    REGRESO("Regreso");

    private final String etiqueta;

    TipoEvento(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
