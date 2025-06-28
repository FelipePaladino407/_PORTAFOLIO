package org.example.PD4;

import org.example.IGrafoNoDirigido;
import org.example.IVertice;
import org.example.TGrafoNoDirigido;
import org.example.Utils.UtilGrafos;

public class PruebasEstanConectados {
    public static void main(String[] args) {

        IGrafoNoDirigido grafo = UtilGrafos.cargarGrafo("/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UT8_PD4/src/main/java/org/example/PD4/vertisongos.txt",
                "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UT8_PD4/src/main/java/org/example/PD4/aristoteles.txt",
                false, TGrafoNoDirigido.class);

        IVertice<String> a =
                ((TGrafoNoDirigido<String>) grafo).getVertices().get("A");
        IVertice<String> d =
                ((TGrafoNoDirigido<String>) grafo).getVertices().get("D");
        IVertice<String> z =
                ((TGrafoNoDirigido<String>) grafo).getVertices().get("Z");


        if (grafo.conectados(a, z)) {
            System.out.println("Los vertices " + a.getEtiqueta() + " y " + d.getEtiqueta() + " estan conectados.");
        }
        else{
            System.out.println("Los vertices " + a.getEtiqueta() + " y " + d.getEtiqueta() + " NO estan conectados.");
        }



    }
}
