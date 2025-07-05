package uy.edu.ucu.aed.SistemaTransporte;

import java.util.List;

/**
 * Clase de prueba para SistemaTransporte.
 * Ejecuta los ejemplos de la consigna:
 *  - Tiempo mínimo de viaje (Dijkstra)
 *  - Red de mantenimiento (MST)
 */
public class Main {
    public static void main(String[] args) {
        SistemaTransporte sistema = new SistemaTransporte();
        // --- Construir el grafo con las rutas de ejemplo ---
        sistema.agregarRuta("A", "B", 5);
        sistema.agregarRuta("A", "C", 10);
        sistema.agregarRuta("B", "C", 2);
        sistema.agregarRuta("B", "D", 4);
        sistema.agregarRuta("C", "D", 1);

        // --- Problema 1: Tiempo mínimo de viaje ---
        int tiempoMin = sistema.consultaTiempoMinimo("A", "D");
        System.out.println("Tiempo mínimo entre A y D: " + tiempoMin);
        // Resultado esperado: 8

        // --- Problema 2: Red de mantenimiento (MST) ---
        List<SistemaTransporte.RutaConectada> red = sistema.redDeMantenimiento();
        System.out.println("\nRed de mantenimiento:");
        for (SistemaTransporte.RutaConectada ruta : red) {
            System.out.println(ruta.origen + " - " + ruta.destino + ": " + ruta.tiempo + " minutos");
        }
        // Resultado esperado:
        //   A - B: 5 minutos
        //   B - C: 2 minutos
        //   C - D: 1 minutos
    }
}
