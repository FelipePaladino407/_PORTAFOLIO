package uy.edu.ucu.aed.SistemaTransporte;

import uy.edu.ucu.aed.*;

import java.util.*;

/**
 * Clase base para resolver los problemas de transporte.
 * Ejercicio 1: Dijkstra sin cola de prioridad.
 * Ejercicio 2: Prim sin cola de prioridad.
 */
public class SistemaTransporte {
    private TGrafoDirigido<Void> grafo;

    public SistemaTransporte() {
        // Arrancamos con grafo vacío
        this.grafo = new TGrafoDirigido<>(
                new LinkedList<IVertice<Void>>(),
                new LinkedList<IArista>());
    }
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
    public void agregarRuta(String origen, String destino, double tiempo) {
        // Si no existe el vértice, lo insertamos
        if (!grafo.existeVertice(origen)) {
            grafo.insertarVertice(new TVertice<Void>(origen));
        }
        if (!grafo.existeVertice(destino)) {
            grafo.insertarVertice(new TVertice<Void>(destino));
        }
        // Insertamos la arista en ambos sentidos
        grafo.insertarArista(new TArista(origen, destino, tiempo));
        grafo.insertarArista(new TArista(destino, origen, tiempo));
    }
    /**
     * Problema 1: Dijkstra sin cola de prioridad.
     * @return tiempo mínimo de viaje, o -1 si no hay camino.
     */
    public int consultaTiempoMinimo(String origen, String destino) {

        IVertice<Void> Vorigen = grafo.buscarVertice(origen);
        IVertice<Void> Vdestino = grafo.buscarVertice(destino);

        if (Vorigen == null || Vdestino == null){
            return -1;
        }
        Map<IVertice<Void>, Integer> distancias = new HashMap<>();
        Set<IVertice<Void>> visitados = new HashSet<>();

        for (IVertice<Void> v : grafo.getVertices().values()){
            distancias.put(v, Integer.MAX_VALUE);
        }
        distancias.put(Vorigen, 0);  // Vertice inicial.

        while (visitados.size() < grafo.getVertices().size()){
            // Elegir vertice no viistado con menor distancia:
            IVertice<Void> actual = null;
            double mejor = Integer.MAX_VALUE;
            for (Map.Entry<IVertice<Void>, Integer> e : distancias.entrySet()){
                if (!visitados.contains(e.getKey()) && e.getValue() < mejor){
                    mejor = e.getValue();
                    actual = e.getKey();
                }
            }
            if (mejor == Integer.MAX_VALUE){
                break; // No hay mas vertices alcanzables.
            }

            if (actual.equals(Vdestino)){
                return distancias.get(Vdestino);
            }
            visitados.add(actual);

            for (IAdyacencia<Void> ady : actual.getAdyacentes()){
                IVertice<Void> v = ady.getDestino();
                if (visitados.contains(v)){
                    continue;
                }
                int peso = (int) Math.round(ady.getCosto());
                int nueva = distancias.get(actual) + peso;
                if (nueva < distancias.get(v)){
                    distancias.put(v, nueva);
                }
            }
        }
        return -1;
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
