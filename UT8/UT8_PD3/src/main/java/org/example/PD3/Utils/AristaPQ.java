package org.example.PD3.Utils;


/**
 * CLASE AUXILIAR PARA ALGORITMO DE PRIM.
 */
public class AristaPQ implements Comparable<AristaPQ> {
    Comparable from, to;
    double costo;

    public AristaPQ(Comparable from, Comparable to, double costo) {
        this.from = from; this.to = to; this.costo = costo;
    }

    public Comparable getFrom() {
        return from;
    }
    public Comparable getTo() {
        return to;
    }
    public double getCosto() {
        return costo;
    }

    @Override
    public int compareTo(AristaPQ o) {
        return Double.compare(this.costo, o.costo);
    }
}

