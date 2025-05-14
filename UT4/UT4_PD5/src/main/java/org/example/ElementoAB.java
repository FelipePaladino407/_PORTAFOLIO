package org.example;

import java.util.LinkedList;
import java.util.List;

public class ElementoAB<T> implements IElementoAB<T> {
    Comparable etiqueta;
    ElementoAB<T> hijoIzq;
    ElementoAB<T> hijoDer;
    T datos;
    ElementoAB<T> padre;   // Aniado esto.

    public ElementoAB(Comparable<T> UnaEtiqueta, T unosDatos) {
        this.etiqueta = UnaEtiqueta;
        this.datos = unosDatos;
        this.hijoIzq = null;
        this.hijoDer = null;
        this.padre = null;
    }

    @Override
    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    @Override
    public IElementoAB<T> getHijoIzq() {
        return this.hijoIzq;
    }

    @Override
    public IElementoAB<T> getHijoDer() {
        return this.hijoDer;
    }

    @Override
    public IElementoAB<T> getPadre() {
        return this.padre;
    }

    @Override
    public void setHijoIzq(IElementoAB<T> HijoIzq) {
        HijoIzq = hijoIzq;
    }

    @Override
    public void setHijoDer(IElementoAB<T> HijoDer) {
        HijoDer = hijoDer;
    }

    @Override
    public void setPadre(IElementoAB<T> padre) {
        padre = padre;
    }

