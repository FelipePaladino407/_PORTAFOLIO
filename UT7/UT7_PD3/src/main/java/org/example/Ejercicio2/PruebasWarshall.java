package org.example.Ejercicio2;

import org.example.TGrafoDirigido;
import org.example.UtilGrafos;

/**
 * Programa de ejemplo para Warshall, no cumple con la consigna del UT7_PD3 EJ.2
 * Por favor, para ver eso, verifique la clase "PruebasWarshallEjercicio2".
 */
public class PruebasWarshall {
    public static void main(String[] args) {

        TGrafoDirigido grafete = UtilGrafos.cargarGrafo(
                "/home/felipe/Documents/AED/UT7_PD3/src/main/java/org/example/aereopuertos.txt",
                "/home/felipe/Documents/AED/UT7_PD3/src/main/java/org/example/conexiones.txt",
                false,
                TGrafoDirigido.class);

        boolean[][] warshall = grafete.warshall();
        Comparable[] etiquetas = grafete.getVertices().keySet().toArray(new Comparable[0]);

        System.out.println("Matriz de Accesibilidad (Warshall):");
        System.out.print("     ");
        for (Comparable etiqueta : etiquetas) {
            System.out.printf("%15s", etiqueta);
        }
        System.out.println();

        for (int i = 0; i < warshall.length; i++) {
            System.out.printf("%15s", etiquetas[i]);
            for (int j = 0; j < warshall.length; j++) {
                System.out.printf("%15s", warshall[i][j] ? "✓" : "×");
            }
            System.out.println();
        }




    }
}
