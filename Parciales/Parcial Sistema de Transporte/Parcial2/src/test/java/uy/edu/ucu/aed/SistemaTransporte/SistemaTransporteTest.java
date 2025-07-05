package uy.edu.ucu.aed.SistemaTransporte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SistemaTransporteTest {

    private SistemaTransporte sistema;

    @BeforeEach
    void setUp() {
        sistema = new SistemaTransporte();
        // Grafo de ejemplo:
        // A—5—B
        // A—10—C
        // B—2—C
        // B—4—D
        // C—1—D
        sistema.agregarRuta("A", "B", 5);
        sistema.agregarRuta("A", "C", 10);
        sistema.agregarRuta("B", "C", 2);
        sistema.agregarRuta("B", "D", 4);
        sistema.agregarRuta("C", "D", 1);
    }

    @Test
    void testCaminoDirecto() {
        // A→B directo
        assertEquals(5, sistema.consultaTiempoMinimo("A", "B"));
    }

    @Test
    void testCaminoIndirecto() {
        // A→D: A→B→C→D = 5 + 2 + 1 = 8 es el mínimo
        assertEquals(8, sistema.consultaTiempoMinimo("A", "D"));
    }

    @Test
    void testMejorEntreVariasRutas() {
        // B→D: directo vale 4, pero B→C→D vale 2 + 1 = 3
        assertEquals(3, sistema.consultaTiempoMinimo("B", "D"));
    }

    @Test
    void testOrigenDestinoIgual() {
        // El coste de ir de un nodo a sí mismo debe ser 0
        assertEquals(0, sistema.consultaTiempoMinimo("A", "A"));
    }

    @Test
    void testVerticeInexistente() {
        // Cualquier consulta con un nodo ausente devuelve -1
        assertEquals(-1, sistema.consultaTiempoMinimo("X", "A"));
        assertEquals(-1, sistema.consultaTiempoMinimo("A", "X"));
    }

    @Test
    void testGrafoNoConexo() {
        // Grafo separado: A—5—B   C—7—D
        SistemaTransporte s2 = new SistemaTransporte();
        s2.agregarRuta("A", "B", 5);
        s2.agregarRuta("C", "D", 7);
        // No hay camino de A a D
        assertEquals(-1, s2.consultaTiempoMinimo("A", "D"));
    }
}