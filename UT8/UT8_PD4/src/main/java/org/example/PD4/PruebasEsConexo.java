package org.example.PD4;

import org.example.*;
import org.example.Utils.UtilGrafos;


public class PruebasEsConexo {
    public static void main(String[] args) {

        IGrafoNoDirigido grafo = UtilGrafos.cargarGrafo("/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UT8_PD4/src/main/java/org/example/PD4/vertices.txt",
                "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UT8_PD4/src/main/java/org/example/PD4/aristas.txt",
                false, TGrafoNoDirigido.class);

        if (grafo.esConexo()){
            System.out.println("El grafo en cuestion, es conexo.");
        }
        else{
            System.out.println("El grafo no es conexo, mi hermano.");
        }
    }
}
