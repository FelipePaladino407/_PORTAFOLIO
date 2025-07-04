package uy.edu.ucu.aed.SistemaTransporte;

import java.util.*;

/**
 * Clase base para resolver los problemas de transporte.
 * Ejercicio 1: Dijkstra sin cola de prioridad.
 * Ejercicio 2: Prim sin cola de prioridad.
 */
public class SistemaTransporte {
    private Map<String, List<Ruta>> grafo = new HashMap<>();

    /**
     * Representa una ruta desde la estación actual hasta 'destino'.
     */
    private static class Ruta {
        String destino;
        int tiempo;
        // Aquí podrían agregarse flags de restricción, p.ej. boolean habilitada;

        Ruta(String destino, int tiempo) {
            this.destino = destino;
            this.tiempo = tiempo;
        }
    }

    /**
     * Clase auxiliar para representaciones internas (no obligatoria).
     */
    private static class Estacion {
        String nombre;
        Estacion(String nombre) { this.nombre = nombre; }
    }

    /**
     * Ruta resultante en el MST (Problema 2).
     */
    public static class RutaConectada {
        public String origen;
        public String destino;
        public int tiempo;

        public RutaConectada(String origen, String destino, int tiempo) {
            this.origen = origen;
            this.destino = destino;
            this.tiempo = tiempo;
        }
    }

    /**
     * Agrega una ruta bidireccional al grafo.
     */
    public void agregarRuta(String origen, String destino, int tiempo) {
        grafo.computeIfAbsent(origen, k -> new LinkedList<>()).add(new Ruta(destino, tiempo));
        grafo.computeIfAbsent(destino, k -> new LinkedList<>()).add(new Ruta(origen, tiempo));
    }

    /**
     * Problema 1: Dijkstra sin cola de prioridad.
     * @return tiempo mínimo de viaje, o -1 si no hay camino.
     */
    public int consultaTiempoMinimo(String origen, String destino) {

        for ()
    }

    /**
     * Problema 2: Prim sin cola de prioridad.
     * @return lista de rutas seleccionadas en el MST.
     */
    public List<RutaConectada> redDeMantenimiento() {
        // TODO: implementar Prim "naïve" sin PriorityQueue
        return Collections.emptyList();
    }
}
