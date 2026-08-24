package Clase10_8_26;

public class Ejercicio_if {
    public static void main(String[] args) {
        int num = 739;
        System.out.println(num%10);
        int puntaje = 76;
        if (puntaje >= 90){
            System.out.println("Tu calificacion es A.");
        } else {
            if (puntaje >= 80){
                System.out.println("Tu calificacion es B.");
            } else {
                if  (puntaje >= 70){
                    System.out.println("Tu calificacion es C.");
                }else {
                    if  (puntaje >= 60){
                        System.out.println("Tu calificacion es D.");
                    }else{
                        System.out.println("Tu calificacion es F.");
                    }
                }
            }
        }
    }
}
