// Contrato para pedirle datos al usuario sin saber si es consola o ventana.
public interface EntradaUsuario {

    // Muestra una pregunta con opciones. Devuelve la opcion elegida (1, 2, 3...).
    int elegir(String pregunta, String... opciones);

    // Pide un numero entero.
    int pedirNumero(String pregunta);
}
