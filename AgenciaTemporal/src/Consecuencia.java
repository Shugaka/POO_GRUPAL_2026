// Clase inmutable: final + atributos final + sin setters.
public final class Consecuencia {

    private final String descripcion;
    private final int impacto;
    private final String momento;

    public Consecuencia(String descripcion, int impacto, String momento) {
        this.descripcion = descripcion;
        this.impacto = impacto;
        this.momento = momento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImpacto() {
        return impacto;
    }

    public String getMomento() {
        return momento;
    }

    public void aplicar(LineaTemporal lineaTemporal) {
        lineaTemporal.agregarConsecuencia(this);   // le pide a la linea temporal que registre esta consecuencia
                                                    // (this = el objeto Consecuencia actual)
    }

    public String mostrar() {
        return "Consecuencia: " + descripcion
                + " | Impacto: " + impacto
                + " | Momento: " + momento;
    }

    @Override
    public String toString() {
        return mostrar();
    }
}
