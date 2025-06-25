package uy.edu.ucu.aed;

import java.util.HashSet;
import java.util.Set;

public class Pruebitas {
    public static void main(final String[] args) {

        IGrafoNoDirigido grafito = UtilGrafos.cargarGrafo(
                "/home/felipe/Downloads/parcial2_alumnos_2-2024 - 1er Semestre/Parcial2/src/main/java/uy/edu/ucu/aed/parcial2/vertices.txt",
                "/home/felipe/Downloads/parcial2_alumnos_2-2024 - 1er Semestre/Parcial2/src/main/java/uy/edu/ucu/aed/parcial2/aristas.txt",
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
