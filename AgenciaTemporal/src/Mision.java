import java.util.List;

// Clase abstracta que implementa la interfaz Jugabilidad y el orden natural (Comparable) por dificultad.
public abstract class Mision implements Jugabilidad, Comparable<Mision> {

    public static final int DIFICULTAD_MAXIMA = 10;

    // protected: las subclases (MisionDetective, MisionPlataformas) pueden usarlos directamente.
    protected String titulo;
    protected int dificultad;
    protected String descripcion;

    private boolean completada;
    private int vecesCompletada;

    public Mision(String titulo, int dificultad, String descripcion) {
        this.titulo = titulo;
        this.dificultad = dificultad;
        this.descripcion = descripcion;
        this.completada = false;
        this.vecesCompletada = 0;
    }

    // Sobrecarga de constructores: este reutiliza al anterior con this(...).
    public Mision(String titulo, String descripcion) {
        this(titulo, 1, descripcion);
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDificultad() {
        return dificultad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public int getVecesCompletada() {
        return vecesCompletada;
    }

    public void iniciar() {
        System.out.println("Iniciando mision: " + titulo);
        System.out.println("Dificultad: " + dificultad);
        System.out.println(descripcion);
    }

    public void completar() {
        if (!completada) {
            completada = true;
            vecesCompletada++;
            System.out.println("Mision completada: " + titulo);
        } else {
            System.out.println("Esta mision ya esta completada.");
        }
    }

    public void aumentarDificultad() {
        if (!completada) {
            System.out.println("Primero tenes que completar la mision.");
            return;
        }

        if (dificultad < DIFICULTAD_MAXIMA) {
            dificultad++;
        }
        completada = false;
        System.out.println("Nueva dificultad de " + titulo + ": " + dificultad);
    }

    // protected: metodo de ayuda que solo usan las subclases.
    protected void mostrarEncabezado(String tipo) {
        System.out.println("\n=== MISION DE " + tipo + " ===");
    }

    // protected: las consecuencias pesan mas cuanto mas dificil es la mision.
    // Con dificultad 1 el impacto no cambia; cada punto extra suma un 10%.
    protected Consecuencia crearConsecuencia(String descripcion, int impactoBase, String momento) {
        int impacto = (int) Math.round(impactoBase * (1 + 0.1 * (dificultad - 1)));   // cast double -> int
        return new Consecuencia(descripcion, impacto, momento);
    }

    // Orden natural: por dificultad (de menor a mayor).
    @Override
    public int compareTo(Mision otra) {
        return Integer.compare(this.dificultad, otra.dificultad);
    }

    @Override
    public String toString() {
        return titulo + " (dificultad " + dificultad + ")";
    }

    @Override
    public abstract List<Consecuencia> jugar(EntradaUsuario entrada);
}
