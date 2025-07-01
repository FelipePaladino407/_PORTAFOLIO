package org.example.Parcial;

/**
 * Representa una ruta en el grafo de transporte.
 */
public class Ruta {
    private final String destino;
    private final int tiempo;

    public Ruta(String destino, int tiempo) {
        this.destino = destino;
        this.tiempo = tiempo;
    }

    public String getDestino() {
        return destino;
    }

    public int getTiempo() {
        return tiempo;
    }
}