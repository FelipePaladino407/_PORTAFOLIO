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

        String vertices = "/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Parcial 2 Trenes y Buses 2/Parcial2/src/main/java/uy/edu/ucu/aed/parcial2/vertices.txt";
        String aristas = "/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Parcial 2 Trenes y Buses 2/Parcial2/src/main/java/uy/edu/ucu/aed/parcial2/aristas.txt";
        TGrafoDeLaRed grafo = UtilGrafos.cargarGrafo(vertices,
                aristas, false, TGrafoDeLaRed.class,
                TVerticeDeLaRed.class);

        // Cal culo de todos los caminos entre dos vertices
        TCaminos<TEstacionDeLaRed> caminos = grafo.caminosDesdeHasta("Vertice_3", "Vertice_4");

        // Escribir archivo de salida con el resultado de la llamada anterior, con los
        // caminos ordenados de menor a mayor costo, uno por línea.

        List<TCamino<TEstacionDeLaRed>> lista =
                new ArrayList<>(caminos.getCaminos());
        lista.sort(Comparator.comparing(TCamino::getCostoTotal));

        String[] salida = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            TCamino<TEstacionDeLaRed> caminito = lista.get(i);
            String textoCamino = caminito.imprimirEtiquetas()
                    + " - Costo total: "
                    + caminito.getCostoTotal();
            salida[i] = textoCamino;


        }
        String output = "/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Parcial 2 Trenes y Buses 2/Parcial2/src/main/java/uy/edu/ucu/aed/parcial2/salida.txt";

        ManejadorArchivosGenerico.escribirArchivo(output, salida);

        System.out.println("Ruta escrita en 'salida.txt'");


    }
}
