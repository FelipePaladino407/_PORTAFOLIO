package org.example.PD2;

import org.example.IArista;
import org.example.IGrafoNoDirigido;
import org.example.TGrafoNoDirigido;
import org.example.Utils.UtilGrafos;

import java.util.HashSet;
import java.util.Set;

public class PruebasKursa {
    public static void main(String[] args) {

        IGrafoNoDirigido grafo = UtilGrafos.cargarGrafo("/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UT8_PD2/src/main/java/org/example/PD2/vertices.txt",
                "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UT8_PD2/src/main/java/org/example/PD2/aristas.txt",
                false, TGrafoNoDirigido.class);

        IGrafoNoDirigido mst = grafo.KruskalOptimizado();

        Set<String> aristasUnicas = new HashSet<>();
        for (IArista arista : ((TGrafoNoDirigido) mst).getLasAristas()) {
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
