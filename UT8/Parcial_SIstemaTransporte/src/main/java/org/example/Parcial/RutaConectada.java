package org.example.Parcial;

/**
 * Representa una ruta elegida en el MST (Prim/Kruskal).
 */
public class RutaConectada {
    private final String origen;
    private final String destino;
    private final int tiempo;

    public RutaConectada(String origen, String destino, int tiempo) {
        this.origen = origen;
        this.destino = destino;
        this.tiempo = tiempo;
    }

    public String getOrigen() {
        return origen;
    }
    public String getDestino() {
        return destino;
    }
    public int getTiempo() {
        return tiempo;
    }

    @Override
    public String toString() {
        return origen + " - " + destino + ": " + tiempo;
    }
}