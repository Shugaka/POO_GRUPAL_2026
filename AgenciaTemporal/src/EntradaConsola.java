import java.util.Scanner;

public class EntradaConsola implements EntradaUsuario {

    private Scanner teclado;

    public EntradaConsola(Scanner teclado) {
        this.teclado = teclado;
    }

    @Override
    public int elegir(String pregunta, String... opciones) {
        System.out.println("\n" + pregunta);
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        System.out.print("Elegi una opcion: ");
        return teclado.nextInt();
    }

    @Override
    public int pedirNumero(String pregunta) {
        System.out.println(pregunta);
        return teclado.nextInt();
    }
}
