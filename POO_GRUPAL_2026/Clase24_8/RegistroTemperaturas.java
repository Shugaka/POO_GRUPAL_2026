package Clase24_8;

public class RegistroTemperaturas {
    //arreglo para almacenar las temperaturas de los 7 dias
    private double[] temperatura; // para que el arreglo no se puede modificar directamente desde afuera.

    public RegistroTemperaturas(){
        //creamos el arreglo con 7 elementos.
        this.temperatura = new double[7];
    }

    //1. cargar las temperaturas en el arreglo
    public void cargarTemperaturas(double[] valores){
        if (valores.length == temperatura.length){ //si tienen 7 elemetos copia cada tempeeratura una por una.
            for (int i = 0; i < temperatura.length; i++){
                temperatura[i] = valores[i];
            }
        } else {
            System.out.println(" Se esperaban exactamente 7 temperaturas");
        }
    }

    //2. Calcular e imprimir el promedio de la semana.
    public double calcularPromedio(){
        double suma = 0.0;
        for (int i = 0; i < temperatura.length; i++){
            suma += temperatura[i];
        }
        return suma / temperatura.length;
    }

    //3. Encontrar la temperatura máxima y la mínima.
    public double encontrarMaximoTemperatura(){
        double maximo = temperatura[0];
        for (int i = 0; i < temperatura.length; i++){
            if (temperatura[i] > maximo){
                maximo = temperatura[i];
            }
        }
        return maximo;
    }

    public double encontrarMinimoTemperatura(){
        double minimo = temperatura[0];
        for (int i = 0; i < temperatura.length; i++){
            if (temperatura[i] < minimo){
                minimo = temperatura[i];
            }
        }
        return minimo;
    }

    //4. Contar cuántos días superaron un umbral dado (por ejemplo, 30.0 grados).
    //devuelve la cantidad de dias que superaron el umbral.
    public int contarDiaSobreUmbral(double umbral){
        int contador = 0;
        for (int i = 0; i < temperatura.length; i++){
            if (temperatura[i] > umbral){
                contador++;
            }
        }
        return contador;
    }

    //5. Recorrer el arreglo usando length e imprimir cada día con su temperatura.
    public void imprimirTemperaturas(){
        System.out.println("Registro Temperaturas semanal:");
        for (int i = 0; i < temperatura.length; i++){
            // uso i de 1 al 7 para mostrar temperatura de cada dias
            System.out.println("Dia " + (i+1) + ": " + "temperatura " + temperatura[i]);
        }
    }

    //main
    public static void main(String [] args){
        RegistroTemperaturas registro = new RegistroTemperaturas();

        double[] temperaturas = {22.5, 25.6, 24.3, 30.5, 33.4, 36.2, 27.2};

        //cargar temperaturas
        registro.cargarTemperaturas(registro.temperatura);
        //mostrar el registros
        registro.imprimirTemperaturas();

        // calcular promedio de temperatura semanal
        double promedio = registro.calcularPromedio();
        System.out.println("Temperatura Promedio: " + promedio);

        double maximo = registro.encontrarMaximoTemperatura();
        System.out.println("Temperatura Maximo: " + maximo);

        double minimo = registro.encontrarMinimoTemperatura();
        System.out.println("Temperatura Minimo: " + minimo);

        int diaumbral = registro.contarDiaSobreUmbral(30.0);
        System.out.println("Dia que superaron umbral: " + diaumbral);
    }
}
