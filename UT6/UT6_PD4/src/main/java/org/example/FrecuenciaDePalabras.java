package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FrecuenciaDePalabras {
    public static void main(String[] args) throws IOException {
        String archivo = "libro.txt";
        Map<String, Integer> frecuencias = new HashMap<>();

        List<String> lineas = Files.readAllLines(Paths.get(archivo));
        for (String linea : lineas) {

            // Convertimos a minuscula y sacamos a volar a los tildes.
            String[] palabras = linea.toLowerCase().replaceAll("[^a-záéíóúñü\\s]", "").split("\\s+");
            for (String palabra : palabras) {
                if (palabra.isEmpty()) continue;
                frecuencias.put(palabra, frecuencias.getOrDefault(palabra, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> top10 = frecuencias.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());

        System.out.println("Top 10 palabras más frecuentes:");
        for (Map.Entry<String, Integer> entrada : top10) {
            System.out.println(entrada.getKey() + " → " + entrada.getValue());
    }
}
}
