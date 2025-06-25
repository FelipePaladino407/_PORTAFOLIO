package org.example.PD1.Ejercicio2;

import org.example.IArista;
import org.example.IGrafoNoDirigido;
import org.example.TGrafoNoDirigido;
import org.example.UtilGrafos;

import java.util.HashSet;
import java.util.Set;

public class PruebaPrimEficiente {
    public static void main(final String[] args) {

        IGrafoNoDirigido grafito = UtilGrafos.cargarGrafo(
                "/home/felipe/Documents/AED/UT8/UT8_PD1/src/main/java/org/example/PD1/Ejercicio2/vertices.txt",
                "/home/felipe/Documents/AED/UT8/UT8_PD1/src/main/java/org/example/PD1/Ejercicio2/aristas.txt",
                false,
                TGrafoNoDirigido.class);

        IGrafoNoDirigido mstEficiente = grafito.PrimEficiente();

        System.out.println("Aristas del MST (Prim eficiente):");
        Set<String> aristasUnicas = new HashSet<>();
        for (IArista arista : ((TGrafoNoDirigido) mstEficiente).getLasAristas()) {
            String clave = arista.getEtiquetaOrigen().compareTo(arista.getEtiquetaDestino()) < 0 ?
                    arista.getEtiquetaOrigen() + "-" + arista.getEtiquetaDestino() :
                    arista.getEtiquetaDestino() + "-" + arista.getEtiquetaOrigen();

            if (aristasUnicas.add(clave)) {
                System.out.printf("%s - %s : %.1f\n",
                        arista.getEtiquetaOrigen(),
                        arista.getEtiquetaDestino(),
                        arista.getCosto());
            }
        }
    }
}
