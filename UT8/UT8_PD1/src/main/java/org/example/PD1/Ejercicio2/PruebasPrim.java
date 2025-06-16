package org.example.PD1.Ejercicio2;

import org.example.IArista;
import org.example.IGrafoNoDirigido;
import org.example.TGrafoNoDirigido;
import org.example.Utils.UtilGrafos;

import java.util.HashSet;
import java.util.Set;

public class PruebasPrim {
    public static void main(final String[] args) {

        IGrafoNoDirigido grafito = UtilGrafos.cargarGrafo(
                "/home/felipe/Documents/AED/UT8_PD1/src/main/java/org/example/PD1/Ejercicio2/vertices.txt",
                "/home/felipe/Documents/AED/UT8_PD1/src/main/java/org/example/PD1/Ejercicio2/aristas.txt",
                false,
                TGrafoNoDirigido.class);

        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(grafito.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matrizCostos, grafito.getVertices(), "Grafo choto");

        IGrafoNoDirigido mst = grafito.Prim();
        System.out.println("pringo: "+mst);

        /**
         * Esto funciona pero devuelve las aristas repetidas.
         *
         System.out.println("Aristas del árbol generador mínimo:");
         for (IArista arista : ((TGrafoNoDirigido) mst).getLasAristas()) {
         System.out.println(arista.getEtiquetaOrigen() + " - " +
         arista.getEtiquetaDestino() + " : " +
         arista.getCosto());
         }
         **/

        System.out.println("Aristas únicas del árbol generador mínimo:");
        Set<String> aristasUnicas = new HashSet<>();
        for (IArista arista : ((TGrafoNoDirigido) mst).getLasAristas()) {
            // Crear una representación única independiente del orden
            String claveUnica = arista.getEtiquetaOrigen().compareTo(arista.getEtiquetaDestino()) < 0
                    ? arista.getEtiquetaOrigen() + "-" + arista.getEtiquetaDestino()
                    : arista.getEtiquetaDestino() + "-" + arista.getEtiquetaOrigen();

            if (aristasUnicas.add(claveUnica)) {
                System.out.println(arista.getEtiquetaOrigen() + " - " +
                        arista.getEtiquetaDestino() + " : " +
                        arista.getCosto());
            }
        }

    }
}
