import java.util.ArrayList;
import java.util.List;

public class MisionDetective extends Mision {

    public MisionDetective(
            String titulo,
            int dificultad,
            String descripcion) {

        super(titulo, dificultad, descripcion);   //llama al constructor de la superclase
    }

    @Override
    public List<Consecuencia> jugar(EntradaUsuario entrada) {

        List<Consecuencia> consecuencias = new ArrayList<>();

        System.out.println("\n=== MISION DE DETECTIVE ===");
        int opcion = entrada.elegir(
                "Investiga el asesinato de una figura historica.",
                "Investigar las pistas", "Ignorar las pistas");

        if (opcion == 1) {

            int decision = entrada.elegir(
                    "Encontraste pruebas importantes.",
                    "Capturar al culpable", "Dejarlo escapar");

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
