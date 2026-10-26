import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MisionDetective extends Mision {

    public MisionDetective(
            String titulo,
            int dificultad,
            String descripcion) {

        super(titulo, dificultad, descripcion);   //llama al constructor de la superclase
    }

    @Override
    public List<Consecuencia> jugar() {

        Scanner teclado = new Scanner(System.in);
        List<Consecuencia> consecuencias = new ArrayList<>();

        System.out.println("\n=== MISION DE DETECTIVE ===");
        System.out.println("Investiga el asesinato de una figura historica.");
        System.out.println("1. Investigar las pistas");
        System.out.println("2. Ignorar las pistas");
        System.out.print("Elegí una opcion: ");

        int opcion = teclado.nextInt();

        if (opcion == 1) {

            System.out.println("\nEncontraste pruebas importantes.");
            System.out.println("1. Capturar al culpable");
            System.out.println("2. Dejarlo escapar");
            System.out.print("Elegí una opcion: ");

            int decision = teclado.nextInt();

            if (decision == 1) {

                consecuencias.add(
                        new Consecuencia(
                                "El culpable fue capturado.",
                                10,
                                "Inmediatamente"
                        )
                );

                completar();  //marcamos la misión como completada cuando el culpable es capturado.

            } else {

                consecuencias.add(
                        new Consecuencia(
                                "El culpable escapo y altero la historia.",
                                -15,
                                "Un año despues"
                        )
                );
            }

        } else {

            consecuencias.add(
                    new Consecuencia(
                            "El misterio quedo sin resolver.",
                            -10,
                            "Muchos años despues"
                    )
            );
        }

        return consecuencias;
    }
}
