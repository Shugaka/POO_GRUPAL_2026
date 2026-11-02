import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private AgenciaTemporal agencia;
    private MaquinaTemporal maquina;
    private Jugador jugador;

    private JTextArea consola = new JTextArea();
    private JLabel lblEstado = new JLabel();
    private JComboBox<String> cmbEpocas = new JComboBox<>();
    private JComboBox<String> cmbMisiones = new JComboBox<>();

    public VentanaPrincipal(AgenciaTemporal agencia, Jugador jugador) {
        super("Agencia Temporal");
        this.agencia = agencia;
        this.jugador = jugador;
        this.maquina = jugador.getMaquinaTemporal();

        redirigirSalidaAlTextArea();
        armarVentana();
        cargarEpocas();
        actualizarEstado();

        // Hilo separado: recarga la energia de a poco y refresca la pantalla (siempre desde el hilo de Swing)
        RecargaAutomatica.iniciar(maquina, 5000, 5, () -> SwingUtilities.invokeLater(this::actualizarEstado));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(760, 520);
        setLocationRelativeTo(null);
    }

    private void armarVentana() {
        setLayout(new BorderLayout(8, 8));

        // Arriba: estado del jugador
        lblEstado.setFont(lblEstado.getFont().deriveFont(Font.BOLD, 14f));
        lblEstado.setBorder(BorderFactory.createEmptyBorder(8, 10, 0, 10));
        add(lblEstado, BorderLayout.NORTH);

        // Centro: "consola" con todo lo que imprimen las clases
        consola.setEditable(false);
        consola.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        add(new JScrollPane(consola), BorderLayout.CENTER);

        // Derecha: seleccion y botones
        JPanel lateral = new JPanel();
        lateral.setLayout(new BoxLayout(lateral, BoxLayout.Y_AXIS));
        lateral.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

        lateral.add(new JLabel("Epoca:"));
        lateral.add(cmbEpocas);
        lateral.add(Box.createVerticalStrut(6));
        lateral.add(new JLabel("Mision:"));
        lateral.add(cmbMisiones);
        lateral.add(Box.createVerticalStrut(10));

        lateral.add(boton("Jugar mision", e -> jugarMision()));
        lateral.add(boton("Historial", e -> { jugador.mostrarHistorial(); }));
        lateral.add(boton("Futuro y consecuencias", e -> agencia.getLineaTemporal().mostrarFuturo()));
        lateral.add(boton("Eventos", e -> agencia.getLineaTemporal().mostrarEventos()));
        lateral.add(boton("Recargar maquina", e -> { maquina.recargarEnergia(); actualizarEstado(); }));
        lateral.add(boton("Regresar al presente", e -> { agencia.regresarAlPresente(); actualizarEstado(); }));
        lateral.add(boton("Salir", e -> System.exit(0)));

        add(lateral, BorderLayout.EAST);

        // Al cambiar de epoca, se recargan sus misiones
        cmbEpocas.addActionListener(e -> cargarMisiones());
    }

    private JButton boton(String texto, java.awt.event.ActionListener accion) {
        JButton b = new JButton(texto);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        b.addActionListener(accion);
        lateralSpacing(b);
        return b;
    }

    private void lateralSpacing(JComponent c) {
        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(3, 0, 3, 0), c.getBorder()));
    }

    private void cargarEpocas() {
        cmbEpocas.removeAllItems();
        for (Epoca ep : agencia.getEpocas()) {
            cmbEpocas.addItem(ep.getNombre() + " (" + ep.getAnio() + ")");
        }
        cargarMisiones();
    }

    private void cargarMisiones() {
        cmbMisiones.removeAllItems();
        int i = cmbEpocas.getSelectedIndex();
        if (i < 0) return;
        for (Mision m : agencia.getEpocas().get(i).getMisiones()) {
            cmbMisiones.addItem(m.getTitulo() + " (dif. " + m.getDificultad() + ")");
        }
    }

    private void jugarMision() {
        int e = cmbEpocas.getSelectedIndex();
        int m = cmbMisiones.getSelectedIndex();
        if (e < 0 || m < 0) {
            JOptionPane.showMessageDialog(this, "Esa epoca no tiene misiones.");
            return;
        }
        Epoca epoca = agencia.getEpocas().get(e);
        agencia.jugarMision(epoca, m + 1, new EntradaSwing(this));
        actualizarEstado();
    }

    private void actualizarEstado() {
        lblEstado.setText("Jugador: " + jugador.getNombre()
                + "   |   Energia: " + maquina.getEnergia()
                + "   |   Estabilidad: " + agencia.getLineaTemporal().getEstabilidad());
    }

    // Todo System.out.println de las clases del modelo termina en el JTextArea.
    private void redirigirSalidaAlTextArea() {
        System.setOut(new PrintStream(new ConsolaOutputStream(), true, java.nio.charset.StandardCharsets.UTF_8));
    }

    // Clase interna (inner class): necesita acceder al JTextArea "consola" de la ventana.
    private class ConsolaOutputStream extends OutputStream {

        @Override
        public void write(int b) {
            SwingUtilities.invokeLater(() -> consola.append(String.valueOf((char) b)));
        }

        @Override
        public void write(byte[] b, int off, int len) {
            String texto = new String(b, off, len, java.nio.charset.StandardCharsets.UTF_8);
            SwingUtilities.invokeLater(() -> {
                consola.append(texto);
                consola.setCaretPosition(consola.getDocument().getLength());
            });
        }
    }
}
