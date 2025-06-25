package org.example;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        TArbolTrie trie = new TArbolTrie();

        String[] dic = ManejadorArchivosGenerico.leerArchivo("/home/felipe/Documents/AED/UT5/UT5_PD3/src/org/example/palabrasIndice.txt");
        for (String w : dic) {
            String p = w.replaceAll("[^A-Za-z]+", "").toLowerCase();
            if (!p.isEmpty()) trie.insertar(p);
        }

        trie.indizarLibro("/home/felipe/Documents/AED/UT5/UT5_PD3/src/org/example/libro.txt");

        System.out.println("Índice de palabras y páginas:");
        trie.imprimirIndice();

        String[] aBuscar = ManejadorArchivosGenerico.leerArchivo("/home/felipe/Documents/AED/UT5/UT5_PD3/src/org/example/palabrasABuscar.txt");
        System.out.println("\nResultados de búsqueda:");
        for (String w : aBuscar) {
            String p = w.replaceAll("[^A-Za-z]+", "").toLowerCase();
            if (!p.isEmpty()) trie.buscar(p);
        }
    }
}
