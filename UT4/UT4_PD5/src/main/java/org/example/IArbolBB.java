package org.example;


import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes"})
public interface IArbolBB<T> {

    /**
     * Inserta un elemento en el árbol. Si ya existe un elemento con la clave
     * proporcionada en "etiqueta", retorna falso.
     *
     * @param etiqueta Clave del elemento a insertar.
     * @param unDato   Dato del elemento a insertar.
     * @return Verdadero si la inserción fue exitosa, falso en caso contrario.
     */
    public boolean insertar(Comparable etiqueta, T unDato);

    /**
     * Busca un elemento en el árbol utilizando la etiqueta como clave de búsqueda.
     *
     * @param unaEtiqueta Etiqueta identificadora del elemento a buscar.
     * @return El elemento encontrado. Si no se encuentra, retorna nulo.
     */
    public T buscar(Comparable unaEtiqueta);

    /**
     * Elimina un elemento del árbol basado en su etiqueta.
     *
     * @param unaEtiqueta La etiqueta del elemento a eliminar.
     */
    public void eliminar(Comparable unaEtiqueta);

    /**
     * Realiza un recorrido en preorden del árbol.
     *
     * @return Una lista con los elementos del recorrido en preorden.
     */
    public List<T> preOrden();

    /**
     * Realiza un recorrido en inorden del árbol.
     *
     * @return Una lista con los elementos del recorrido en inorden.
     */
    public List<T> inOrden();

    /**
     * Realiza un recorrido en postorden del árbol.
     *
     * @return Una lista con los elementos del recorrido en postorden.
     */
    public List<T> postOrden();

    /**
     * Retorna si el árbol es vacío.
     * @return Verdadero si el árbol es vacío, falso en caso contrario.
     */
    public boolean esVacio();

    /**
     * Vacia el árbol.
     * @return Verdadero si el árbol fue vaciado, falso en caso contrario.
     */
    public boolean vaciar();
    /**
     * Devuelve la altura de un arbol.
     *
     * @return Altura del arbol
     */
    public int altura();

    /**
     * Devuelve la cantidad de hojas de un arbol (nodos sin hijos).
     *
     * @return Cantiddad de hojas del arbol
     */
    public int hojas();

    /**
     * Devuelve un string con el recorrido del árbol en preorden.
     * @return String con el recorrido en preorden.
     */
    public String preOrdenStr();

    /**
     * Devuelve un string con el recorrido del árbol en inorden.
     * @return String con el recorrido en inorden.
     */
    public String inOrdenStr();

    /**
     * Devuelve un string con el recorrido del árbol en postorden.
     * @return String con el recorrido en postorden.
     */
    public String postOrdenStr();

    /**
     * Busca el nodo con la menor clave en el árbol.
     * @return Elemento del árbol con la menor clave.
     */
    public Comparable menorClave();

    /**
     * Busca el nodo con la mayor clave en el árbol.
     * @return Elemento del árbol con la mayor clave.
     */
    public Comparable mayorClave();

    public Map<Comparable, Integer> hojasPorNivel();

    public int cantidadNodosNiveles(int pp);

}
