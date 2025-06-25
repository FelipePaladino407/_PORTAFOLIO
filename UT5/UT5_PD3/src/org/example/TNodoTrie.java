package org.example;

import java.util.LinkedList;
import java.util.List;

public class TNodoTrie implements INodoTrie {
    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrie[] hijos;
    boolean esPalabra;
    List<Integer> paginas;  // Lista de páginas donde aparece la palabra.

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        esPalabra = false;
        paginas = new LinkedList<>();
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrie nodo = this;
        for (char c : unaPalabra.toCharArray()) {
            int indice = c - 'a';
            if (indice < 0 || indice >= CANT_CHR_ABECEDARIO) continue;
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
    }

    // Agrega la página a la lista (sin duplicados).
    public void agregarPagina(int pagina) {
        if (!paginas.contains(pagina)) {
            paginas.add(pagina);
        }
    }

    // Devuelve el nodo correspondiente a la palabra (o null).
    public TNodoTrie obtenerNodo(String s) {
        TNodoTrie nodo = this;
        for (char c : s.toCharArray()) {
            int indice = c - 'a';
            if (indice < 0 || indice >= CANT_CHR_ABECEDARIO || nodo.hijos[indice] == null) {
                return null;
            }
            nodo = nodo.hijos[indice];
        }
        return nodo;
    }

    @Override
    public int buscar(String s) {
        // Cuenta comparaciones y recorre el trie.
        TNodoTrie nodo = this;
        int comparaciones = 0;
        for (char c : s.toCharArray()) {
            int indice = c - 'a';
            comparaciones++;
            if (indice < 0 || indice >= CANT_CHR_ABECEDARIO || nodo.hijos[indice] == null) {
                return 0; // no existe
            }
            nodo = nodo.hijos[indice];
        }
        return nodo.esPalabra ? comparaciones : 0;
    }

    @Override
    public void predecir(String prefijo, java.util.LinkedList<String> palabras) {
        // igual que antes
        TNodoTrie nodo = obtenerNodo(prefijo);
        if (nodo != null) {
            predecirRec(prefijo, palabras, nodo);
        }
    }

    private void predecirRec(String prefijo, java.util.LinkedList<String> palabras, TNodoTrie nodo) {
        if (nodo.esPalabra) palabras.add(prefijo);
        for (int i = 0; i < CANT_CHR_ABECEDARIO; i++) {
            if (nodo.hijos[i] != null) {
                predecirRec(prefijo + (char)(i + 'a'), palabras, nodo.hijos[i]);
            }
        }
    }

    @Override
    public void imprimir() {
        imprimirRec("", this);
    }

    private void imprimirRec(String s, TNodoTrie nodo) {
        if (nodo.esPalabra) {
            System.out.println(s);
        }
        for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
            if (nodo.hijos[c] != null) {
                imprimirRec(s + (char)(c + 'a'), nodo.hijos[c]);
            }
        }
    }

    // Imprime el índice: palabra y páginas.
    public void imprimirIndice(String prefijo, TNodoTrie nodo) {
        if (nodo.esPalabra) {
            System.out.print(prefijo);
            for (int p : nodo.paginas) {
                System.out.print(" " + p);
            }
            System.out.println();
        }
        for (int i = 0; i < CANT_CHR_ABECEDARIO; i++) {
            if (nodo.hijos[i] != null) {
                imprimirIndice(prefijo + (char)(i + 'a'), nodo.hijos[i]);
            }
        }
    }
}