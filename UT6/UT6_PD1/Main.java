package org.example.UT6.PD1;

import org.example.UT5.ManejadorArchivosGenerico;

public class Main {
    public static void main(String[] args) {

        TArbolTrie trie = new TArbolTrie();
        trie.insertar("casa");
        trie.insertar("casamiento");
        trie.insertar("arbol");
        trie.insertar("grito");
        trie.imprimir();
        System.out.println(trie.predecir("ca"));

        String cadenas[] = ManejadorArchivosGenerico.leerArchivo("src/main/java/org/example/UT5/palabras.txt");
        for (int i = 0; i < cadenas.length; i++) {
            trie.insertar(cadenas[i]);
        }

        System.out.println(trie.buscar("programa")); // Está en trie (Devuelve 8)
        System.out.println(trie.buscar("program")); // No está en trie (Devuelve -1)
        System.out.println(trie.buscar("ala")); // Está en trie (Devuelve 3)
        System.out.println(trie.buscar("cotonete")); // No está
        System.out.println(trie.buscar("alabastro")); // Está y devuelve 9
        System.out.println(trie.buscar("alabarro")); // Non estamus -> -1
        System.out.println(trie.buscar("perro")); // Está -> 5
        System.out.println(trie.buscar("perrosssssssss")); // No está -> -1
        System.out.println(trie.buscar("pera")); // Está y devuelve 4
        System.out.println(trie.buscar("peste")); // NOOOO -> Devuelve -1

        System.out.println(trie.predecir("pe"));
        medirTiempoBusquedas(trie, "perro", 10000);

    }

    static void medirTiempoBusquedas(TArbolTrie trie, String palabra, int iteraciones) {

        long startTime = System.nanoTime();

        for (int i = 0; i < iteraciones; i++) {
            trie.buscar(palabra);
        }

        long endTime = System.nanoTime();
        long durationNs = endTime - startTime;
        double durationMs = durationNs / 1_000_000.0;

        System.out.println("Tiempo promedio por búsqueda: " +
                (durationMs / iteraciones) + " ms");
    }
}

