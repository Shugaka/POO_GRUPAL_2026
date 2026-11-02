import java.time.LocalDateTime;

// Clase inmutable: final + atributos final + sin setters.
public final class Evento {

    private static int totalEventos = 0;   // static: contador compartido por todos los eventos

    private final int id;
    private final String descripcion;
    private final TipoEvento tipo;
    private final LocalDateTime fechaHora;

    public Evento(String descripcion, TipoEvento tipo) {
        totalEventos++;
        this.id = totalEventos;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaHora = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public static int getTotalEventos() {
        return totalEventos;
    }

    public String mostrar() {
        return "#" + id + " [" + fechaHora.withNano(0) + "] "
                + tipo.getEtiqueta() + ": " + descripcion;
    }

    @Override
    public String toString() {
        return mostrar();
    }
}
