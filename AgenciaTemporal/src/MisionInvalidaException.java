// Excepcion propia (checked): el numero de mision elegido no existe en la epoca.
public class MisionInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    private final int numeroElegido;

    public MisionInvalidaException(int numeroElegido, int totalMisiones) {
        super("Numero de mision invalido: " + numeroElegido
                + " (la epoca tiene " + totalMisiones + " misiones).");
        this.numeroElegido = numeroElegido;
    }

    public int getNumeroElegido() {
        return numeroElegido;
    }
}
