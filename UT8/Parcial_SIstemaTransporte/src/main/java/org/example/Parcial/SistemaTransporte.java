package org.example.Parcial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sistema de transporte público como grafo ponderado.
 */
public class SistemaTransporte {
    private final Map<String, List<Ruta>> grafo = new HashMap<>();

    /** Añade una ruta dirigida de origen→destino con el tiempo dado. */
    public void agregarRuta(String origen, String destino, int tiempo) {
        grafo.computeIfAbsent(origen, k -> new ArrayList<>())
                .add(new Ruta(destino, tiempo));
        // Si queremos grafo no dirigido, también invertimos:
        grafo.computeIfAbsent(destino, k -> new ArrayList<>())
                .add(new Ruta(origen, tiempo));
    }
}
