package org.example;

import java.util.*;

public class ArbolBB<T> implements IArbolBB<T> {
    public ElementoAB<T> raiz;

    @Override
    public boolean insertar(Comparable etiqueta, T unDato) {
        if (esVacio()){
            raiz = new ElementoAB<T>(etiqueta, unDato);
            return true;
        }
        return raiz.insertar(new ElementoAB<T>(etiqueta, unDato));
    }
    // Sub-equipo A. parte a)
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
    @Override
    public String preOrdenStr(){
        return raiz.preOrden().toString();
    }

    @Override
    public String inOrdenStr(){
        return raiz.inOrden().toString();
    }

    @Override
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

    @Override
    public Comparable menorClave(){
        if (esVacio()){
            throw new IllegalStateException("Arbol vacio.");
        }
        return minimoRecursivo(raiz);


    }
    private Comparable minimoRecursivo(ElementoAB<T> nodo){
        if (!nodo.tieneHijoIzq()) {
            return nodo.getEtiqueta();
        }
        return minimoRecursivo((ElementoAB<T>) nodo.getHijoIzq());
    }

    // A partir de aqui a abajo, se encuentran los metodos del UT4_PD5:

    @Override
    public Comparable mayorClave(){
        if (esVacio()){
            throw new IllegalStateException("Arbol vacio.");
        }
        return mayorRecursivo(raiz);
    }
    private Comparable mayorRecursivo(ElementoAB<T> nodo){
        if (!nodo.tieneHijoDer()) {
            return nodo.getEtiqueta();
        }
        return mayorRecursivo((ElementoAB<T>) nodo.getHijoDer());
    }

    /**
     * Devuelve un Map donde cada hoja del arbol esta asociada a un nivel.
     * La raiz es nivel 0.
     * @return Un mapita.
     */
    @Override
    public Map<Comparable, Integer> hojasPorNivel(){
        if (esVacio()){
            throw new IllegalStateException("Arbol vacio.");
        }
        Map<Comparable, Integer> hojas = new LinkedHashMap<>();
        recorrerHojas(raiz, 0, hojas);
        return hojas;

    }
    private void recorrerHojas(ElementoAB<T> nodo, int nivel, Map<Comparable, Integer> pp){
        if (nodo == null){
            return;
        }
        if (!nodo.tieneHijoIzq() && !nodo.tieneHijoDer()){   // Caso de que sea hoja.
            pp.put(nodo.getEtiqueta(), nivel);
        }
        else{
            recorrerHojas((ElementoAB<T>) nodo.getHijoIzq(), nivel + 1, pp);
            recorrerHojas((ElementoAB<T>) nodo.getHijoDer(), nivel + 1, pp);
        }
    }

    @Override
    public int cantidadNodosNiveles(int nivelBuscado) {
        if (nivelBuscado < 0) {
            throw new IllegalStateException("El nivel no puede ser negativo.");
        }
        // Empieza en la raíz (nivelActual = 0)
        return contarNivelRecursivo((ElementoAB<T>) raiz, nivelBuscado, 0);
    }

    private int contarNivelRecursivo(ElementoAB<T> nodo, int nivelBuscado, int nivelActual) {
        // 1) Si no hay nodo, no hay nada que contar
        if (nodo == null) {
            return 0;
        }
        // 2) Si llegamos al nivel deseado, contamos este único nodo
        if (nivelActual == nivelBuscado) {
            return 1;
        }
        // 3) Si aún no llegamos, descendemos a hijos (incrementando nivelActual)
        int izq = contarNivelRecursivo((ElementoAB<T>) nodo.getHijoIzq(), nivelBuscado, nivelActual + 1);
        int der = contarNivelRecursivo((ElementoAB<T>) nodo.getHijoDer(), nivelBuscado, nivelActual + 1);
        return izq + der;
    }

    






















    public String dibujarEstructura() {
        List<String> líneas = dibujarRecursivo(raiz);
        return String.join("\n", líneas);
    }

    private List<String> dibujarRecursivo(ElementoAB<T> nodo) {
        if (nodo == null) return new LinkedList<>();

        List<String> izq = dibujarRecursivo((ElementoAB<T>) nodo.getHijoIzq());
        List<String> der = dibujarRecursivo((ElementoAB<T>) nodo.getHijoDer());
        String et = nodo.getEtiqueta().toString();

        int anchoIzq = anchoMáximo(izq), anchoDer = anchoMáximo(der);
        int sep = 1;

        // 1) Línea raíz (la etiqueta centrada entre los recuadros)
        String líneaRaíz = repeat(" ", anchoIzq + sep) + et;

        // 2) Si el nodo NO es hoja, dibujamos sólo las ramas que existen
        List<String> resultado = new LinkedList<>();
        resultado.add(líneaRaíz);

        if (nodo.tieneHijoIzq() || nodo.tieneHijoDer()) {
            // Posiciones relativas de cada rama
            int posIzq = anchoIzq;
            int posDer = anchoIzq + sep + et.length() - 1;

            // Construyo un char[] lleno de espacios y luego pongo '/' o '\' donde haga falta
            int totalAncho = anchoIzq + sep + et.length() + anchoDer;
            char[] ramaChars = new char[totalAncho];
            Arrays.fill(ramaChars, ' ');

            if (nodo.tieneHijoIzq())
                ramaChars[posIzq] = '/';
            if (nodo.tieneHijoDer())
                ramaChars[posDer] = '\\';

            resultado.add(new String(ramaChars));
        }

        // 3) Extiendo subárboles para que tengan igual número de líneas
        int alto = Math.max(izq.size(), der.size());
        extender(izq, alto, anchoIzq);
        extender(der, alto, anchoDer);

        // 4) Combino línea a línea
        for (int i = 0; i < alto; i++) {
            String partIzq = izq.get(i);
            String partDer = der.get(i);
            resultado.add(
                    partIzq
                            + repeat(" ", sep + et.length())
                            + partDer
            );
        }

        return resultado;
    }


    private int anchoMáximo(List<String> líneas) {
        return líneas.stream()
                .mapToInt(String::length)
                .max().orElse(0);
    }

    private void extender(List<String> líneas, int alto, int ancho) {
        while (líneas.size() < alto) {
            líneas.add(repeat(" ", ancho));
        }
    }

    private String repeat(String s, int veces) {
        return String.join("", Collections.nCopies(veces, s));
    }
}

