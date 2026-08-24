package Clase10_8_26;

public class Ejercicio_mes {
    public static void main(String[] args) {
        String mesInput = "4";
        String mes;
        int Dia;

        if (mesInput == "1"){
            mes = "Enero";
            Dia = 30;
        } else if (mesInput == "2") {
            mes = "Febrero";
            Dia = 28;
        } else if (mesInput == "3") {
            mes = "Marzo";
            Dia = 29;
        } else if (mesInput == "4") {
            mes = "Abril";
            Dia = 30;
        } else if (mesInput == "5") {
            mes = "Mayo";
            Dia = 31;
        } else if (mesInput == "6") {
            mes = "Junio";
            Dia = 30;
        } else if (mesInput == "7") {
            mes = "Julio";
            Dia = 31;
        } else if (mesInput == "8") {
            mes = "Agosto";
            Dia = 31;
        } else if (mesInput == "9") {
            mes = "Septiembre";
            Dia = 30;
        } else if (mesInput == "10") {
            mes = "octubre";
            Dia = 31;
        } else if (mesInput == "11") {
            mes = "Noviembre";
            Dia = 30;
        } else {
            mes = "Diciembre";
            Dia = 31;
        }
        System.out.println("Mes: " + mes + ", Dia: " + Dia);
    }
}
