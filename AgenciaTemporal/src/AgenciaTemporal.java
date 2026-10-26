import java.util.ArrayList;
import java.util.List;

public class AgenciaTemporal {

    private Jugador jugador;
    private List<Epoca> epocas;
    private LineaTemporal lineaTemporal;

    public AgenciaTemporal(Jugador jugador) {
        this.jugador = jugador;
        this.epocas = new ArrayList<>();
        this.lineaTemporal = new LineaTemporal();
    }

    public Jugador getJugador() {
        return jugador;
    }

    public LineaTemporal getLineaTemporal() {
        return lineaTemporal;
    }

    public void agregarEpoca(Epoca epoca) {
        epocas.add(epoca);
    }

    public void mostrarEpocas() {
        System.out.println("\n---- EPOCAS DISPONIBLES ----");

        if (epocas.isEmpty()) {
            System.out.println("No hay epocas registradas.");
            return;
        }

        for (int i = 0; i < epocas.size(); i++) {
            Epoca epoca = epocas.get(i);

            System.out.println((i + 1) + ". " + epoca.getNombre() + " | Año: " + epoca.getAnio()
            );
        }
    }


    public void jugarMision(Epoca epoca, int numeroMision) {
        if (!epocas.contains(epoca)) {
            System.out.println("Esta epoca no pertenece a la agencia.");
            return;
        }

        List<Mision> misiones = epoca.getMisiones();

        if (numeroMision < 1 || numeroMision > misiones.size()) {
            System.out.println("Numero de mision invalido.");
            return;
        }

        MaquinaTemporal maquina = jugador.getMaquinaTemporal();

        maquina.viajar(epoca);

        if (!maquina.isViajando()) {
            return;
        }

        lineaTemporal.agregarEvento(
                new Evento("Viaje a " + epoca.getNombre(), "VIAJE")
        );

        Mision mision = misiones.get(numeroMision - 1);

        mision.iniciar();

        List<Consecuencia> consecuencias = mision.jugar();

        if (consecuencias != null && !consecuencias.isEmpty()) {
            for (Consecuencia consecuencia : consecuencias) {
                consecuencia.aplicar(lineaTemporal);
            }
        }

        jugador.registrarMision(mision);

        lineaTemporal.agregarEvento(
                new Evento("Se ejecuto la mision: " + mision.getTitulo(), "MISION")
        );

        lineaTemporal.aplicarCambios();
        lineaTemporal.mostrarFuturo();

        maquina.regresarAlPresente();

        lineaTemporal.agregarEvento(
                new Evento("El jugador intento regresar al presente.", "REGRESO")
        );
    }

}
