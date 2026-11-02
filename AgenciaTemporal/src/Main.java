import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Hilo de recarga automatica: +5 de energia cada 5 segundos.
    private static final long INTERVALO_RECARGA_MS = 5000;
    private static final int CANTIDAD_RECARGA = 5;

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // 1. Crear la maquina temporal y el jugador
        MaquinaTemporal maquina = new MaquinaTemporal();

        System.out.print("Ingresa tu nombre: ");
        String nombre = teclado.nextLine();

        Jugador jugador = new Jugador(nombre, maquina);

        // 2. Crear la agencia
        AgenciaTemporal agencia = new AgenciaTemporal(jugador);

        // 3. Crear las epocas
        Epoca edadMedia = new Epoca("Edad Media", 1200);
        Epoca futuro = new Epoca("Futuro", 2200);

        // 4. Crear las misiones
        MisionDetective detective = new MisionDetective(
                "El misterio del castillo",
                1,
                "Investiga un asesinato en el castillo."
        );

        MisionPlataformas plataformas = new MisionPlataformas(
                "La defensa del reino",
                2,
                "Atraviesa el nivel y derrota a los enemigos."
        );

        // 5. Agregar las misiones a sus epocas
        edadMedia.agregarMision(detective);
        edadMedia.agregarMision(plataformas);

        // 6. Registrar las epocas en la agencia
        agencia.agregarEpoca(edadMedia);
        agencia.agregarEpoca(futuro);

        // 7. Arrancar el hilo que recarga la energia en segundo plano
        RecargaAutomatica.iniciar(maquina, INTERVALO_RECARGA_MS, CANTIDAD_RECARGA, null);

        EntradaUsuario entrada = new EntradaConsola(teclado);

        // 8. Iniciar el menu
        int opcion = -1;

        do {
            System.out.println("\n==============================");
            System.out.println("     AGENCIA TEMPORAL");
            System.out.println("==============================");
            System.out.println("Jugador: " + jugador.getNombre());
            System.out.println("Energia: " + maquina.getEnergia() + "/" + MaquinaTemporal.ENERGIA_MAXIMA
                    + " (se recarga sola +" + CANTIDAD_RECARGA + " cada " + (INTERVALO_RECARGA_MS / 1000) + " s)");
            System.out.println("1. Mostrar epocas");
            System.out.println("2. Mostrar misiones de una epoca");
            System.out.println("3. Jugar una mision");
            System.out.println("4. Mostrar historial");
            System.out.println("5. Mostrar futuro y consecuencias");
            System.out.println("6. Mostrar eventos");
            System.out.println("7. Recargar maquina temporal");
            System.out.println("8. Regresar al presente");
            System.out.println("0. Salir");
            System.out.print("Elegí una opcion: ");

            try {
                opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:
                        agencia.mostrarEpocas();
                        break;

                    case 2: {
                        Epoca epoca = elegirEpoca(agencia, teclado);
                        if (epoca != null) {
                            epoca.mostrarMisiones();
                        }
                        break;
                    }

                    case 3: {
                        Epoca epoca = elegirEpoca(agencia, teclado);
                        if (epoca == null) {
                            break;
                        }

                        epoca.mostrarMisiones();

                        if (!epoca.tieneMisiones()) {
                            break;
                        }

                        System.out.print("Numero de mision: ");
                        int numeroMision = teclado.nextInt();

                        agencia.jugarMision(epoca, numeroMision, entrada);
                        break;
                    }

                    case 4:
                        jugador.mostrarHistorial();
                        break;

                    case 5:
                        agencia.getLineaTemporal().mostrarFuturo();
                        break;

                    case 6:
                        agencia.getLineaTemporal().mostrarEventos();
                        System.out.println("Total de eventos creados: " + Evento.getTotalEventos());
                        break;

                    case 7:
                        maquina.recargarEnergia();
                        break;

                    case 8:
                        agencia.regresarAlPresente();
                        break;

                    case 0:
                        System.out.println("Gracias por jugar.");
                        break;

                    default:
                        System.out.println("Opcion invalida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida: ingresa un numero.");
                teclado.nextLine();   // descarta lo que se escribio mal
                opcion = -1;
            }

        } while (opcion != 0);

        teclado.close();
    }

    // Muestra las epocas de la agencia y devuelve la elegida (o null si el numero no es valido).
    private static Epoca elegirEpoca(AgenciaTemporal agencia, Scanner teclado) {
        agencia.mostrarEpocas();

        System.out.print("Numero de epoca: ");
        int numeroEpoca = teclado.nextInt();

        List<Epoca> epocas = agencia.getEpocas();

        if (numeroEpoca < 1 || numeroEpoca > epocas.size()) {
            System.out.println("Numero de epoca invalido.");
            return null;
        }

        return epocas.get(numeroEpoca - 1);
    }
}
