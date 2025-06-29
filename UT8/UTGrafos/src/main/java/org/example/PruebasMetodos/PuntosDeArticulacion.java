package org.example.PruebasMetodos;

import org.example.IGrafoNoDirigido;
import org.example.TGrafoNoDirigido;
import org.example.Utils.UtilGrafos;

import java.util.Set;

public class PuntosDeArticulacion {
    public static void main(String[] args) {

        IGrafoNoDirigido grafo = UtilGrafos.cargarGrafo("/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UTGrafos/src/main/java/org/example/PruebasMetodos/vertices.txt",
                "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UTGrafos/src/main/java/org/example/PruebasMetodos/aristas.txt",
                false, TGrafoNoDirigido.class);

        Set<Comparable> articulaciones = grafo.puntosArticulacion();
        System.out.println("Puntos de articulación: " + articulaciones);
}
}
