package org.example;

import org.example.PD7.Tarea;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TGrafoDirigidoTest {

    @org.junit.jupiter.api.Test
    void ordenParcial() {
    }

    @org.junit.jupiter.api.Test
    void listarTareas() {
    }

    /**
     * Crea el grafo manualmente (sin leer archivo), simula precedencias simples
     */
    @Test
    public void testOrdenSimple() {
        // Tareas A -> B -> C.
        Tarea tA = new Tarea("A", 2);
        Tarea tB = new Tarea("B", 3);
        Tarea tC = new Tarea("C", 1);

        TVertice vA = new TVertice<>("A");
        vA.setDatos(tA);
        TVertice vB = new TVertice<>("B");
        vB.setDatos(tB);
        TVertice vC = new TVertice<>("C");
        vC.setDatos(tC);

        List<IVertice> vertices = List.of(vA, vB, vC);
        List<IArista> aristas = List.of(
                new TArista("A", "B", 1),
                new TArista("B", "C", 1)
        );

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);
        LinkedList<Tarea> orden = grafo.ordenParcial();

        List<String> esperado = List.of("A", "B", "C");
        List<String> resultado = orden.stream().map(Tarea::getCodigo).toList();

        assertEquals(esperado, resultado);
    }

    /**
     * Verifica que un archivo de tareas sin aristas no explote y devuelva el orden
     */
    @Test
    public void testSinPrecedencias() {
        Tarea t1 = new Tarea("A", 1);
        Tarea t2 = new Tarea("B", 1);

        TVertice v1 = new TVertice<>("A");
        v1.setDatos(t1);
        TVertice v2 = new TVertice<>("B");
        v2.setDatos(t2);

        List<IVertice> vertices = List.of(v1, v2);
        List<IArista> aristas = List.of(); // sin precedencias

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);
        LinkedList<Tarea> orden = grafo.ordenParcial();

        assertEquals(2, orden.size());
        assertTrue(orden.stream().map(Tarea::getCodigo).toList().containsAll(List.of("A", "B")));
    }

    /**
     * Verifica que el orden parcial de un archivo real sea correcto
     */
    @Test
    public void testDesdeArchivos() {
        TGrafoDirigido grafo = UtilGrafos.cargarGrafo(
                "/home/felipe/Downloads/parcial2_alumnos_2-2024 - 1er Semestre/UT7_PD7/src/main/java/org/example/PD7/tareas.txt",
                "/home/felipe/Downloads/parcial2_alumnos_2-2024 - 1er Semestre/UT7_PD7/src/main/java/org/example/PD7/precedesncias.txt",
                true, TGrafoDirigido.class);
        assertNotNull(grafo);

        LinkedList<Tarea> orden = grafo.ordenParcial();
        assertNotNull(orden);
        assertFalse(orden.isEmpty());

        // Validación básica: que "Inicio" sea primero y "Fin" último
        assertEquals("Inicio", orden.getFirst().getCodigo());
        assertEquals("Fin", orden.getLast().getCodigo());
    }


}