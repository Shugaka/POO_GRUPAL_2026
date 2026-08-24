package Clase10_8;

public class calficacionUriel {
    public static void main (String[] args){
        int puntaje = 45;

        char nota1 = 'A';
        char nota2 = 'B';
        char nota3 = 'C';
        char nota4 = 'D';
        char nota5 = 'F';

        if (puntaje >= 90){
            System.out.println(nota1);
        } else if (puntaje >= 80) {
            System.out.println(nota2);
        } else if (puntaje >= 70) {
            System.out.println(nota3);
        } else if (puntaje >= 60) {
            System.out.println(nota4);
        } else {
            System.out.println(nota5);
        }
    }
}
