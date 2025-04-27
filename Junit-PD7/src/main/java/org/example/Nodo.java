package org.example;

public class Nodo<T> {

    private final Comparable etiqueta;
    private T dato;
    private Nodo<T> siguiente = null;

    public Nodo(Comparable etiqueta, T dato ) {
        this.etiqueta = etiqueta;
        this.dato = dato;
    }


    public T getDato() {
        return this.dato;
    }

    public Nodo<T> getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(Nodo<T> nodo) {
        this.siguiente = nodo;
    }

    public void imprimir() {
        System.out.println( this.dato);
    }

    public void imprimirEtiqueta() {
        System.out.println( this.etiqueta);
    }

    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    public int compareTo(Comparable etiqueta) {
        return this.etiqueta.compareTo(etiqueta);
    }

}
