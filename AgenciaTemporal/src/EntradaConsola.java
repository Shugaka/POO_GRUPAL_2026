import java.util.InputMismatchException;
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
        return leerEntero();
    }

    @Override
    public int pedirNumero(String pregunta) {
        System.out.println(pregunta);
        return leerEntero();
    }

    // Lee una palabra y la convierte a int. Si no es un numero lanza InputMismatchException
    // (la palabra ya fue consumida, asi que no queda basura en el Scanner).
    private int leerEntero() {
        String texto = teclado.next();
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Se esperaba un numero y se recibio: " + texto);
        }
    }
}
