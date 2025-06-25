package org.example.Ejercicio1;

import org.example.TGrafoDirigido;
import org.example.UtilGrafos;

public class PruebaVueloSeguro {
    public static void main(String[] args) {

        TGrafoDirigido grafito = UtilGrafos.cargarGrafo(
                "/home/felipe/Documents/AED/UT7_PD3/src/main/java/org/example/aereopuertos.txt",
                "/home/felipe/Documents/AED/UT7_PD3/src/main/java/org/example/conexiones.txt",
                true,
                TGrafoDirigido.class);

        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(grafito.getVertices());

        UtilGrafos.imprimirMatrizMejorado(matrizCostos, grafito.getVertices(), "Conexiones - Vuelo Seguro");

        grafito.bpf("Montevideo");
    }
}
