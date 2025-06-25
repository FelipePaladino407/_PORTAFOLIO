// Cola.java
package org.example;

/**
 * Implementación de ICola<T> basada en composición sobre Lista<T>.
 * Sólo expone operaciones de cola (FIFO).
 */
public class Cola<T> implements ICola<T> {
    private final Lista<T> lista = new Lista<>();

    @Override
    public void encolar(Nodo<T> nodo) {
        // insertar al final: utiliza el insertar() de Lista
        lista.insertar(nodo);
    }

    @Override
    public Nodo<T> desencolar() {
        if (lista.esVacia()) return null;
        Nodo<T> frente = lista.getPrimero();
        lista.setPrimero(frente.getSiguiente());
        frente.setSiguiente(null);
        return frente;
    }

    @Override
    public Nodo<T> obtenerFrente() {
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
