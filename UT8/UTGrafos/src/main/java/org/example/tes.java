package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.ucu.aed.IArista;
import uy.edu.ucu.aed.IVertice;
import uy.edu.ucu.aed.TArista;
import uy.edu.ucu.aed.TVertice;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TGrafoRutasTest {
    private TGrafoRutas grafo;

    @BeforeEach
    void setUp() {
        // Crear vértices de ciudad
        List<IVertice<String>> vertices = Arrays.asList(
                new TVertice<>("A"),
                new TVertice<>("B"),
                new TVertice<>("C"),
                new TVertice<>("D")
        );

        // Crear aristas con distancias
        List<IArista> aristas = Arrays.asList(
                new TArista("A", "B", 10),
                new TArista("B", "C", 20),
                new TArista("A", "C", 50),
                new TArista("C", "D", 10)
        );

        // Crear lista de camiones
        List<Camion> camiones = Arrays.asList(
                new Camion("T1", 10),  // velocidad 10 → tiempo = distancia/10
                new Camion("T2", 20)   // velocidad 20 → tiempo = distancia/20
        );

        grafo = new TGrafoRutas(vertices, aristas, camiones);
    }

    @Test
    void testOrigenDestinoNoExisten() {
        // Ciudad X no está en el grafo → lista vacía
        List<Recorrido> resultados = grafo.listaRutas("X", "Y");
        assertTrue(resultados.isEmpty(), "Debe devolver lista vacía si ciudades no existen");
    }

    @Test
    void testNoHayCamionQueLlegue() {
        // Eliminar arista C-D para desconectar D
        grafo = new TGrafoRutas(grafo.getVertices().values(),
                Arrays.asList(
                        new TArista("A", "B", 10),
                        new TArista("B", "C", 20),
                        new TArista("A", "C", 50)
                ), grafo.camiones
        );
        List<Recorrido> resultados = grafo.listaRutas("A", "D");
        assertTrue(resultados.isEmpty(), "Debe devolver lista vacía si no hay ruta a destino");
    }

    @Test
    void testRutaMinimaYTiempo() {
        List<Recorrido> resultados = grafo.listaRutas("A", "D");
        assertEquals(2, resultados.size(), "Deben llegar ambos camiones");

        // Para T1: distancia mínima A-B-C-D = 10+20+10 = 40, velocidad=10 → tiempo=4
        Recorrido r1 = resultados.stream()
                .filter(r -> r.getCamionId().equals("T1")).findFirst().orElse(null);
        assertNotNull(r1);
        assertEquals(4, r1.getTiempo());
        assertEquals(Arrays.asList("A","B","C","D"), r1.getCiudades());

        // Para T2: misma ruta, velocidad=20 → tiempo=2
        Recorrido r2 = resultados.stream()
                .filter(r -> r.getCamionId().equals("T2")).findFirst().orElse(null);
        assertNotNull(r2);
        assertEquals(2, r2.getTiempo());
        assertEquals(Arrays.asList("A","B","C","D"), r2.getCiudades());
    }
}
