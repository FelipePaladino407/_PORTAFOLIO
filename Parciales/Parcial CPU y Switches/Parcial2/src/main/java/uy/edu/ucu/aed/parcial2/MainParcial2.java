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
public class MainParcial2 
{
    public static void main( String[] args )
    {
        // Cargar grafo a partir de archivos de entrada
        TGrafoDeLaRed grafo = UtilGrafos.cargarGrafo("/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Parcial_1/Parcial2/src/main/java/uy/edu/ucu/aed/parcial2/vertices.txt",
                "/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Parcial_1/Parcial2/src/main/java/uy/edu/ucu/aed/parcial2/aristas.txt",
                false, TGrafoDeLaRed.class, TVerticeDeLaRed.class);

        // Calculo de todos los caminos entre dos vertices
        TCaminos<TNodoDeLaRed> caminos = grafo.caminosDesdeHasta("Vertice_3", "Vertice_4");
        
        // Escribir archivo de salida con el resultado de la llamada anterior, con los caminos ordenados de menor a mayor costo, uno por línea.
        List<TCamino<TNodoDeLaRed>> listonga = new ArrayList<>(caminos.getCaminos());
        listonga.sort(Comparator.comparingDouble(TCamino::getCostoTotal));

        // Preparo las lineas de salida:
        String[] lineas = new String[listonga.size()];
        for (int i = 0; i < listonga.size(); i++) {
            TCamino<TNodoDeLaRed> camino = listonga.get(i);
            // Imprimir etiquetas:
            lineas[i] = camino.imprimirEtiquetas() + " - Costo: " + camino.getCostoTotal();
        }

        // Volcamos el archivo:
        String rutaSalida = "/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Parcial_1/Parcial2/src/main/java/uy/edu/ucu/aed/parcial2/caminos_ordenados.txt";
        ManejadorArchivosGenerico.escribirArchivo(rutaSalida, lineas);

        System.out.println("Caminos ordenados escritos en: " + rutaSalida);

    }
}
