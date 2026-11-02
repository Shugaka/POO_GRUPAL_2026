import java.util.ArrayList;
import java.util.InputMismatchException;
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

    public List<Epoca> getEpocas() {
        return new ArrayList<>(epocas);
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
            System.out.println((i + 1) + ". " + epocas.get(i));
        }
    }

    public void jugarMision(Epoca epoca, int numeroMision, EntradaUsuario entrada) {
        if (!epocas.contains(epoca)) {
            System.out.println("Esta epoca no pertenece a la agencia.");
            return;
        }

        // 1. Buscar la mision (puede lanzar una excepcion propia)
        Mision mision;
        try {
            mision = epoca.getMision(numeroMision);
        } catch (MisionInvalidaException e) {
            System.out.println(e.getMessage());
            return;
        }

        // 2. Viajar a la epoca (puede lanzar una excepcion propia)
        MaquinaTemporal maquina = jugador.getMaquinaTemporal();

        if (maquina.isViajando()) {
            System.out.println("Seguis en el pasado: jugas la mision sin viajar de nuevo.");
        } else {
            try {
                maquina.viajar(epoca);
            } catch (EnergiaInsuficienteException e) {
                System.out.println(e.getMessage());
                return;
            }
            lineaTemporal.agregarEvento(
                    new Evento("Viaje a " + epoca.getNombre(), TipoEvento.VIAJE)
            );
        }

        // 3. Jugar la mision. Pase lo que pase, el bloque finally intenta volver al presente.
        try {
            mision.iniciar();

            List<Consecuencia> consecuencias = mision.jugar(entrada);

            if (consecuencias != null) {
                for (Consecuencia consecuencia : consecuencias) {
                    consecuencia.aplicar(lineaTemporal);
                }
            }

            jugador.registrarMision(mision);

            lineaTemporal.agregarEvento(
                    new Evento("Se ejecuto la mision: " + mision.getTitulo(), TipoEvento.MISION)
            );

            lineaTemporal.aplicarCambios();
            lineaTemporal.mostrarFuturo();

            if (mision.isCompletada()) {
                mision.aumentarDificultad();   // la proxima vez que se juegue, sera mas dificil
            }

        } catch (InputMismatchException e) {
            System.out.println("Entrada invalida: se cancela la mision.");
        } finally {
            regresarAlPresente();
        }
    }

    public void regresarAlPresente() {
        MaquinaTemporal maquina = jugador.getMaquinaTemporal();
        boolean estabaViajando = maquina.isViajando();

        try {
            maquina.regresarAlPresente();

            if (estabaViajando && !maquina.isViajando()) {
                lineaTemporal.agregarEvento(
                        new Evento("El jugador regreso al presente.", TipoEvento.REGRESO)
                );
            }
        } catch (EnergiaInsuficienteException e) {
            System.out.println(e.getMessage()
                    + " Espera a que la maquina se recargue o usa la opcion de recargar.");
        }
    }
}
