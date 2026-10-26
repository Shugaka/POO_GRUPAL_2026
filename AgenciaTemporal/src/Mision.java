import java.util.List;

public abstract class Mision implements Jugabilidad {

    private String titulo;
    private int dificultad;
    private String descripcion;
    private boolean completada;
    private int vecesCompletada;

    public Mision(String titulo, int dificultad, String descripcion) {
        this.titulo = titulo;
        this.dificultad = dificultad;
        this.descripcion = descripcion;
        this.completada = false;
        this.vecesCompletada = 0;
    }

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
        if (completada) {
            dificultad++;
            completada = false;

            System.out.println(
                    "Nueva dificultad de " + titulo + ": " + dificultad
            );
        } else {
            System.out.println(
                    "Primero tenes que completar la mision."
            );
        }
    }

    public abstract List<Consecuencia> jugar();
}
