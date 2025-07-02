package org.example.PruebasMetodos.bpfCaminos;

import org.example.TCamino;
import org.example.TGrafoNoDirigido;
import org.example.Utils.UtilGrafos;

public class PruebasBpfCaminos {

    public static void main(String[] args) {
        // 1) Rutas a los .txt (ajusta según tu estructura)
        String archivoVertices = "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UTGrafos/src/main/java/org/example/PruebasMetodos/bpfCaminos/vertices.txt";
        String archivoAristas  = "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UTGrafos/src/main/java/org/example/PruebasMetodos/bpfCaminos/aristas.txt";

        // 2) Cargar el grafo no dirigido usando UtilGrafos
        //    Parámetros: (rutaVertices, rutaAristas, ignoreHeader, claseDelGrafo)
        TGrafoNoDirigido<String> grafo = UtilGrafos.cargarGrafo(
                archivoVertices,
                archivoAristas,
                false,
                TGrafoNoDirigido.class
        );

        // 3) Definir etiquetas de los dos alumnos y la puerta
        Comparable<String> alumno1 = "A";
        Comparable<String> alumno2 = "B";
        Comparable<String> puerta   = "P";

        // 4) Calcular rutas mínimas con BFS (peso=1)
        TCamino<String> ruta1 = grafo.caminoMinimoBFS(alumno1, puerta);
        TCamino<String> ruta2 = grafo.caminoMinimoBFS(alumno2, puerta);



        // 5) Obtener costes (número de pasos)
        Double pasos1 = ruta1.getCostoTotal();
        Double pasos2 = ruta2.getCostoTotal();

        // (6) Mostrar resultados
        System.out.println("Alumno 1 (A) → Puerta: " + ruta1.imprimirEtiquetas()
                + "  | Pasos: " + pasos1);
        System.out.println("Alumno 2 (D) → Puerta: " + ruta2.imprimirEtiquetas()
                + "  | Pasos: " + pasos2);

        if (pasos1 < pasos2) {
            System.out.println("Gana Alumno 1");
        } else if (pasos2 < pasos1) {
            System.out.println("Gana Alumno 2");
        } else {
            System.out.println("Empate");
        }
    }
}


