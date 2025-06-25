package org.example.Ejercicio1;

import org.example.TGrafoDirigido;
import org.example.UtilGrafos;

public class PruebaVueloSeguroFloyd_2 {
    public static void main(String[] args) {

        TGrafoDirigido grafito = UtilGrafos.cargarGrafo(
                "/home/felipe/Documents/AED/UT7_PD3/src/main/java/org/example/aereopuertos.txt",
                "/home/felipe/Documents/AED/UT7_PD3/src/main/java/org/example/conexiones.txt",
                false,
                TGrafoDirigido.class);

        Double[][] matrizConElAmigoFloyd = grafito.floyd();

        UtilGrafos.imprimirMatrizMejorado(matrizConElAmigoFloyd, grafito.getVertices(), "Conexiones - Vuelo Seguro");

        System.out.println("Centro de mantenimiento (vertice con " +
                "menor excentricidad): " + grafito.centroDelGrafo());

    }

    /**
     * Respondiendo preguntas ejercicio 1:
     *
     *1. El costo de volar de Montevideo a Rio de Janeiro es:
     * a. 1980.
     * b. 3780.
     * c. 1000.
     * d. 980.
     * Respuesta: 3780.
     *
     * 2. El costo de volar de Montevideo a Curitiba es:
     * a. 2580
     * b. 3780.
     * c. 1980.
     * d. Ninguna de las anteriores.
     * Resupuesta: 2580.
     *
     * 3. Los servicios de mantenimiento se instalan en:
     * a. Montevideo
     * b. Punta del Este
     * c. Curitiba
     * d. Porto Alegre
     */
}
