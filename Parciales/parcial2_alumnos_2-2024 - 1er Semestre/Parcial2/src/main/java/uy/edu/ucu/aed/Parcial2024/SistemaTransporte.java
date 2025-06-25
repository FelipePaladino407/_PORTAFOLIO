package uy.edu.ucu.aed.Parcial2024;
import uy.edu.ucu.aed.*;
import java.util.*;

public class SistemaTransporte {
    private Map<String, List<Ruta>> grafo = new HashMap<>();

    private static class Ruta {
        String destino;
        int tiempo;

        Ruta(String destino, int tiempo) {
            this.destino = destino;
            this.tiempo = tiempo;
        }
    }

    private static class Estacion{
        String nombre;
        int tiempo;

        Estacion(String nombre, int tiempo){
            this.nombre = nombre;
            this.tiempo = tiempo;
        }
    }

    public static class RutaConectada{
        String origen;
        String destino;
        int tiempo;
    }

    /**
     * PROBLEMA 1 - CONSULTA DE TIEMPO MINIMO.
     * @param origen
     * @param destino
     * @return
     */
    public int consultaTiempoMinimo(String origen, String destino){

        if (!grafo.containsKey(origen) || !grafo.containsKey(destino)) {
            System.out.println("Alguna de las dos estaciones no existe");
            return -1; // No existe, ya sea el origen o el destino.
        }
        // Mapa de tiempos minimos desde el origen:
        Map<String, Integer> distancias = new HashMap<>();
        for (String estacion : grafo.keySet()) {
            distancias.put(estacion, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);

        // Cola de prioridad con las esatciones que vamos a visitar:
        PriorityQueue<Estacion> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.tiempo));
        pq.offer(new Estacion(origen, 0));

        Set<String> visitados = new HashSet<>();

        while (!pq.isEmpty()) {
            Estacion actual = pq.poll();

            if (visitados.contains(actual.nombre)) {
                continue;
            }
            visitados.add(actual.nombre);

            if (actual.nombre.equals(destino)) {
                return actual.tiempo;
            }

            for (Ruta ruta : grafo.getOrDefault(actual.nombre, Collections.emptyList())) {
                if (!visitados.contains(ruta.destino)) {
                    int nuevoTime = actual.tiempo + ruta.tiempo;
                    if (nuevoTime < distancias.get(ruta.destino)) {
                        distancias.put(ruta.destino, nuevoTime);
                        pq.offer(new Estacion(ruta.destino, nuevoTime));
                    }
                }
            }
        }
        return -1; // No se encontro un camino.
    }

    public void agregarRuta(String origen, String destino, int tiempo){
        grafo.putIfAbsent(origen, new ArrayList<>());
        grafo.putIfAbsent(destino, new ArrayList<>());
        grafo.get(origen).add(new Ruta(destino, tiempo));
    }

    public List<RutaConectada> redDeMantenimiento() {
        List<IVertice<Void>> vertices = new LinkedList<>();
        List<IArista> aristas = new LinkedList<>();
        Set<String> creados = new HashSet<>();

        for (String nombre : grafo.keySet()) {
            if (!creados.contains(nombre)) {
                TVertice<Void> v = new TVertice(nombre);
                vertices.add(v);
                creados.add(nombre);
            }
        }
        Set<String> aristasCreadas = new HashSet<>();
        for (Map.Entry<String, List<Ruta>> entrada : grafo.entrySet()) {
            String origen = entrada.getKey();
            for (Ruta ruta : entrada.getValue()) {
                String claveArista = origen + "==" + ruta.destino;
                String claveInversa = ruta.destino + "==" + origen;

                // Evitamos duplicar rutas
                if (!aristasCreadas.contains(claveArista) && !aristasCreadas.contains(claveInversa)) {
                    aristas.add(new TArista(origen, ruta.destino, ruta.tiempo));
                    aristasCreadas.add(claveArista);
                }
            }
        }
        TGrafoNoDirigido<Void> grafoND = new TGrafoNoDirigido<>(vertices, aristas);
        IGrafoNoDirigido<Void> mst = grafoND.Prim();

        // Convertimos resultado:
        List<RutaConectada> resultado = new LinkedList<>();
        for (IArista arista : mst.getLasAristas()) {
            RutaConectada rc = new RutaConectada();
            rc.origen = (String) arista.getEtiquetaOrigen();
            rc.destino = (String) arista.getEtiquetaDestino();
            rc.tiempo = (int) arista.getCosto();
            resultado.add(rc);
        }
        return resultado;
    }





}
