package org.example.PD3;


import org.example.PD3.Utils.ManejadorArchivosGenerico;
import org.example.PD3.Utils.UtilGrafos;

import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // cargar grafo con casas y distancias
        TGrafoRedElectrica<String> laRed = UtilGrafos.cargarGrafo(
                "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UT8_PD3/src/main/java/org/example/PD3/barrio.txt",
                "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UT8_PD3/src/main/java/org/example/PD3/distancias.txt",
                false, TGrafoRedElectrica.class);

        /*

        calcular la mejor red electrica\
        listar en el archivo "redelectrica.txt" el costo total del cableado
        y las conexiones establecidas, una por linea (origen, destino, costo)

        */

        TAristas mst = laRed.mejorRedElectrica();

        double costoTotal = mst.stream().mapToDouble(IArista::getCosto).sum();

        String[] salida = new String[mst.size() + 2];
        salida[0] = "Prim eficiente:";
        salida[1] = String.valueOf(costoTotal/2);
        int i = 2;
        for (IArista arista : mst) {
            salida[i++] = arista.getEtiquetaOrigen() + ","
                    + arista.getEtiquetaDestino() + "," + arista.getCosto();
        }
        ManejadorArchivosGenerico.escribirArchivo("/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UT8_PD3/src/main/java/org/example/PD3/redelectrica.txt", salida);

        System.out.println("Archivo redelectrica.txt generado exitosamente.");
    }
}