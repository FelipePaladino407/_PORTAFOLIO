package org.example.PD8;
import org.example.*;

import java.util.List;

public class PruebasArcos {
    public static void main(String[] args) {

        TVertice<String> A = new TVertice<>("A");
        TVertice<String> B = new TVertice<>("B");
        TVertice<String> C = new TVertice<>("C");
        TVertice<String> D = new TVertice<>("D");

        A.insertarAdyacencia(1.0, B);
        B.insertarAdyacencia(1.0, C);
        A.insertarAdyacencia(1.0, C);
        C.insertarAdyacencia(1.0, A); // Arco de retroceso.

        List<IVertice<String>> verts = List.of(A, B, C, D);
        List<IArista> aris = List.of();
        TGrafoDirigido<String> grafo = new TGrafoDirigido<>(verts, aris);

        grafo.clasificarArcos("A");
    }
}
