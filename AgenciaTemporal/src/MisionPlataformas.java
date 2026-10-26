import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MisionPlataformas extends Mision {

    public MisionPlataformas(
            String titulo,
            int dificultad,
            String descripcion) {

        super(titulo, dificultad, descripcion);   //llama al constructor de la superclase
    }

    @Override
    public List<Consecuencia> jugar() {

        Scanner teclado = new Scanner(System.in);
        List<Consecuencia> consecuencias = new ArrayList<>();

        System.out.println("\n=== MISION DE PLATAFORMAS ===");
        System.out.println("Tenes que atravesar un nivel.");
        System.out.println("1. Explorar y recolectar monedas");
        System.out.println("2. Avanzar rapidamente");
        System.out.print("Elegí una opcion: ");

        int opcion = teclado.nextInt();

        if (opcion == 1) {

            System.out.println("Recolectaste monedas.");
            System.out.println("¿Cuantos enemigos derrotaste?");

            int enemigos = teclado.nextInt();

            if (enemigos >= 3) {
                consecuencias.add(
                        new Consecuencia(
                                "La region quedo protegida de los enemigos.",
                                10,
                                "Un año despues"
                        )
                );

                completar();

            } else {
                consecuencias.add(
                        new Consecuencia(
                                "Quedaron enemigos que amenazan la region.",
                                -5,
                                "Una semana despues"
                        )
                );
            }

        } else if (opcion == 2) {

            consecuencias.add(
                    new Consecuencia(
                            "El jugador avanzo rapido, pero perdio recursos.",
                            -10,
                            "Inmediatamente"
                    )
            );

        } else {

            System.out.println("Opcion invalida.");
        }

        return consecuencias;
    }
}
