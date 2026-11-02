// Hilo de ejecucion separado: recarga de a poco la energia de la maquina temporal mientras el juego sigue.
// Clase con acceso por defecto (package-private): solo se usa dentro del paquete.
class RecargaAutomatica implements Runnable {

    private final MaquinaTemporal maquina;
    private final long intervaloMs;
    private final int cantidad;
    private final Runnable alCambiar;      // aviso opcional (la GUI lo usa para refrescar la pantalla)
    private volatile boolean activa = true;

    RecargaAutomatica(MaquinaTemporal maquina, long intervaloMs, int cantidad, Runnable alCambiar) {
        this.maquina = maquina;
        this.intervaloMs = intervaloMs;
        this.cantidad = cantidad;
        this.alCambiar = alCambiar;
    }

    @Override
    public void run() {
        while (activa) {
            try {
                Thread.sleep(intervaloMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            if (maquina.recargarEnergia(cantidad) && alCambiar != null) {
                alCambiar.run();
            }
        }
    }

    void detener() {
        activa = false;
    }

    // Metodo static de fabrica: crea el hilo, lo marca como daemon (no impide cerrar el programa) y lo arranca.
    static Thread iniciar(MaquinaTemporal maquina, long intervaloMs, int cantidad, Runnable alCambiar) {
        Thread hilo = new Thread(new RecargaAutomatica(maquina, intervaloMs, cantidad, alCambiar), "recarga-energia");
        hilo.setDaemon(true);
        hilo.start();
        return hilo;
    }
}
