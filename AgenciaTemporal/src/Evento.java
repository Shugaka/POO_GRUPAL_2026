import java.time.LocalDateTime;

public class Evento {

    private String descripcion;
    private String tipo;
    private LocalDateTime fechaHora;

    public Evento(String descripcion, String tipo) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaHora = LocalDateTime.now();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String mostrar() {
        return "[" + fechaHora + "] "
                + tipo + ": " + descripcion;
    }
}

