// IPila.java
package org.example;

/**
 * TDA Pila: LIFO
 */
public interface IPila<T> {
    /** Inserta un nodo en el tope de la pila */
    void apilar(Nodo<T> nodo);

    /** Saca y devuelve el nodo del tope. Null si está vacía */
    Nodo<T> desapilar();

    /** Devuelve (sin sacar) el nodo del tope. Null si está vacía */
    Nodo<T> obtenerTope();

    /** True si no tiene elementos */
    boolean esVacia();

    /** Cantidad de elementos en la pila */
    int cantElementos();
}
