public class Consecuencia {

    private String descripcion;
    private int impacto;
    private String momento;

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
        lineaTemporal.agregarConsecuencia(this);   //Le pide a la línea temporal que registre esta consecuencia.
                                                    // Usamos this para enviar el objeto Consecuencia actual.
    }

    public String mostrar() {
        return "Consecuencia: " + descripcion
                + " | Impacto: " + impacto
                + " | Momento: " + momento;
    }
}
