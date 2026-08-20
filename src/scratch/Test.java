package scratch;

public class Test {
    public static void main (String[] args) {
        System.out.println("HolaMundo");

        Persona p = new Persona("Marquinhos");
        p.saludar();
    }
}

class Persona {
    String nombre;

    Persona(String nombre) {
        this.nombre=nombre;
    }
    void saludar() {
        System.out.println("Hola, soy " + nombre);
    }
}