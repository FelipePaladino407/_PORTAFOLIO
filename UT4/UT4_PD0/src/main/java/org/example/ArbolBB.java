package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ArbolBB<T> implements IArbolBB<T> {
    public ElementoAB<T> raiz;


    /** PD0_Ejercicio 3.
     * Tornaremos a implementar un diccionario, necesario para poder remplazar variables por constantes
     * numericas. Cada variable tiene un valor de constante predefinido.
     */
    Map<String, Integer> diccionario = new HashMap<>();

    public ArbolBB() {
        diccionario.put("x", 5);
        diccionario.put("y", 4);
        diccionario.put("z", 3);
    }

    public void sustituir(){
        this.raiz = sustituirRecursivo(this.raiz);
    }

    private ElementoAB<T> sustituirRecursivo(ElementoAB<T> nodo)
    {
        if (nodo == null) return null;

        Comparable etiqueta = nodo.getEtiqueta();
        T datosOG = nodo.getDatos();    // Datos originales del nodo.

        if (!nodo.tieneHijoDer() && !nodo.tieneHijoIzq()){    // Si el nodo es hoja.
            if (etiqueta instanceof String){
                Integer pp = diccionario.get(etiqueta.toString());

                if (pp != null){    // Siempre y cuando se encuntre la variable en el diccionario.

                    // Misma etiqueta,p pero ahora con el nuevo dato, que deja de ser una variable.
                    return new ElementoAB<>(etiqueta, (T) pp);
                }
                // Si no se encuentra la variable o simplemente no es variable, se retorna el nodo existente.
                return new ElementoAB<>(etiqueta, datosOG);
            }

        }
        ElementoAB<T> copia = new ElementoAB<>(etiqueta, datosOG);
        copia.setHijoIzq(sustituirRecursivo((ElementoAB<T>) nodo.getHijoIzq()));
        copia.setHijoDer(sustituirRecursivo((ElementoAB<T>) nodo.getHijoDer()));
        return copia;
    }

    public int evaluar(){
        if (this.raiz == null){
            throw new IllegalStateException("El arbol esta vacio, por favor revisa lo que haces.");
        }
        return evaluarRecursivo(raiz);
    }

    private int evaluarRecursivo(ElementoAB<T> nodo){

        // Caso base (version base), evaluamos que la raiz sea hoja.
        if (!nodo.tieneHijoIzq() && !nodo.tieneHijoDer()){

            return (Integer) nodo.getDatos();
        }

        int izq = evaluarRecursivo((ElementoAB<T>) nodo.getHijoIzq());
        int der = evaluarRecursivo((ElementoAB<T>) nodo.getHijoDer());

        String operador = nodo.getEtiqueta().toString();
        switch (operador){
            case "+": return izq + der;
            case "-": return izq - der;
            case "*": return izq * der;
            case "/": if (der != 0){
                return izq / der;
            }
            else{
                throw new ArithmeticException("Division por cero.");
            }
            default:
                throw new IllegalArgumentException("Operador: " + operador + " desconocido.");
        }

    }




    @Override
    public boolean insertar(Comparable etiqueta, T unDato) {
        if (esVacio()){
            raiz = new ElementoAB<T>(etiqueta, unDato);
            return true;
        }
        return raiz.insertar(new ElementoAB<T>(etiqueta, unDato));
    }

    public int insertarCont(Comparable etiqueta, T unDato) {
        if (esVacio()){
            raiz = new ElementoAB<T>(etiqueta, unDato);
            System.out.println(1);;
            return 1;
        }
        else{
            int cont = raiz.insertarCont(new ElementoAB<T>(etiqueta, unDato));
            System.out.println(cont);
            return cont;
        }
    }

    @Override
    public T buscar(Comparable unaEtiqueta) {
        if(esVacio()){
            return null;
        } else{
            if (raiz.buscar(unaEtiqueta) != null)
                return raiz.buscar(unaEtiqueta).getDatos();
            else
                return null;
        }
    }

    public int buscarCont(Comparable unaEtiqueta){
        if (esVacio()){
            return -1;
        } else {
            return raiz.buscarCont(unaEtiqueta);
        }
    }

    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (esVacio()){
            return;
        }
        raiz.eliminar(unaEtiqueta);

    }

    @Override
    public List<T> preOrden() {
        if (esVacio()){
            return null;
        }
        return raiz.preOrden();
    }

    @Override
    public List<T> inOrden() {
        if (esVacio()){
            return null;
        }
        return raiz.inOrden();
    }

    @Override
    public List<T> postOrden() {
        if (esVacio()){
            return null;
        }
        return raiz.postOrden();
    }
    // Sub-equipo A. Parte c)
    public String preOrdenStr(){
        return raiz.preOrden().toString();
    }

    public String inOrdenStr(){
        return raiz.inOrden().toString();
    }

    public String postOrdenStr(){
        return raiz.postOrden().toString();
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

    @Override
    public boolean vaciar() {
        if (esVacio()){
            return false;
        }
        else
            raiz = null;
        return true;
    }
    public int hojas(){
        if (esVacio()){
            return 0;
        }
        else{
            if (raiz.esHoja()){
                return 1;
            }
            else{
                return raiz.hojas();
            }
        }
    }
    public int altura(){
        if (esVacio()){
            return -1;
        }
        else{
            return raiz.altura();
        }
    }
}
