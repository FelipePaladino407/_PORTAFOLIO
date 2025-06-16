package org.example;

public class TAdyacencia implements IAdyacencia {
   

    private Comparable etiqueta;
    private double costo;
    private IVertice destino;
    
    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }
 
    @Override
    public double getCosto() {
        return costo;
    }

    @Override
    public IVertice getDestino() {
        return destino;
    }

    public TAdyacencia(double costo, IVertice destino) {
        this.etiqueta = destino.getEtiqueta();
        this.costo = costo;
        this.destino = destino;
    }
}
