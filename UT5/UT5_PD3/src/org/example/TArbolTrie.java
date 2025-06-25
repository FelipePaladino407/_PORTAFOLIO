package org.example;

import java.util.LinkedList;

public class TArbolTrie implements IArbolTrie {
    private TNodoTrie raiz;

    @Override
    public void insertar(String palabra) {
        if (raiz == null) raiz = new TNodoTrie();
        raiz.insertar(palabra);
    }

    @Override
    public void imprimir() {
        if (raiz != null) raiz.imprimir();
    }

    @Override
    public int buscar(String palabra) {
        if (raiz == null) return 0;
        int comparaciones = raiz.buscar(palabra);
        if (comparaciones == 0) {
            System.out.println(palabra + " no existe");
        } else {
            TNodoTrie nodo = raiz.obtenerNodo(palabra);
            System.out.print(palabra + " (comparaciones=" + comparaciones + ") páginas:");
            for (int p : nodo.paginas) System.out.print(" " + p);
            System.out.println();
        }
        return comparaciones;
    }

    @Override
    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> resultados = new LinkedList<>();
        if (raiz != null) {
            raiz.predecir(prefijo, resultados);
        }
        return resultados;
    }

    // Indizar un libro: cada 50 lneas = una pgina.
    public void indizarLibro(String nombreArchivo) {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        for (int i = 0; i < lineas.length; i++) {
            int pagina = (i / 50) + 1;
            // Limpiar y separar palabras.
            String limpio = lineas[i].replaceAll("[^A-Za-z]+", " ").toLowerCase();
            String[] partes = limpio.split("\\s+");
            for (String w : partes) {
                if (w.isEmpty()) continue;
                TNodoTrie nodo = raiz.obtenerNodo(w);
                if (nodo != null && nodo.esPalabra) {
                    nodo.agregarPagina(pagina);
                }
            }
        }
    }

    // Imprime indice completo (palabra + pginas).
    public void imprimirIndice() {
        if (raiz != null) raiz.imprimirIndice("", raiz);
    }
}
