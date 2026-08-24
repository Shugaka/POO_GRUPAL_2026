package Feriado17_8;

public class Ejer_Fibonacci  {
    public static void main(String[] args) {
        //Escribir un programa que calcule los primeros n términos de la sucesión de Fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, ..
        int anterior = 0;
        int actual = 1;
        int n = 10;
        int contador = 0;
        System.out.print(anterior + " ");
        while  (contador<n){
            System.out.print(actual + " ");
            int siguiente = anterior + actual;
            anterior = actual;
            actual = siguiente;
            contador++;
        }
    }
}
