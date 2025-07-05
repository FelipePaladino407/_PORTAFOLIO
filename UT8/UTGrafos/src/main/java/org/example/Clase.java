package org.example;

import java.util.*;
import uy.edu.ucu.aed.*;

/**
 * Representa un recorrido realizado por un camión entre dos ciudades.
 */
public class Recorrido {
    private final String camionId;
    private final int tiempo;
    private final List<String> ciudades;

    public Recorrido(String camionId, int tiempo, List<String> ciudades) {
        this.camionId = camionId;
        this.tiempo = tiempo;
        this.ciudades = new ArrayList<>(ciudades);
    }

    public String getCamionId() {
        return camionId;
    }

    public int getTiempo() {
        return tiempo;
    }

    public List<String> getCiudades() {
        return Collections.unmodifiableList(ciudades);
    }
}

/**
 * Representa un camión con identificador y velocidad (unidades de distancia por unidad de tiempo).
 */
public class Camion {
    private final String id;
    private final double velocidad;

    public Camion(String id, double velocidad) {
        this.id = id;
        this.velocidad = velocidad;
    }

    public String getId() {
        return id;
    }

    public double getVelocidad() {
        return velocidad;
    }
}

/**
 * Extiende el grafo no dirigido de ciudades y permite calcular rutas óptimas para cada camión.
 * Las aristas almacenan distancias y el tiempo se calcula como distancia/velocidad.
 */
public class TGrafoRutas extends TGrafoNoDirigido<String> {
    private final List<Camion> camiones;

    public TGrafoRutas(Collection<IVertice<String>> vertices,
                       Collection<IArista> aristas,
                       List<Camion> camiones) {
        super(vertices, aristas);
        this.camiones = new ArrayList<>(camiones);
    }

    /**
     * Para cada camión, aplica Dijkstra para hallar la distancia mínima entre origen y destino,
     * calcula el tiempo y reconstruye la lista de ciudades.
     * Si un camión no puede llegar, se omite.
     */
    public List<Recorrido> listaRutas(String ciudadOrigen, String ciudadDestino) {
        List<Recorrido> resultados = new ArrayList<>();
        if (!existeVertice(ciudadOrigen) || !existeVertice(ciudadDestino)) {
            return resultados;
        }
        // Para cada camión, ejecutamos Dijkstra
        for (Camion camion : camiones) {
            // Inicialización
            Map<Comparable, Double> dist = new HashMap<>();
            Map<Comparable, Comparable> prev = new HashMap<>();
            for (Comparable etiqueta : getVertices().keySet()) {
                dist.put(etiqueta, Double.POSITIVE_INFINITY);
            }
            dist.put(ciudadOrigen, 0.0);
            // Cola de prioridad por distancia acumulada
            PriorityQueue<Comparable> pq = new PriorityQueue<>(
                    Comparator.comparing(dist::get)
            );
            pq.add(ciudadOrigen);

            // Dijkstra
            while (!pq.isEmpty()) {
                Comparable u = pq.poll();
                if (u.equals(ciudadDestino)) break;
                double du = dist.get(u);
                if (Double.isInfinite(du)) break;
                IVertice<String> vertU = getVertices().get(u);
                for (IAdyacencia<String> ady : vertU.getAdyacentes()) {
                    Comparable v = ady.getDestino().getEtiqueta();
                    double alt = du + ady.getCosto();
                    if (alt < dist.get(v)) {
                        dist.put(v, alt);
                        prev.put(v, u);
                        pq.remove(v);
                        pq.add(v);
                    }
                }
            }

            double distanciaTotal = dist.get(ciudadDestino);
            if (Double.isInfinite(distanciaTotal)) {
                // Este camión no puede llegar
                continue;
            }
            // Tiempo redondeado al entero más cercano hacia arriba
            int tiempo = (int) Math.ceil(distanciaTotal / camion.getVelocidad());
            // Reconstruir el camino
            LinkedList<String> path = new LinkedList<>();
            Comparable paso = ciudadDestino;
            while (paso != null) {
                path.addFirst(paso.toString());
                paso = prev.get(paso);
            }
            resultados.add(new Recorrido(camion.getId(), tiempo, path));
        }

        return resultados;
    }
}
