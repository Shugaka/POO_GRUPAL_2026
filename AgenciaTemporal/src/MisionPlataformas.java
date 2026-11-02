import java.util.ArrayList;
import java.util.List;

public class MisionPlataformas extends Mision {

    private static final int ENEMIGOS_PARA_GANAR = 3;

    public MisionPlataformas(
            String titulo,
            int dificultad,
            String descripcion) {

        super(titulo, dificultad, descripcion);   // llama al constructor de la superclase
    }

    @Override
    public void iniciar() {
        super.iniciar();
        System.out.println("Objetivo: derrotar al menos " + ENEMIGOS_PARA_GANAR + " enemigos.");
    }

    @Override
    public List<Consecuencia> jugar(EntradaUsuario entrada) {

        List<Consecuencia> consecuencias = new ArrayList<>();

        mostrarEncabezado("PLATAFORMAS");
        int opcion = entrada.elegir(
                "Tenes que atravesar un nivel.",
                "Explorar y recolectar monedas", "Avanzar rapidamente");

        if (opcion == 1) {

            System.out.println("Recolectaste monedas.");
            int enemigos = entrada.pedirNumero("¿Cuantos enemigos derrotaste?");

            if (enemigos >= ENEMIGOS_PARA_GANAR) {
                consecuencias.add(crearConsecuencia(
                        "La region quedo protegida de los enemigos.",
                        10,
                        "Un año despues"));

                completar();

            } else {
                consecuencias.add(crearConsecuencia(
                        "Quedaron enemigos que amenazan la region.",
                        -5,
                        "Una semana despues"));
            }

        } else if (opcion == 2) {

            consecuencias.add(crearConsecuencia(
                    "El jugador avanzo rapido, pero perdio recursos.",
                    -10,
                    "Inmediatamente"));

        } else {

            System.out.println("Opcion invalida.");
        }

        return consecuencias;
    }

    @Override
    public String toString() {
        return "[Plataformas] " + super.toString();
    }
}
