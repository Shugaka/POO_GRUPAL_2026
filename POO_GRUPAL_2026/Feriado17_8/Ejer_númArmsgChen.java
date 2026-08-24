package Feriado17_8;


public class Ejer_númArmsgChen {
    public static void main(String args[]) {
        int num = 371;
        int numOrig = num;
        int ultimo;
        double digito = Math.floor(Math.log10(num)) + 1;
        double suma = 0;

        //num/10 sacar el ultimo digito
        //num%10 tomando el ultimo digito
        while (num > 0){
            ultimo = num % 10;
            suma = suma + Math.pow(ultimo, digito);
            num = num / 10;
        }

        if (suma == numOrig){
            System.out.println(numOrig + " es numero de Armstrong");
        } else {
            System.out.println(numOrig + " no es numero de Armstrong");
        }

    }
}
