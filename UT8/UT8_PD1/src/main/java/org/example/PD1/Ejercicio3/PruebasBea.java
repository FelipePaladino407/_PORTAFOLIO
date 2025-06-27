package org.example.PD1.Ejercicio3;

import org.example.IVertice;
import org.example.TGrafoNoDirigido;
import org.example.UtilGrafos;

import java.util.Collection;

public class PruebasBea {
        public static void main(String[] args) {

            TGrafoNoDirigido grafo = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
                    "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UT8_PD1/src/main/java/org/example/PD1/Ejercicio2/vertices.txt",
                    "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UT8_PD1/src/main/java/org/example/PD1/Ejercicio2/aristas.txt",
                    false,
                    TGrafoNoDirigido.class
            );

            Collection<IVertice> recorrido = grafo.bea();

            System.out.println("Recorrido en amplitud (BEA):");
            for (IVertice v : recorrido) {
                System.out.println(v.getEtiqueta());
            }
        }
    }



