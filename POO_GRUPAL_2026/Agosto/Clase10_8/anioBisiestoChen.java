package Agosto.Clase10_8;
import java.util.Scanner;
public class anioBisiestoChen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un año para ver si es bisiesto: ");
        int ano = sc.nextInt();

        if (ano % 4 == 0 && ano % 100 != 0 || ano % 400 == 0){
            System.out.println("Si es bisiesto");
        } else{
            System.out.println("No es bisiesto");
        }
        sc.close();
    }

}
