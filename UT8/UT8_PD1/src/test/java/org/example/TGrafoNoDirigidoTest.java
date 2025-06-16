package org.example;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
class TGrafoNoDirigidoTest {

    @Test
    void prim() {
        TVertice a = new TVertice("A");
        TVertice b = new TVertice("B");
        TVertice c = new TVertice("C");
        TVertice d = new TVertice("D");

        LinkedList<TVertice> vertices = new LinkedList<>();
        vertices.add(a);
        vertices.add(b);
        vertices.add(c);
        vertices.add(d);

        // Crear aristas (grafo no dirigido)
        LinkedList<IArista> aristas = new LinkedList<>();
        aristas.add(new TArista("A", "B", 1));
        aristas.add(new TArista("A", "C", 4));
        aristas.add(new TArista("B", "D", 3));
        aristas.add(new TArista("C", "D", 2));

        // Cargar grafo
        TGrafoNoDirigido grafo = new TGrafoNoDirigido(vertices, aristas);

        // Ejecutar Prim
        IGrafoNoDirigido mst = grafo.Prim();

        // Validar cantidad de aristas
        int cantidadEsperada = grafo.getVertices().size() - 1;
        assertEquals(cantidadEsperada, ((TGrafoNoDirigido) mst).getLasAristas().size(), "Cantidad incorrecta de aristas");

        // Validar costo total
        double costoTotal = ((TGrafoNoDirigido) mst).getLasAristas().stream()
                .mapToDouble(IArista::getCosto)
                .sum();

        assertEquals(6.0, costoTotal, 0.01, "Costo total incorrecto");

        // Verificamos que las aristas sean las esperadas
        boolean contieneAB = ((TGrafoNoDirigido) mst).getLasAristas().stream().anyMatch(a1 ->
                (a1.getEtiquetaOrigen().equals("A") && a1.getEtiquetaDestino().equals("B")) ||
                        (a1.getEtiquetaOrigen().equals("B") && a1.getEtiquetaDestino().equals("A"))
        );
        boolean contieneBD = ((TGrafoNoDirigido) mst).getLasAristas().stream().anyMatch(a1 ->
                (a1.getEtiquetaOrigen().equals("B") && a1.getEtiquetaDestino().equals("D")) ||
                        (a1.getEtiquetaOrigen().equals("D") && a1.getEtiquetaDestino().equals("B"))
        );
        boolean contieneCD = ((TGrafoNoDirigido) mst).getLasAristas().stream().anyMatch(a1 ->
                (a1.getEtiquetaOrigen().equals("C") && a1.getEtiquetaDestino().equals("D")) ||
                        (a1.getEtiquetaOrigen().equals("D") && a1.getEtiquetaDestino().equals("C"))
        );

        assertTrue(contieneAB, "No contiene arista A-B");
        assertTrue(contieneBD, "No contiene arista B-D");
        assertTrue(contieneCD, "No contiene arista C-D");
    }
    }
