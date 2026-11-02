import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Jugador {

    private String nombre;
    private List<Mision> historialMisiones;
    private MaquinaTemporal maquinaTemporal;
    private Map<String, Integer> vecesJugada;   // titulo -> cuantas veces la jugo (Integer = tipo wrapper)

    public Jugador(String nombre, MaquinaTemporal maquinaTemporal) {
        this.nombre = nombre;
        this.maquinaTemporal = maquinaTemporal;
        this.historialMisiones = new ArrayList<>();
        this.vecesJugada = new TreeMap<>();   // TreeMap: claves ordenadas alfabeticamente
    }

    public String getNombre() {
        return nombre;
    }

    public MaquinaTemporal getMaquinaTemporal() {
        return maquinaTemporal;
    }

    public List<Mision> getHistorialMisiones() {
        return new ArrayList<>(historialMisiones);  // devuelve una copia: quien lo pida no puede modificar la lista interna
    }

    public void registrarMision(Mision mision) {
        historialMisiones.add(mision);
        vecesJugada.merge(mision.getTitulo(), 1, Integer::sum);   // autoboxing int <-> Integer
    }

    public int getVecesJugada(String titulo) {
        return vecesJugada.getOrDefault(titulo, 0);   // auto-unboxing Integer -> int
    }

    public void mostrarHistorial() {
        System.out.println("\n---- HISTORIAL DEL JUGADOR ----");
        System.out.println("Jugador: " + nombre);

        if (historialMisiones.isEmpty()) {
            System.out.println("Todavia no jugaste ninguna mision.");
            return;
        }

        // Ordenamiento personalizado con Comparator: por titulo.
        List<Mision> ordenado = getHistorialMisiones();
        Collections.sort(ordenado, new ComparadorMisionPorTitulo());

        for (Mision mision : ordenado) {
            System.out.println("Mision: " + mision.getTitulo()
                    + " | Dificultad: " + mision.getDificultad()
                    + " | Veces completada: " + mision.getVecesCompletada());
        }

        System.out.println("\nResumen de partidas:");
        for (Map.Entry<String, Integer> entrada : vecesJugada.entrySet()) {
            System.out.println(" - " + entrada.getKey() + ": jugada " + entrada.getValue() + " vez/veces");
        }
    }
}
