import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EntradaSwing implements EntradaUsuario {

    private JFrame ventana;

    public EntradaSwing(JFrame ventana) {
        this.ventana = ventana;
    }

    @Override
    public int elegir(String pregunta, String... opciones) {
        int r = JOptionPane.showOptionDialog(
                ventana, pregunta, "Mision",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);
        return r + 1;   // showOptionDialog devuelve indice desde 0 (o -1 si cierra)
    }

    @Override
    public int pedirNumero(String pregunta) {
        while (true) {
            String texto = JOptionPane.showInputDialog(ventana, pregunta);
            if (texto == null) return 0;   // cancelo
            try {
                return Integer.parseInt(texto.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(ventana, "Ingresa un numero valido.");
            }
        }
    }
}
