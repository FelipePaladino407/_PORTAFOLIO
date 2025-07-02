package org.example.PruebasMetodos.dijkstraCaminos;


import org.example.TCamino;
import org.example.TGrafoNoDirigido;
import org.example.Utils.UtilGrafos;

public class MainDijkstra {

    public static void main(String[] args) {
        // 1) Rutas a los archivos de texto
        String archivoVertices = "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UTGrafos/src/main/java/org/example/PruebasMetodos/dijkstraCaminos/vertices.txt";
        String archivoAristas  = "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UTGrafos/src/main/java/org/example/PruebasMetodos/dijkstraCaminos/aristas.txt";

        // 2) Cargar el grafo no dirigido con UtilGrafos
        //    (inserta también la inversa de cada arista automáticamente)
        TGrafoNoDirigido<String> grafo =
                UtilGrafos.cargarGrafo(
                        archivoVertices,
                        archivoAristas,
                        false,
                        TGrafoNoDirigido.class
                );

        // 3) Definir las etiquetas de los dos alumnos y la puerta
        Comparable<String> alumno1 = "A";
        Comparable<String> alumno2 = "D";
        Comparable<String> puerta   = "P";

        // 4) Calcular rutas mínimas usando Dijkstra sin prioridad
        TCamino<String> ruta1 = grafo.caminoMinimoDijkstraSinPQ(alumno1, puerta);
        TCamino<String> ruta2 = grafo.caminoMinimoDijkstraSinPQ(alumno2, puerta);

        // 5) Obtener costos (lo acumulas en cada arista)
        Double costo1 = ruta1 != null ? ruta1.getCostoTotal() : Double.POSITIVE_INFINITY;
        Double costo2 = ruta2 != null ? ruta2.getCostoTotal() : Double.POSITIVE_INFINITY;

        // 6) Mostrar los caminos y sus costos
        if (ruta1 != null) {
            System.out.println("Alumno 1 (A) → Puerta: "
                    + ruta1.imprimirEtiquetas()
                    + "  | Costo total: " + costo1);
        } else {
            System.out.println("Alumno 1 (A) no tiene camino a la puerta");
        }

        if (ruta2 != null) {
            System.out.println("Alumno 2 (D) → Puerta: "
                    + ruta2.imprimirEtiquetas()
                    + "  | Costo total: " + costo2);
        } else {
            System.out.println("Alumno 2 (D) no tiene camino a la puerta");
        }

        // 7) Comparar y declarar ganador
        if (costo1 < costo2) {
            System.out.println("Gana Alumno 1");
        } else if (costo2 < costo1) {
            System.out.println("Gana Alumno 2");
        } else if (costo1.equals(costo2) && costo1 < Double.POSITIVE_INFINITY) {
            System.out.println("Empate");
        } else {
            System.out.println("Ninguno puede llegar a la puerta");
        }
    }
}
