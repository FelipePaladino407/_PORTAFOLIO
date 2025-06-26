package org.example.UT6.PD1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class TNodoTrie implements INodoTrie{
    private HashMap<Character, TNodoTrie> hijos;
    private boolean esPalabra;

    public TNodoTrie() {
        hijos = new HashMap<Character, TNodoTrie>();
        esPalabra = false;
    }

    @Override
    public int buscar(String s) {
        TNodoTrie nodo = this;
        int cantidadMov = 0;

        for (int c = 0; c < s.length(); c++) {
            Character currentChar = s.charAt(c);
            if (!nodo.hijos.containsKey(currentChar)) {
                return -1;
            }
            cantidadMov++;
            nodo = nodo.hijos.get(currentChar);
        }
        if (nodo.esPalabra){
            return cantidadMov;
        }
        return -1;
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);

            }
            for (Character c : nodo.hijos.keySet()){
                imprimir(s+c, nodo.hijos.get(c));
            }

        }
    }

    private void agregarPalabra(LinkedList<String> lista, String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                lista.add(s);
//                System.out.println(s);

            }
            for (Character c : nodo.hijos.keySet()){
                agregarPalabra(lista,s+c, nodo.hijos.get(c));
            }

        }
    }

    @Override
    public void insertar(String unaPalabra) {
       TNodoTrie nodo = this;
       for (int c = 0; c < unaPalabra.length(); c++){
           Character currentChar = unaPalabra.charAt(c);
           if (!nodo.hijos.containsKey(currentChar)){
               TNodoTrie nuevo = new TNodoTrie();
               nodo.hijos.put(currentChar, nuevo);
           }
           nodo = nodo.hijos.get(currentChar);
       }
       nodo.esPalabra = true;
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
       TNodoTrie actual = this;

       if (prefijo.isEmpty()) {
           return;
       }

       for (int c = 0; c < prefijo.length(); c++) {
           Character charActual = prefijo.charAt(c);
           if (!actual.hijos.containsKey(charActual)) {
              return;
           }
           actual = actual.hijos.get(charActual);
       }

       agregarPalabra(palabras, prefijo, actual);
    }
}
