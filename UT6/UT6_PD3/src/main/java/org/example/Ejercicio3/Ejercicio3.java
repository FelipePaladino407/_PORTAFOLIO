package org.example.Ejercicio3;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> cadenas = new ArrayList<String>();

        System.out.println("Por favor, y no tan por favor, introduzca cadenas de caracteres " +
                "(una por linea, por favor, energumeno) y cuando termines escribi 'termine': ");

        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            if (linea.equals("termine")) {
                break;

            }
            cadenas.add(linea);
        }

        cadenas.sort(Comparator.comparingInt(String::length).thenComparing(String::compareTo));

        System.out.println("=== Cadenas ordenadas ===");
        for (String cadena : cadenas) {
            System.out.println(cadena);
        }
    }
}
