package org.example;

public class ArbolEsBinario {
    public boolean esABB(ElementoAB<Integer> raiz) {
        return esABBRecorrido(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean esABBRecorrido(ElementoAB<Integer> nodo, Integer min, Integer max) {
        if (nodo == null) {
            return true;
        }

        int valorActual = (Integer) nodo.getEtiqueta();

        if (valorActual <= min || valorActual >= max) {
            return false;
        }

        return esABBRecorrido((ElementoAB<Integer>) nodo.getHijoIzq(), min, valorActual) &&
                esABBRecorrido((ElementoAB<Integer>) nodo.getHijoDer(), valorActual, max);
    }
}