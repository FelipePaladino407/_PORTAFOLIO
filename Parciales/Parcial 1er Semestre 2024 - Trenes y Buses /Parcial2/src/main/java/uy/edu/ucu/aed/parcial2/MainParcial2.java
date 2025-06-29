package uy.edu.ucu.aed.parcial2;

import uy.edu.ucu.aed.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Algoritmo y Estrucutras de Datos
 * Parcial 2 - Parte 3
 *
 */
public class MainParcial2 {
    public static void main(String[] args) {
        // Cargar grafo a partir de archivos de entrada
        TGrafoDeLaRed grafo = UtilGrafos.cargarGrafo("/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Parcial 1er Semestre 2024 - Segunda version/Parcial2/src/main/java/uy/edu/ucu/aed/parcial2/vertices.txt",
                "/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Parcial 1er Semestre 2024 - Segunda version/Parcial2/src/main/java/uy/edu/ucu/aed/parcial2/aristas.txt", false, TGrafoDeLaRed.class,
                TVerticeDeLaRed.class);

        // Calculo de todos los caminos entre dos vertices
        TCaminos<TEstacionDeLaRed> caminos = grafo.caminosDesdeHasta("Vertice_3", "Vertice_4");

        // Escribir archivo de salida con el resultado de la llamada anterior, con los
        // caminos ordenados de menor a mayor costo, uno por línea.

        // Paso los caminos a una lista y los ordeno por costo:
        List<TCamino<TEstacionDeLaRed>> lista =
                new ArrayList<>(caminos.getCaminos());
        lista.sort(Comparator.comparing(TCamino::getCostoTotal));

        String[] salida = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            TCamino<TEstacionDeLaRed> camino = lista.get(i);
            String textoCamino = camino.imprimirEtiquetas() + "- costo total: "
                    + camino.getCostoTotal();
            salida[i] = textoCamino;
        }

        ManejadorArchivosGenerico.escribirArchivo("/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Parcial 1er Semestre 2024 - Segunda version/Parcial2/src/main/java/uy/edu/ucu/aed/parcial2/salida.txt", salida);

        System.out.println("Ruta escrita en: 'salida.txt'. Y tiene un tamano de: " + salida.length);



    }
}
