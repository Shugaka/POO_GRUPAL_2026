// Excepcion propia (checked): la maquina temporal no tiene energia para la accion pedida.
public class EnergiaInsuficienteException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String accion;
    private final int energiaRequerida;
    private final int energiaActual;

    public EnergiaInsuficienteException(String accion, int energiaRequerida, int energiaActual) {
        super("No hay energia suficiente para " + accion
                + " (necesita " + energiaRequerida + ", tiene " + energiaActual + ").");
        this.accion = accion;
        this.energiaRequerida = energiaRequerida;
        this.energiaActual = energiaActual;
    }

    public String getAccion() {
        return accion;
    }

    public int getEnergiaRequerida() {
        return energiaRequerida;
    }

    public int getEnergiaActual() {
        return energiaActual;
    }
}
