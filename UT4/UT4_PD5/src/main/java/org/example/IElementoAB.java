package org.example;

import java.util.LinkedList;

/**
 * Interfaz para el elemento de un árbol binario.
 *
 * @param <T> Tipo de los datos almacenados en el elemento.
 */
@SuppressWarnings({"rawtypes"})
public interface IElementoAB<T> {

    /**
     * Obtiene el valor de la etiqueta del nodo.
     *
     * @return Etiqueta del nodo.
     */
    public Comparable getEtiqueta();

    /**
     * Obtiene el hijo izquierdo del nodo.
     *
     * @return Hijo izquierdo del nodo.
     */
    public IElementoAB<T> getHijoIzq();

    /**
     * Obtiene el hijo derecho del nodo.
     *
     * @return Hijo derecho del nodo.
     */
    public IElementoAB<T> getHijoDer();

    /**
     * Asigna el hijo izquierdo del nodo.
     *
     * @param elemento Elemento a asignar como hijo izquierdo.
     */
    public void setHijoIzq(IElementoAB<T> elemento);

    /**
     * Asigna el hijo derecho del nodo.
     *
     * @param elemento Elemento a asignar como hijo derecho.
     */
    public void setHijoDer(IElementoAB<T> elemento);


    /**
     * Asigna un padre.
     * @return
     */
    public IElementoAB<T> getPadre();

    public void setPadre(IElementoAB<T> elemento);

    /**
     * Busca un elemento dentro del árbol con la etiqueta indicada.
     *
     * @param unaEtiqueta Etiqueta del nodo a buscar.
     * @return Elemento encontrado. Si no se encuentra, retorna nulo.
     */
    public IElementoAB<T> buscar(Comparable unaEtiqueta);

    /**
     * Inserta un elemento en el árbol.
     *
     * @param elemento Elemento a insertar.
     * @return Éxito de la operación.
     */
    public boolean insertar(IElementoAB<T> elemento);

    /**
     * Agrega las etiquetas del recorrido en preorden a una lista enlazada.
     *
     // * @param unaLista Lista en la cual se deben agregar las etiquetas.
     */
    public LinkedList<T> preOrden();

    /**
     * Agrega las etiquetas del recorrido en inorden a una lista enlazada.
     *
     // * @param unaLista Lista en la cual se deben agregar las etiquetas.
     */
    public LinkedList<T> inOrden();

    /**
     * Agrega las etiquetas del recorrido en postorden a una lista enlazada.
     *
     // * @param unaLista Lista en la cual se deben agregar las etiquetas.
     */
    public LinkedList<T> postOrden();

    /**
     * Retorna los datos contenidos en el elemento.
     *
     * @return Los datos del elemento.
     */
    public T getDatos();

    /**
     * Elimina un elemento dado una etiqueta.
     *
     * @param unaEtiqueta Etiqueta del elemento a eliminar.
     * @return Elemento que fue eliminado. Si no se encuentra, retorna nulo.
     */
    public IElementoAB<T> eliminar(Comparable unaEtiqueta);

    /**
     * Obtiene el tamaño del árbol (número de elementos).
     *
     * @return Tamaño del árbol.
     */
    public int obtenerTamaño();

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

    public int obtenerNiveles();

    public int buscarCont(Comparable unaEtiqueta);

    /**
     * Inserta un nuevo elemento en el Arbol, e implementa un contador que
     * se incrementa con cada invocación al mismo
     * @param elemento
     * @return int que representa la cantidad de veces que se ha invocado al
     * método insertarCont
     */
    public int insertarCont(IElementoAB<T> elemento); //Ejercicio 1, a



    /**
     * Busca el nodo con la clave inmediata a la del nodo dado.
     * @param unNodo
     * @return Elemento del árbol con la clave inmediata.
     */
     public ElementoAB claveInmediata(ElementoAB unNodo);

    /**
     * Lista todas las hojas del árbol indicando el nivel en el que se ubican.
     * @return
     */

     public boolean esBusqueda(ElementoAB<T> nodo, Comparable min, Comparable max);

}