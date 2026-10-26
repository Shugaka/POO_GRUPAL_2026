import java.util.ArrayList;
import java.util.List;

public class LineaTemporal {

    private int estabilidad;
    private List<Consecuencia> consecuencias;
    private int consecuenciasAplicadas;
    private List<Evento> eventos;   //una lista que guarda los eventos ocurridos durante la partida

    public LineaTemporal() {
        this.estabilidad = 100;
        this.consecuencias = new ArrayList<>();
        this.consecuenciasAplicadas = 0;
        this.eventos = new ArrayList<>();
    }

    public int getEstabilidad() {
        return estabilidad;
    }

    public List<Consecuencia> getConsecuencias() {
        return new ArrayList<>(consecuencias);
    }

    public List<Evento> getEventos() {
        return new ArrayList<>(eventos);
    }

    public void agregarConsecuencia(Consecuencia consecuencia) {
        consecuencias.add(consecuencia);
    }

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }

    public void aplicarCambios() {
        while (consecuenciasAplicadas < consecuencias.size()) {
            Consecuencia consecuencia =
                    consecuencias.get(consecuenciasAplicadas);

            estabilidad += consecuencia.getImpacto();
            consecuenciasAplicadas++;
        }

        if (estabilidad > 100) {
            estabilidad = 100;
        }

        if (estabilidad < 0) {
            estabilidad = 0;
        }
    }

    public void mostrarFuturo() {
        System.out.println("\n=== FUTURO RESULTANTE ===");
        System.out.println("Estabilidad temporal: " + estabilidad);

        if (consecuencias.isEmpty()) {
            System.out.println("Todavia no hay consecuencias.");
        } else {
            for (Consecuencia consecuencia : consecuencias) {
                System.out.println(consecuencia.mostrar());
            }
        }
    }

    public void mostrarEventos() {
        System.out.println("\n=== REGISTRO DE EVENTOS ===");

        if (eventos.isEmpty()) {
            System.out.println("Todavia no hay eventos registrados.");
            return;
        }

        for (Evento evento : eventos) {
            System.out.println(evento.mostrar());
        }
    }
}
