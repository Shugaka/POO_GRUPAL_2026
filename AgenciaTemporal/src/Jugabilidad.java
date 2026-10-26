import java.util.List;

public interface Jugabilidad {

    List<Consecuencia> jugar();  //metodo que cada tipo de misión implementará para ejecutar su propia mecánica
}
