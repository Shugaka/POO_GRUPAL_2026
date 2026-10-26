import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private String nombre;
    private List<Mision> historialMisiones;
    private MaquinaTemporal maquinaTemporal;

    public Jugador(String nombre, MaquinaTemporal maquinaTemporal) {
        this.nombre = nombre;
        this.maquinaTemporal = maquinaTemporal;
        this.historialMisiones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public MaquinaTemporal getMaquinaTemporal() {
        return maquinaTemporal;
    }

    public List<Mision> getHistorialMisiones() {
        return new ArrayList<>(historialMisiones);  //devuelve una copia de la lista. Así, alguien que obtenga el historial no podrá modificar directamente la lista interna del jugador
    }

    public void registrarMision(Mision mision) {
        historialMisiones.add(mision);
    }

    public void mostrarHistorial() {
        System.out.println("\n---- HISTORIAL DEL JUGADOR ----");
        System.out.println("Jugador: " + nombre);

        if (historialMisiones.isEmpty()) {
            System.out.println("Todavia no jugaste ninguna mision.");
            return;
        }

        for (Mision mision : historialMisiones) {
            System.out.println(
                    "Mision: " + mision.getTitulo()
                            + " | Dificultad: " + mision.getDificultad()
            );
        }
    }
}