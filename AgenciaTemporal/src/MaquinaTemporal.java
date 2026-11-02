public class MaquinaTemporal {

    // Constantes static final: un unico valor compartido por todas las maquinas.
    public static final int ENERGIA_MAXIMA = 100;
    public static final int COSTO_VIAJE = 30;
    public static final int COSTO_REGRESO = 20;

    private int energia;
    private boolean viajando;

    public MaquinaTemporal() {
        this.energia = ENERGIA_MAXIMA;
        this.viajando = false;
    }

    // synchronized: la energia tambien la modifica el hilo RecargaAutomatica.
    public synchronized int getEnergia() {
        return energia;
    }

    public synchronized boolean isViajando() {
        return viajando;
    }

    public synchronized void viajar(Epoca epoca) throws EnergiaInsuficienteException {
        if (viajando) {
            System.out.println("Ya estas viajando en el tiempo.");
            return;
        }

        if (energia < COSTO_VIAJE) {
            throw new EnergiaInsuficienteException("viajar", COSTO_VIAJE, energia);
        }

        energia -= COSTO_VIAJE;
        viajando = true;

        System.out.println("\n=== VIAJE TEMPORAL ===");
        System.out.println("Viajaste a: " + epoca.getNombre());
        System.out.println("Año: " + epoca.getAnio());
        System.out.println("Energia restante: " + energia);
    }

    public synchronized void regresarAlPresente() throws EnergiaInsuficienteException {
        if (!viajando) {
            System.out.println("La maquina ya esta en el presente.");
            return;
        }

        if (energia < COSTO_REGRESO) {
            throw new EnergiaInsuficienteException("regresar", COSTO_REGRESO, energia);
        }

        energia -= COSTO_REGRESO;
        viajando = false;

        System.out.println("\nRegresaste al presente.");
        System.out.println("Energia restante: " + energia);
    }

    // Sobrecarga de metodos: mismo nombre, distintos parametros.
    // Version manual: recarga todo y avisa.
    public synchronized void recargarEnergia() {
        energia = ENERGIA_MAXIMA;
        System.out.println("Maquina recargada. Energia: " + energia);
    }

    // Version gradual (la usa el hilo): suma una cantidad sin pasarse del maximo.
    // Devuelve true si la energia realmente cambio.
    public synchronized boolean recargarEnergia(int cantidad) {
        int anterior = energia;
        energia = Math.min(ENERGIA_MAXIMA, energia + cantidad);
        return energia != anterior;
    }
}
