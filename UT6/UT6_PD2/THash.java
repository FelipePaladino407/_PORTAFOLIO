package org.example.UT6.PD2;

public class THash<K, V> implements IHash<K, V> {
    private static class Nodo<K, V> {
        K clave;
        V valor;
        Nodo<K, V> siguiente;

        public Nodo(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo<K, V>[] tabla;
    private int capacidad;
    private int elementos = 0;

    @SuppressWarnings("unchecked")
    public THash(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = (Nodo<K, V>[]) new Nodo[capacidad];
    }

  protected int funcionHashing(K unaClave) {
    return Math.abs(unaClave.hashCode()) % tabla.length;
    }

    @Override
    public boolean insertar(K clave, V valor) {
        int indice = funcionHashing(clave);
        Nodo<K, V> nodoActual = tabla[indice];
        while (nodoActual != null) {
            if (nodoActual.clave.equals(clave)) {
                return false;
            }
            nodoActual = nodoActual.siguiente;
        }
        Nodo<K, V> nuevoNodo = new Nodo<>(clave, valor);
        nuevoNodo.siguiente = tabla[indice];
        tabla[indice] = nuevoNodo;
        elementos++;
        return true;
    }

    @Override
    public V buscar(K clave) {
        int indice = funcionHashing(clave);
        Nodo<K, V> actual = tabla[indice];
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public int buscarComparaciones(K clave) {
        int indice = funcionHashing(clave);
        Nodo<K, V> actual = tabla[indice];
        int comp = 0;
        while (actual != null) {
            comp++;
            if (actual.clave.equals(clave)) {
                return comp;
            }
            actual = actual.siguiente;
        }
        return comp;
    }

    public int insertarComparaciones(K clave, V valor) {
        int indice = funcionHashing(clave);
        Nodo<K, V> nodoActual = tabla[indice];
        int comp = 0;
        while (nodoActual != null) {
            comp++;
            if (nodoActual.clave.equals(clave)) {
                return comp;
            }
            nodoActual = nodoActual.siguiente;
        }
        Nodo<K, V> nuevoNodo = new Nodo<>(clave, valor);
        nuevoNodo.siguiente = tabla[indice];
        tabla[indice] = nuevoNodo;
        elementos++;
        return comp;
    }

    public double getFactorCarga() {
        return (double) elementos / capacidad;
    }
}