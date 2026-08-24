package Ejercicio_while;

public class Ejer_1divN {
    public static void main(String[] args) {
        //Escribir un programa que, dado un número entero positivo n, calcule la suma: 1 + 1/2 + 1/3 + ... + 1/n.
        int n = 9;
        double suma = 0;
        while (n > 0){
            suma = suma + 1.0/n; //usamos double 1 , por que Java hace división entera.
            n--;
        }
        System.out.println(suma);
    }
}
