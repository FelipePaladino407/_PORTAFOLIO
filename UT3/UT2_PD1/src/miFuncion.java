import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class miFuncion {
    public static void main(String[] args) {
        try{
            Scanner scannete =  new Scanner(new File("C:\\Users\\Estudiante UCU\\IdeaProjects\\UT2_PD1\\src\\numeros.txt"));
            int N = Integer.parseInt(scannete.nextLine().trim());
            double[] array = new double[N];
            for (int pp = 0; pp < N; pp++) {
                array[pp] = Double.parseDouble(scannete.nextLine().trim());
            }
            scannete.close();

            int cuentaCuenta = 0;

            for (int q = 0; q < N-1; q++){
                for (int j = q+1; j < N; j++){
                    if (array[j] > array[j-1]){
                        // Lo que hace aca es intercambiar los elementos, como sugiere el algoritmo de la consigna.
                        double temp = array[j];
                        array[j] = array[j-1];
                        array[j-1] = temp;
                        cuentaCuenta++;
                    }
                }
            }

            System.out.println("Largo del total de numeros: " + N);
            System.out.println("Cantidad de intercambios efectuados: " + cuentaCuenta);
            System.out.println("Primer elementete en la lista: " + array[0]);
            System.out.println("Ultimo elementete en la lista: " + array[N-1]);
        }
        catch (FileNotFoundException e){
            System.err.println("ERROR ERROR!!!!: El archivo no fue encontrado.");
        }
        catch (Exception e){
            System.err.println("ERROR ERROR!!!!: " + e.getMessage());
        }
    }
}