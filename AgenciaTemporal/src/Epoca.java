import java.util.ArrayList;
import java.util.List;

public class Epoca {

    private String nombre;
    private int anio;
    private List<Mision> misiones;   //una época puede contener muchos objetos que sean de tipo Mision o de sus subclases

    public Epoca(String nombre, int anio) {
        this.nombre = nombre;
        this.anio = anio;
        this.misiones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnio() {
        return anio;
    }

    public List<Mision> getMisiones() {
        return new ArrayList<>(misiones);
    }

    public void agregarMision(Mision mision) {
        misiones.add(mision);
    }

    public void mostrarMisiones() {
        System.out.println("\nMisiones disponibles en " + nombre);

        if (misiones.isEmpty()) {
            System.out.println("No hay misiones disponibles.");
            return;
        }

        for (int i = 0; i < misiones.size(); i++) {
            Mision mision = misiones.get(i);

            System.out.println(
                    (i + 1) + ". " + mision.getTitulo()
                            + " | Dificultad: " + mision.getDificultad()
            );
        }
    }
}
