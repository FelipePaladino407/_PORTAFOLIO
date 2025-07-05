package uy.edu.ucu.aed;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TGrafoRedDatosTest {

    private TGrafoRedDatos crearGrafoEjemplo() {
        TVertice a = new TVertice("A");
        TVertice b = new TVertice("B");
        TVertice c = new TVertice("C");
        TVertice d = new TVertice("D");

        List<TVertice> vertices = Arrays.asList(a, b, c, d);
        List<TArista> aristas = Arrays.asList(
                new TArista("A", "B", 1),
                new TArista("B", "C", 1)
                // No hay conexión desde C a D
        );

        return new TGrafoRedDatos(vertices, aristas);
    }

    @Test
    public void testConectadosTrue() {
        TGrafoRedDatos grafo = crearGrafoEjemplo();
        IVertice a = grafo.getVertices().get("A");
        IVertice c = grafo.getVertices().get("C");

        assertTrue(grafo.conectados(a, c));
    }

    @Test
    public void testConectadosFalse() {
        TGrafoRedDatos grafo = crearGrafoEjemplo();
        IVertice a = grafo.getVertices().get("A");
        IVertice d = grafo.getVertices().get("D");

        assertFalse(grafo.conectados(a, d));
    }

    @Test
    public void testConectadosMismoNodo() {
        TGrafoRedDatos grafo = crearGrafoEjemplo();
        IVertice b = grafo.getVertices().get("B");

        assertTrue(grafo.conectados(b, b));
    }

    @Test
    public void testConectadosConNull() {
        TGrafoRedDatos grafo = crearGrafoEjemplo();

        assertFalse(grafo.conectados(null, null));
    }
}