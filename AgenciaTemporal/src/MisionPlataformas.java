import java.util.ArrayList;
import java.util.List;

public class MisionPlataformas extends Mision {

    public MisionPlataformas(
            String titulo,
            int dificultad,
            String descripcion) {

        super(titulo, dificultad, descripcion);   //llama al constructor de la superclase
    }

    @Override
    public List<Consecuencia> jugar(EntradaUsuario entrada) {

        List<Consecuencia> consecuencias = new ArrayList<>();

        System.out.println("\n=== MISION DE PLATAFORMAS ===");
        int opcion = entrada.elegir(
                "Tenes que atravesar un nivel.",
                "Explorar y recolectar monedas", "Avanzar rapidamente");

        if (opcion == 1) {

            System.out.println("Recolectaste monedas.");
            int enemigos = entrada.pedirNumero("Cuantos enemigos derrotaste?");

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