    @Override
    public ElementoAB<T> buscar(Comparable unaEtiqueta) { //Ejercicio 1(subB) a
        if (unaEtiqueta.compareTo(this.etiqueta) == 0) {
            return this; //CASO BASE
        } else {
            if (unaEtiqueta.compareTo(this.etiqueta) < 0) {
                if (this.hijoIzq != null) {
                    return this.hijoIzq.buscar(unaEtiqueta);
                } else {
                    return null; //CASO BASE
                }
            } else {
                if (unaEtiqueta.compareTo(this.etiqueta) > 0) {
                    if (this.hijoDer != null) {
                        return this.hijoDer.buscar(unaEtiqueta);
                    } else {
                        return null; //CASO BASE
                    }
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public boolean insertar(IElementoAB<T> elemento) {
        if (this.etiqueta == elemento.getEtiqueta()) {
            return false; // No se permiten etiquetas duplicadas
        }
        if (elemento.getEtiqueta().compareTo(this.etiqueta) < 0) {
            if (this.hijoIzq == null) {
                this.hijoIzq = (ElementoAB<T>) elemento;
                return true;
            } else {
                return this.hijoIzq.insertar(elemento);
            }
        } else {
            if (this.hijoDer == null) {
                this.hijoDer = (ElementoAB<T>) elemento;    // Ta bien castear?
                return true;
            } else {
                return this.hijoDer.insertar(elemento);
            }
        }

    }


    /** Metodo de eliminar actualizado.
     * @param unaEtiqueta Etiqueta del elemento a eliminar.
     * @return
     */
    @Override
    public ElementoAB<T> eliminar(Comparable unaEtiqueta) {
        return eliminarRecursivo(this, unaEtiqueta);
    }

    private ElementoAB<T> eliminarRecursivo(ElementoAB<T> nodo, Comparable etiqueta) {
        if (nodo == null) {
            return null;
        }
        int cmp = etiqueta.compareTo(nodo.etiqueta);
        if (cmp < 0) {
            nodo.hijoIzq = eliminarRecursivo(nodo.hijoIzq, etiqueta);
        } else if (cmp > 0) {
            nodo.hijoDer = eliminarRecursivo(nodo.hijoDer, etiqueta);
        } else {
            if (nodo.hijoIzq == null && nodo.hijoDer == null) {
                // CASO DE QUE NO TIENE HIJOS.
                return null;
            }
            if (nodo.hijoIzq == null) {
                // CASO SOLO HIJO DIESTRO.
                return nodo.hijoDer;
            }
            if (nodo.hijoDer == null) {
                // CASO SOLO HIJO ZURDO.
                return nodo.hijoIzq;
            }
            // CASO 3: PROCEDO A USAR EL MINIMO VALOR EN EL SUBARBOL DERECHO.
            ElementoAB<T> sucesor = minNodo(nodo.hijoDer);
            nodo.etiqueta = sucesor.etiqueta;
            nodo.datos    = sucesor.datos;
            // Se borra al sucesor en el arbol derecho.
            nodo.hijoDer  = eliminarRecursivo(nodo.hijoDer, sucesor.etiqueta);
        }
        return nodo;
    }

    private ElementoAB<T> minNodo(ElementoAB<T> nodo) {
        // Busca el más pequeno (izquierda más profunda).
        while (nodo.hijoIzq != null) {
            nodo = nodo.hijoIzq;
        }
        return nodo;
    }

    @Override
    public LinkedList<T> preOrden() {
        LinkedList<T> listaResultado = new LinkedList<>();
        listaResultado.add(this.getDatos());
        if (this.hijoIzq != null) {
            List<T> izq = this.hijoIzq.preOrden();
            listaResultado.addAll(izq);
        }
        if (this.hijoDer != null) {
            this.hijoDer.preOrden();
            List<T> der = this.hijoDer.preOrden();
            listaResultado.addAll(der);
        }
        return listaResultado;
    }

    @Override
    public LinkedList<T> inOrden() {
        LinkedList<T> listaResultado = new LinkedList<>();
        if (this.hijoIzq != null) {
            this.hijoIzq.inOrden();
            List<T> izq = this.hijoIzq.inOrden();
            listaResultado.addAll(izq);
        }
        listaResultado.add(this.getDatos());
        if (this.hijoDer != null) {
            this.hijoDer.inOrden();
            List<T> der = this.hijoDer.inOrden();
            listaResultado.addAll(der);
        }
        return listaResultado;

    }

    @Override
    public LinkedList<T> postOrden() {
        LinkedList<T> listaResultado = new LinkedList<>();
        if (this.hijoIzq != null) {
            this.hijoIzq.postOrden();
            List<T> izq = this.hijoIzq.postOrden();
            listaResultado.addAll(izq);
        }
        if (this.hijoDer != null) {
            this.hijoDer.postOrden();
            List<T> der = this.hijoDer.postOrden();
            listaResultado.addAll(der);
        }
        listaResultado.add(this.getDatos());
        return listaResultado;
    }


    public String imprimir() {
        return this.etiqueta.toString() + " ";
    }

    public boolean esVacio() {
        return (this.etiqueta == null);
    }

    public boolean esHoja() {
        return (this.hijoIzq == null && this.hijoDer == null);
    }

    public boolean tieneHijoIzq() {
        return (this.hijoIzq != null);
    }

    public boolean tieneHijoDer() {
        return (this.hijoDer != null);
    }

    @Override
    public int obtenerTamaño() {
        if (this == null) {
            return 0;
        } else {
            return 1 + this.getHijoIzq().obtenerTamaño() + this.getHijoDer().obtenerTamaño();
        }
    }

    @Override
    public int altura() {
        int A = -1;
        int B = -1;
        if (this.getHijoIzq() != null) {
            A = this.getHijoIzq().altura();
        }
        if (this.getHijoDer() != null) {
            B = this.getHijoDer().altura();
        }
        return Math.max(A, B) + 1;
    }

    @Override
    public int hojas() {
        int hojas = 0;
        if (this.esHoja()) {
            hojas++;
        }
        if (this.getHijoIzq() != null) {
            hojas += this.getHijoIzq().hojas();
        }
        if (this.getHijoDer() != null) {
            hojas += this.getHijoDer().hojas();
        }
        return hojas;
    }

    @Override
    public T getDatos() {
        return this.datos;
    }

    @Override
    public int insertarCont(IElementoAB<T> elemento) {
        int cont = 0;
        if (this.etiqueta == elemento.getEtiqueta()) {
            return cont; // No se permiten etiquetas duplicadas
        }
        if (elemento.getEtiqueta().compareTo(this.etiqueta) < 0) {
            if (this.hijoIzq == null) {
                this.hijoIzq = (ElementoAB<T>) elemento;
                return cont + 1;
            } else {
                cont += this.hijoIzq.insertarCont(elemento);
                return cont + 1;
            }
        } else {
            if (this.hijoDer == null) {
                this.hijoDer = (ElementoAB<T>) elemento;    // Ta bien castear?
                return cont + 1;
            } else {
                this.hijoDer.insertarCont(elemento);
                cont += this.hijoDer.insertarCont(elemento);
                return cont + 1;
            }
        }

    }

    @Override
    public int buscarCont(Comparable unaEtiqueta) {
        int contador = 1;
        if (unaEtiqueta.compareTo(this.etiqueta) == 0) {
            return contador;
        } else if (unaEtiqueta.compareTo(this.etiqueta) < 0) {
            if (hijoIzq != null) {
                return contador + hijoIzq.buscarCont(unaEtiqueta);
            }
        } else {
            if (hijoDer != null) {
                return contador + hijoDer.buscarCont(unaEtiqueta);
            }
        }
        return -1;  // no se encontró
    }

    @Override
    public int obtenerNiveles() {
        return this.altura() + 1;
    }


    @Override
    public ElementoAB claveInmediata(ElementoAB unNodo){
        if (unNodo == null) {
            return null;
        }
        // Caso si es que tiene hijo izquierdo (implementacion de Ramiro).
        if (unNodo.tieneHijoIzq()){
            ElementoAB<T> actual = unNodo.hijoIzq;
            while (actual.hijoDer != null) {
                actual = actual.hijoDer;   // El mas grande a la izquierda.
            }
            return actual;
        }
        // Caso en el que no tenga hijo izquierdo
        ElementoAB<T> ancestro = unNodo.padre;
        ElementoAB<T> actual = unNodo;
        while (ancestro != null && actual == ancestro.hijoIzq) {
            actual = ancestro;
            ancestro = ancestro.padre;
        }
        return ancestro;
    }


    @Override
    public boolean esBusqueda(ElementoAB<T> nodo, Comparable min, Comparable max){
        if (nodo == null) {
            return true;
        }
        if ((min != null && nodo.getEtiqueta().compareTo(min) <= 0) ||
                (max != null && nodo.getEtiqueta().compareTo(max) >= 0)) {
            return false;
        }
        return esBusqueda(nodo.hijoIzq, min, nodo.getEtiqueta()) &&
                esBusqueda(nodo.hijoDer, nodo.getEtiqueta(), max);
    }

}