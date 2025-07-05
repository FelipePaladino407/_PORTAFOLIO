package uy.edu.ucu.aed.parcial2;

import org.junit.jupiter.api.Test;
import uy.edu.ucu.aed.IArista;
import uy.edu.ucu.aed.TArista;
import uy.edu.ucu.aed.TCamino;
import uy.edu.ucu.aed.TCaminos;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TGrafoDeLaRedTest {

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
        TCaminos<TEstacionDeLaRed> rutas = grafo.caminosDesdeHasta("Vertice_1",
                "Vertice_3");

        assertEquals(1, rutas.getCaminos().size());

        TCamino<TEstacionDeLaRed> ruta = rutas.getCaminos().iterator().next();
        assertEquals(2.5, ruta.getCostoTotal(), 0.0001);

        // Y la secuencia debe ser 1→2→3
        String etiquetas = ruta.imprimirEtiquetas();
        assertTrue(etiquetas.contains("Vertice_1"));
        assertTrue(etiquetas.contains("Vertice_2"));
        assertTrue(etiquetas.contains("Vertice_3"));
}

    @Test
    public void sinRutaDirecta(){
        TVerticeDeLaRed a = new TVerticeDeLaRed("Vertice_1", "2"); // BUS
        TVerticeDeLaRed b = new TVerticeDeLaRed("Vertice_2", "2"); // BUS


        List<TVerticeDeLaRed> verts = Arrays.asList(a, b);
        List<IArista> aris = Collections.singletonList(
                new TArista("Vertice_1", "Vertice_2", 3.0)
        );

        TGrafoDeLaRed grafo = new TGrafoDeLaRed(verts, aris);
        TCaminos<TEstacionDeLaRed> rutas = grafo.caminosDesdeHasta("Vertice_1",
                "Vertice_2");

        assertTrue(rutas.getCaminos().isEmpty());

    }

    @Test
    public void multiplesRutas(){

        TVerticeDeLaRed a = new TVerticeDeLaRed("Vertice_1", "2"); // BUS
        TVerticeDeLaRed x = new TVerticeDeLaRed("Vertice_2", "1"); // TREN
        TVerticeDeLaRed y = new TVerticeDeLaRed("Vertice_3", "1"); // TREN
        TVerticeDeLaRed b = new TVerticeDeLaRed("Vertice_4", "2"); // BUS

        List<TVerticeDeLaRed> verts = Arrays.asList(a, x, y, b);
        List<IArista> aris = Arrays.asList(
                new TArista("Vertice_1", "Vertice_2", 1.0),
                new TArista("Vertice_2", "Vertice_4", 2.0),
                new TArista("Vertice_1", "Vertice_3", 1.5),
                new TArista("Vertice_3", "Vertice_4", 1.5)
        );
        TGrafoDeLaRed grafo = new TGrafoDeLaRed(verts, aris);
        TCaminos<TEstacionDeLaRed> rutas = grafo.caminosDesdeHasta("Vertice_1",
                "Vertice_4");

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