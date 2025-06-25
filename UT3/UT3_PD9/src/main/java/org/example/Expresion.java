// Expresion.java
package org.example;

public class Expresion {

    /**
     * Recorre la lista de entrada y usa internamente una "pila" montada
     * sobre Lista<Character> para empujar '{' y desapilar al ver '}'.
     * @param listaDeEntrada Lista de caracteres con la secuencia a validar
     * @return true si todos los '{' tienen su correspondiente '}', false otherwise
     */
    public static boolean controlCorchetes(Lista<Character> listaDeEntrada) {
        // creamos nuestra pila sobre Lista<Character>
        Lista<Character> pila = new Lista<>();

        Nodo<Character> aux = listaDeEntrada.getPrimero();
        while (aux != null) {
            char c = aux.getDato();  // dato y etiqueta son ambos Character
            if (c == '{') {
                // push: creamos nodo y lo enlazamos al frente
                Nodo<Character> nodo = new Nodo<>(c, c);
                nodo.setSiguiente(pila.getPrimero());
                pila.setPrimero(nodo);
            } else if (c == '}') {
                // pop: si no hay nada para cerrar, secuencia mal formada
                if (pila.esVacia()) {
                    return false;
                }
                Nodo<Character> tope = pila.getPrimero();
                pila.setPrimero(tope.getSiguiente());
                // opcional: tope.setSiguiente(null);
            }
            // otros caracteres se ignoran
            aux = aux.getSiguiente();
        }
        // si al final la pila quedó vacía, todos los '{' fueron cerrados
        return pila.esVacia();
    }
}
