package org.example;

public class Conjunto<T> implements IConjunto<T> {

    private Nodo<T> primero = null;
    private int cantElementos = 0;

    public Conjunto() {
        primero = null;
        cantElementos = 0;
    }


    // Metodos nuevos.

    @Override
    public IConjunto<T> union(IConjunto<T> OtroConjunto) {
        IConjunto<T> Union = new Conjunto();

        Nodo<T> actual = primero;
        while (actual != null) {
            // Le anado el nodo con su valor y su clave.
            Union.insertar(actual.getDato(), actual.getEtiqueta());
            actual = actual.getSiguiente();
        }
        // Ahora está lo difficile, hay que acceder a los elementos del otro conjunto.
        for (int pp = 0; pp < OtroConjunto.cantElementos(); pp++){
            Nodo<T> nodoDelOtroConjunto = ((Conjunto<T>) OtroConjunto).getNodo(pp);
            if (Union.buscar(nodoDelOtroConjunto.getEtiqueta()) == null) {
                Union.insertar(nodoDelOtroConjunto.getDato(), nodoDelOtroConjunto.getEtiqueta());

            }

        }
        return Union;


    }

    @Override
    public IConjunto<T> intersection(IConjunto<T> OtroConjunto) {
        IConjunto<T> Intersection = new Conjunto();

        Nodo<T> actual = primero;
        while (actual != null) {
            if (OtroConjunto.buscar(actual.getEtiqueta()) != null) {
                Intersection.insertar(actual.getDato(), actual.getEtiqueta());
            }
            actual = actual.getSiguiente();
        }
        return Intersection;
    }



    @Override
    public void insertar(T dato, Comparable clave) {
        if (buscar(clave) == null) {                                // Corrobora que no exista ya un nodo con
            Nodo<T> nuevoNodo = new Nodo<>(clave, dato);            // la misma clave.
            if (primero == null) {
                primero = nuevoNodo;
            } else {
                Nodo<T> actual = primero;
                while (actual.getSiguiente() != null) {
                    actual = actual.getSiguiente();
                }
                actual.setSiguiente(nuevoNodo);
            }
            cantElementos++;
        }
    }

    @Override
    public T buscar(Comparable clave) {
        Nodo<T> actual = primero;
        while(actual != null){
            if(actual.getEtiqueta().equals(clave)){
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null; }

    @Override
    public boolean eliminar(Comparable clave) {
        Nodo<T> actual = primero;
        while(actual.getSiguiente() != null){
            if(actual.getSiguiente().getEtiqueta().equals(clave)){
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                cantElementos--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;}

    @Override
    public String imprimir() {
        Nodo<T> actual = primero;
        String cadena = "";
        while(actual != null){
            cadena += actual.getDato() + " ";
            actual = actual.getSiguiente();
        }
        return cadena;
    }

    @Override
    public String imprimir(String separador) {
        Nodo<T> actual = primero;
        String cadena = "";
        while(actual != null){
            cadena += actual.getDato() + separador;
            actual = actual.getSiguiente();
        }
        return cadena;
    }

    @Override
    public int cantElementos() {
        return this.cantElementos;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }

    // Metodo CLAVE para seguir viviendo y poder hacer funcionar el metodo de union.
    // Lo que hace es acceder al nodo por índice, lo que me permite buscar el nodo en el "otroConjunto".
    public Nodo<T> getNodo(int index) {
        Nodo<T> actual = primero;
        int pp = 0;
        while (actual != null) {
            if (pp == index){
                return actual;
            }
            actual = actual.getSiguiente();
            pp++;
        }
        return null;
    }
}
