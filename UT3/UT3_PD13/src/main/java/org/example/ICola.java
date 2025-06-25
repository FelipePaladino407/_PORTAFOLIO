// ICola.java
package org.example;

/**
 * TDA Cola: FIFO
 */
public interface ICola<T> {
    /** Agrega un nodo al final de la cola */
    void encolar(Nodo<T> nodo);

    /** Saca y devuelve el nodo del frente. Null si está vacía */
    Nodo<T> desencolar();

    /** Devuelve (sin sacar) el nodo del frente. Null si está vacía */
    Nodo<T> obtenerFrente();

    /** True si no tiene elementos */
    boolean esVacia();

    /** Cantidad de elementos en la cola */
    int cantElementos();
}
