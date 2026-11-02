import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Epoca {

    private String nombre;
    private int anio;
    private List<Mision> misiones;   // una epoca puede contener muchos objetos de tipo Mision o de sus subclases

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

    // Devuelve una copia ordenada por dificultad (orden natural, Comparable de Mision).
    public List<Mision> getMisiones() {
        List<Mision> copia = new ArrayList<>(misiones);
        Collections.sort(copia);
        return copia;
    }

    // Busca una mision por su numero (1, 2, 3...) en el mismo orden en que se muestran.
    public Mision getMision(int numero) throws MisionInvalidaException {
        List<Mision> ordenadas = getMisiones();
        if (numero < 1 || numero > ordenadas.size()) {
            throw new MisionInvalidaException(numero, ordenadas.size());
        }
        return ordenadas.get(numero - 1);
    }

    public void agregarMision(Mision mision) {
        misiones.add(mision);
    }

    // Sin modificador (acceso por defecto): solo lo usan clases del mismo paquete.
    boolean tieneMisiones() {
        return !misiones.isEmpty();
    }

    public void mostrarMisiones() {
        System.out.println("\nMisiones disponibles en " + nombre);

        if (!tieneMisiones()) {
            System.out.println("No hay misiones disponibles.");
            return;
        }

        int numero = 1;
        for (Mision mision : getMisiones()) {
            System.out.println(numero + ". " + mision);
            numero++;
        }
    }

    @Override
    public String toString() {
        return nombre + " (" + anio + ")";
    }
}
