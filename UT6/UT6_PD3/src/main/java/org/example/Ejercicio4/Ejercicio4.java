/**
 * IMPORTANTISIMO PARA ESTE EJERCICIO CAMBIAR EL ARGUMENTO.
 * EN LOS 3 PUNTITOS ALADO DE LA CUCARACHA DE ARRIBA A LA DERECHA,
 * PRESIONAR: EDIT. Luego, en donde dice ARGUMENTO DEL PROGRAMA, poner la ruta absoluta
 * del archivo a "archivo.txt", y luego un numero simbolizando la cantidad de veces
 * aleatorias que se imprima.
 */


package org.example.Ejercicio4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ejercicio4 {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Vamos a ver... Debe de haber al menos 2 args.");
            return;
        }

        String nombreArchvio = args[0];
        int cantidadSolicitadas = Integer.parseInt(args[1]);

        File archivo = new File(nombreArchvio);
        long tamanioBytes = archivo.length();
        int promedioLongitud = 40;
        int cantidadDeLineasEstimadas = (int) (tamanioBytes / promedioLongitud) + 1;

        List<String> lines = new ArrayList<>(cantidadDeLineasEstimadas);

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lines.add(linea);
            }
        }

        if (cantidadSolicitadas > lines.size()) {
            System.out.println("EL archivo solamente tiene " + lines.size() + " lineas, " +
                    "lo cual es insuficiente.");
            return;
        }

        // Uso "suffle" para que me haga un orden aleatorio.
        Collections.shuffle(lines);
        for (int pp = 0; pp < cantidadSolicitadas; pp++) {
            System.out.println(lines.get(pp));
        }


    }
}
