package org.example.Parcial;

import org.example.*;

public interface IGrafoRedTransporte {
    /**
     * Tiempo mínimo de viaje entre dos estaciones, o -1 si no hay camino.
     */
    int consultaTiempoMinimo(Comparable origen, Comparable destino);

    /**
     * Árbol de expansión mínima: rutas prioritarias para mantenimiento.
     */
    TAristas redDeMantenimiento();
}
