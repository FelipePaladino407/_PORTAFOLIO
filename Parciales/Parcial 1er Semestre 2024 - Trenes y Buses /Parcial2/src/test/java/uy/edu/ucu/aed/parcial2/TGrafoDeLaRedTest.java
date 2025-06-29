package uy.edu.ucu.aed.parcial2;

import org.junit.jupiter.api.Test;
import uy.edu.ucu.aed.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TGrafoDeLaRedTest {

    /**
     * Caso 1: Una única ruta válida A(BUS)→X(TREN)→B(BUS)
     */
    @Test
    public void testRutaConTrenIntermedio() {
        TVerticeDeLaRed a = new TVerticeDeLaRed("Vertice_1", "2"); // BUS
        TVerticeDeLaRed x = new TVerticeDeLaRed("Vertice_2", "1"); // TREN
        TVerticeDeLaRed b = new TVerticeDeLaRed("Vertice_3", "2"); // BUS

        List<TVerticeDeLaRed> verts = Arrays.asList(a, x, b);
        List<IArista> aris = Arrays.asList(
                new TArista("Vertice_1", "Vertice_2", 1.0),
                new TArista("Vertice_2", "Vertice_3", 1.5)
        );

        TGrafoDeLaRed grafo = new TGrafoDeLaRed(verts, aris);
        TCaminos<TEstacionDeLaRed> rutas =
                grafo.caminosDesdeHasta("Vertice_1", "Vertice_3");

        // Debe encontrarse exactamente 1 ruta
        assertEquals(1, rutas.getCaminos().size());

        // Y su costo total es 1.0 + 1.5 = 2.5
        TCamino<TEstacionDeLaRed> ruta = rutas.getCaminos().iterator().next();
        assertEquals(2.5, ruta.getCostoTotal(), 0.0001);

        // Y la secuencia debe ser 1→2→3
        String etiquetas = ruta.imprimirEtiquetas();
        assertTrue(etiquetas.contains("Vertice_1"));
        assertTrue(etiquetas.contains("Vertice_2"));
        assertTrue(etiquetas.contains("Vertice_3"));
    }

    /**
     * Caso 2: No hay ruta válida A(BUS)→B(BUS) directa (BUS→BUS prohibido)
     */
    @Test
    public void testSinRutaDirectaBusBus() {
        TVerticeDeLaRed a = new TVerticeDeLaRed("Vertice_1", "2");
        TVerticeDeLaRed b = new TVerticeDeLaRed("Vertice_2", "2");

        List<TVerticeDeLaRed> verts = Arrays.asList(a, b);
        List<IArista> aris = Collections.singletonList(
                new TArista("Vertice_1", "Vertice_2", 3.0)
        );

        TGrafoDeLaRed grafo = new TGrafoDeLaRed(verts, aris);
        TCaminos<TEstacionDeLaRed> rutas =
                grafo.caminosDesdeHasta("Vertice_1", "Vertice_2");

        // No debe haber rutas válidas
        assertTrue(rutas.getCaminos().isEmpty());
    }

    /**
     * Caso 3: Dos rutas alternativas:
     *   A→X1→B y A→X2→B
     */
    @Test
    public void testMultipleRutasConTrenesDistintos() {
        TVerticeDeLaRed a  = new TVerticeDeLaRed("Vertice_1", "2"); // BUS
        TVerticeDeLaRed x1 = new TVerticeDeLaRed("Vertice_2", "1"); // TREN
        TVerticeDeLaRed x2 = new TVerticeDeLaRed("Vertice_3", "1"); // TREN
        TVerticeDeLaRed b  = new TVerticeDeLaRed("Vertice_4", "2"); // BUS

        List<TVerticeDeLaRed> verts = Arrays.asList(a, x1, x2, b);
        List<IArista> aris = Arrays.asList(
                new TArista("Vertice_1", "Vertice_2", 1.0),
                new TArista("Vertice_2", "Vertice_4", 2.0),
                new TArista("Vertice_1", "Vertice_3", 1.5),
                new TArista("Vertice_3", "Vertice_4", 1.5)
        );

        TGrafoDeLaRed grafo = new TGrafoDeLaRed(verts, aris);
        TCaminos<TEstacionDeLaRed> rutas =
                grafo.caminosDesdeHasta("Vertice_1", "Vertice_4");

        // Deben ser dos rutas
        assertEquals(2, rutas.getCaminos().size());

        // Verifico que ambas rutas estén presentes con sus costos
        Set<Double> costos = new HashSet<>();
        for (TCamino<TEstacionDeLaRed> camino : rutas.getCaminos()) {
            costos.add(camino.getCostoTotal());
        }
        assertTrue(costos.contains(3.0));  // 1.0 + 2.0
        assertTrue(costos.contains(3.0));  // 1.5 + 1.5
    }

}