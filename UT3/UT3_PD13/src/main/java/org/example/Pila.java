// Pila.java
package org.example;

/**
 * Implementación de IPila<T> basada en composición sobre Lista<T>.
 * Sólo expone operaciones de pila (LIFO).
 */
public class Pila<T> implements IPila<T> {
    private final Lista<T> lista = new Lista<>();

    @Override
    public void apilar(Nodo<T> nodo) {
        // insertar al frente: O(1)
        nodo.setSiguiente(lista.getPrimero());
        lista.setPrimero(nodo);
    }

    @Override
    public Nodo<T> desapilar() {
        if (lista.esVacia()) return null;
        Nodo<T> tope = lista.getPrimero();
        lista.setPrimero(tope.getSiguiente());
        tope.setSiguiente(null);
        return tope;
    }

    @Override
    public Nodo<T> obtenerTope() {
        return lista.getPrimero();
    }

    @Override
    public boolean esVacia() {
        return lista.esVacia();
    }

    @Override
    public int cantElementos() {
        return lista.cantElementos();
    }
}
