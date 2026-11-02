import java.util.ArrayList;
import java.util.List;

public class MisionDetective extends Mision {

    // Arreglos (arrays) de referencia: sospechosos y la pista que deja cada uno (mismo indice).
    private static final String[] SOSPECHOSOS = {"El mayordomo", "La cocinera", "El herrero"};
    private static final String[] PISTAS = {
            "huellas de barro que vienen de la entrada de servicio",
            "un delantal con restos de harina junto al cuerpo",
            "restos de carbon en la espada del crimen"
    };

    private final int culpable;   // indice dentro de SOSPECHOSOS

    public MisionDetective(
            String titulo,
            int dificultad,
            String descripcion) {

        super(titulo, dificultad, descripcion);   // llama al constructor de la superclase
        this.culpable = (int) (Math.random() * SOSPECHOSOS.length);   // cast double -> int
    }

    @Override
    public void iniciar() {
        super.iniciar();   // reutiliza el comportamiento de Mision y le agrega algo propio
        System.out.println("Objetivo: descubrir quien cometio el crimen.");
    }

    @Override
    public List<Consecuencia> jugar(EntradaUsuario entrada) {

        List<Consecuencia> consecuencias = new ArrayList<>();

        mostrarEncabezado("DETECTIVE");
        int opcion = entrada.elegir(
                "Investiga el asesinato de una figura historica.",
                "Investigar las pistas", "Ignorar las pistas");

        if (opcion == 1) {

            System.out.println("\nEncontraste una pista: " + PISTAS[culpable] + ".");
            int acusado = entrada.elegir("¿Quien es el culpable?", SOSPECHOSOS) - 1;

            if (acusado == culpable) {

                consecuencias.add(crearConsecuencia(
                        "El culpable (" + SOSPECHOSOS[culpable] + ") fue capturado.",
                        10,
                        "Inmediatamente"));

                completar();   // marcamos la mision como completada cuando se captura al culpable

            } else if (acusado >= 0 && acusado < SOSPECHOSOS.length) {

                consecuencias.add(crearConsecuencia(
                        "Acusaste a un inocente y el culpable escapo y altero la historia.",
                        -15,
                        "Un año despues"));

            } else {

                consecuencias.add(crearConsecuencia(
                        "Nadie fue acusado: el caso quedo abierto.",
                        -10,
                        "Muchos años despues"));
            }

        } else {

            consecuencias.add(crearConsecuencia(
                    "El misterio quedo sin resolver.",
                    -10,
                    "Muchos años despues"));
        }

        return consecuencias;
    }

    @Override
    public String toString() {
        return "[Detective] " + super.toString();
    }
}
