import java.math.MathContext;

public class Ejercicio1Manual1 {
    public static void main (String[] args){
        int valorA = 2;
        int valorB = 4;
        int valorC = 6;

        double discriminante = (valorB*valorB) - (4*valorA*valorC);

        if (discriminante > 0){
            double raiz1 = (-valorB + Math.sqrt(discriminante)) / (2*valorA);
            double raiz2 = (-valorB + Math.sqrt(discriminante)) / (2*valorA);
            System.out.println("2 raices reales:");
            System.out.println("x = " + raiz1);
            System.out.println("x = " + raiz2);
        } else if (discriminante == 0) {
            double raiz = -valorB / (2*valorA);
            System.out.println("Raiz real:");
            System.out.println("x = " + raiz);
        } else {
            System.out.println("No tiene ninguna raiz");
        }
    }
}
