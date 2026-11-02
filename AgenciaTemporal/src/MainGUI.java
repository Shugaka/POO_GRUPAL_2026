import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MainGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            String nombre = JOptionPane.showInputDialog(null, "Ingresa tu nombre:");
            if (nombre == null || nombre.isBlank()) {
                return;
            }

            MaquinaTemporal maquina = new MaquinaTemporal();
            Jugador jugador = new Jugador(nombre, maquina);
            AgenciaTemporal agencia = new AgenciaTemporal(jugador);

            Epoca edadMedia = new Epoca("Edad Media", 1200);
            Epoca futuro = new Epoca("Futuro", 2200);

            edadMedia.agregarMision(new MisionDetective(
                    "El misterio del castillo", 1, "Investiga un asesinato en el castillo."));
            edadMedia.agregarMision(new MisionPlataformas(
                    "La defensa del reino", 2, "Atraviesa el nivel y derrota a los enemigos."));

            agencia.agregarEpoca(edadMedia);
            agencia.agregarEpoca(futuro);

            new VentanaPrincipal(agencia, jugador).setVisible(true);
        });
    }
}
