package org.example.PD1.Ejercicio3;

import org.example.IVertice;
import org.example.TGrafoNoDirigido;
import org.example.TVertice;
import org.example.Utils.UtilGrafos;

import java.util.Collection;

public class PruebasBea {
        public static void main(String[] args) {
            // Cargar el grafo desde los archivos .txt
            TGrafoNoDirigido grafo = (TGrafoNoDirigido) UtilGrafos.cargarGrafo(
                    "/home/felipe/Documents/AED/UT8_PD1/src/main/java/org/example/PD1/Ejercicio3/vertices.txt",
                    "/home/felipe/Documents/AED/UT8_PD1/src/main/java/org/example/PD1/Ejercicio3/aristas.txt",
                    false, // Es no dirigido
                    TGrafoNoDirigido.class
            );

            // Ejecutar BEA
            Collection<IVertice> recorrido = grafo.bea();

            // Imprimir recorrido
            System.out.println("Recorrido en amplitud (BEA):");
            for (IVertice v : recorrido) {
                System.out.println(v.getEtiqueta());
            }
        }
    }



