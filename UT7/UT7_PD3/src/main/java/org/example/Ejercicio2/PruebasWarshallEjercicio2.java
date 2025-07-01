package org.example.Ejercicio2;

import org.example.TGrafoDirigido;
import org.example.UtilGrafos;

/**
 * Programa para verificar si extiste conexion entre dos ciudades distnitas utilizando
 * el algoritmo de Warshall. Vaya con Dios.
 */
public class PruebasWarshallEjercicio2 {

    /**
     * Funcion auxiliar para encontrar el indice de una cierta ciudad.
     * @param array
     * @param ciudad
     * @return pp or -1;
     */
    private static int getIndiceCiudad(Comparable[] array, String ciudad) {
        for (int pp = 0; pp < array.length; pp++) {
            if (array[pp].toString().equalsIgnoreCase(ciudad)) {
                return pp;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        TGrafoDirigido grafito = UtilGrafos.cargarGrafo(
                "/home/felipe/Documents/AED/_PORTAFOLIO/UT7/UT7_PD3/src/main/java/org/example/aereopuertos.txt",
                "/home/felipe/Documents/AED/_PORTAFOLIO/UT7/UT7_PD3/src/main/java/org/example/conexiones.txt",
                false,
                TGrafoDirigido.class);

        boolean[][] warshall = grafito.warshall();

        Comparable[] etiquetas = grafito.getVertices().keySet().toArray(new Comparable[0]);

        String ciudad1 = "Montevideo";
        String ciudad2 = "Curitiba";

        int pp = getIndiceCiudad(etiquetas, ciudad1);
        int gg = getIndiceCiudad(etiquetas, ciudad2);

        if (pp != -1 && gg != -1) {
            System.out.println("Pregunta preguntosa, Hay conexion entre " + ciudad1 + " y "
                    + ciudad2 + "?" + (warshall[pp][gg]
                    ? "Si, la hay\n" : "\nQue lastima, no la hay."));
        }
        else {
            System.out.println("Mhh, parece ser que alguna de las dos ciudades, o bien" +
                    "ambas, no se encuentran en el infame grafo. Por favor chequealo tu.");
        }

        System.out.println("=======================");


        String ciudad3 = "Porto_Alegre";
        String ciudad4 = "Santos";

        int xx = getIndiceCiudad(etiquetas, ciudad3);
        int uu = getIndiceCiudad(etiquetas, ciudad4);

        if (xx != -1 && uu != -1) {
            System.out.println("Pregunta preguntosa, Hay conexion entre " + ciudad3 + " y "
                    + ciudad4 + "?" + (warshall[xx][uu]
                    ? "\nSi, la hay." : "\nQue lastima, no la hay."));
        }
        else {
            System.out.println("Mhh, parece ser que alguna de las dos ciudades, o bien" +
                    "ambas, no se encuentran en el infame grafo. Por favor chequealo tu.");
        }


    }

}
