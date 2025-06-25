package org.example.PD5;

import org.example.TGrafoDirigido;
import org.example.UtilGrafos;

import java.util.List;

public class PruebasTopo {
    public static void main(String[] args) {
        TGrafoDirigido grafo = UtilGrafos.cargarGrafo(
                "/home/felipe/Documents/AED/UT7_PD5/src/main/java/org/example/aereopuertos.txt",
                "/home/felipe/Documents/AED/UT7_PD5/src/main/java/org/example/conexiones.txt",
                false,
                TGrafoDirigido.class

        );

        List<Comparable> orden_Topo = grafo.ordenTopologicoSkiena();
        if (!orden_Topo.isEmpty()){
            System.out.println("Orden Topo: ");
            for (Comparable Gasporgor : orden_Topo){
                System.out.println(Gasporgor + " ");
            }
        }
        else{
            System.out.println("El grafo tiene ciclos, no se puede hacer Topo.");
        }
    }}

