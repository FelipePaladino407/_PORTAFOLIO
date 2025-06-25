package org.example.PD4;

import org.example.TCamino;
import org.example.TCaminos;
import org.example.TGrafoDirigido;
import org.example.UtilGrafos;

public class PruebasCaminos {
    public static void main(String[] args) {
        TGrafoDirigido grafo = UtilGrafos.cargarGrafo(
                "/home/felipe/Documents/AED/UT7_PD4/src/main/java/org/example/aereopuertos.txt",
                "/home/felipe/Documents/AED/UT7_PD4/src/main/java/org/example/conexiones.txt",
                false,
                TGrafoDirigido.class

        );

        TCaminos caminito = grafo.todosLosCaminos("Buenos_Aires", "Asuncion");
        caminito.imprimirCaminosConsola();
    }


}
