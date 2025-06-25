package uy.edu.ucu.aed.parcial2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.ucu.aed.IArista;
import uy.edu.ucu.aed.TArista;
import uy.edu.ucu.aed.TCamino;
import uy.edu.ucu.aed.TCaminos;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TGrafoDeLaRedTest {

    private TGrafoDeLaRed grafo;
    private TVerticeDeLaRed vp1, vp2, vs1;

    @BeforeEach
    void setUp() {
        vp1 = new TVerticeDeLaRed("VP_1", "2");
        vp2 = new TVerticeDeLaRed("VP_2", "2");
        vs1 = new TVerticeDeLaRed("VS_1", "1");

        List<TVerticeDeLaRed> vertices = List.of(vp1, vp2, vs1);

        List<IArista> aristas = List.of(new TArista("VP_1", "VS_1", 1.0),
                new TArista("VS_1", "VP_2", 2.0),
                new TArista("VP_1", "VP_2", 5.0));

        grafo = new TGrafoDeLaRed(vertices, aristas);

    }

    @Test
    void testCaminoSimplePorSwitch() {
        // De P1 a P2 hay dos rutas “teóricas”:
        //  A) P1→P2 (costo 5), B) P1→S1→P2 (costo 3)
        TCaminos<TNodoDeLaRed> resultado = grafo.caminosDesdeHasta("VP_1","VP_2");

        // Debe haber solo la ruta B, porque A es P→P directo
        assertEquals(1, resultado.getCaminos().size(),
                "Solo un camino válido (pasando por switch)");

        TCamino<TNodoDeLaRed> camino = resultado.getCaminos().iterator().next();
        // Verificar secuencia de etiquetas
        assertEquals("Origen: VP_1 -> VS_1 -> VP_2", camino.imprimirEtiquetas().trim());
        // Costo total debe ser 1 + 2 = 3
        assertEquals(Double.valueOf(3.0), camino.getCostoTotal());

}
    @Test
    void testNoCamino() {
        // Si quitamos la arista de S1 a P2, no hay ruta válida
        grafo.eliminarArista("VS_1","VP_2");
        TCaminos<TNodoDeLaRed> resultado = grafo.caminosDesdeHasta("VP_1","VP_2");
        assertTrue(resultado.getCaminos().isEmpty(),
                "Debe ser vacío cuando no hay switch que conecte");
}
    @Test
    void testOrigenEqualsDestino() {
        // Camino trivial de un nodo a sí mismo
        TCaminos<TNodoDeLaRed> resultado = grafo.caminosDesdeHasta("P_1","P_1");
        // Dependiendo de la especificación, podrías esperar:
        //  a) 0 caminos (sin auto-lazos)
        //  b) 1 camino vacío con costo 0
        // Ajusta según tu decisión; aquí asumo que NO se devuelve nada:
        assertTrue(resultado.getCaminos().isEmpty(),
                "No devolvemos camino cuando origen=destino");
}
}