public class MaquinaTemporal {

    private int energia;
    private boolean viajando;

    public MaquinaTemporal() {
        this.energia = 100;
        this.viajando = false;
    }

    public int getEnergia() {
        return energia;
    }

    public boolean isViajando() {
        return viajando;
    }

    public void viajar(Epoca epoca) {
        if (viajando) {
            System.out.println("Ya estas viajando en el tiempo.");
            return;
        }

        if (energia < 30) {
            System.out.println("No hay energia suficiente para viajar.");
            return;
        }

        energia -= 30;
        viajando = true;

        System.out.println("\n=== VIAJE TEMPORAL ===");
        System.out.println("Viajaste a: " + epoca.getNombre());
        System.out.println("Año: " + epoca.getAnio());
        System.out.println("Energia restante: " + energia);
    }

    public void regresarAlPresente() {
        if (!viajando) {
            System.out.println("La maquina ya esta en el presente.");
            return;
        }

        if (energia < 20) {
            System.out.println("No hay energia suficiente para regresar.");
            return;
        }

        energia -= 20;
        viajando = false;

        System.out.println("\nRegresaste al presente.");
        System.out.println("Energia restante: " + energia);
    }

    public void recargarEnergia() {
        energia = 100;
        System.out.println("Maquina recargada. Energia: " + energia);
    }
}
