package org.example;

import java.util.LinkedList;
import java.util.List;

public class ElementoAB<T> implements IElementoAB<T> {
    Comparable etiqueta;
    ElementoAB<T> hijoIzq;
    ElementoAB<T> hijoDer;
    T datos;

    public ElementoAB(Comparable UnaEtiqueta, T unosDatos) {
        this.etiqueta = UnaEtiqueta;
        this.datos = unosDatos;
        this.hijoIzq = null;
        this.hijoDer = null;
    }

    @Override
    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    public void setEtiqueta(Comparable etiqueta) {
        this.etiqueta = etiqueta;
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
    public void setHijoIzq(IElementoAB<T> hijoIzq) {
        this.hijoIzq = (ElementoAB<T>) hijoIzq;
    }
    @Override
    public void setHijoDer(IElementoAB<T> hijoDer) {
        this.hijoDer = (ElementoAB<T>) hijoDer;
    }


    @Override
    public ElementoAB<T> buscar(Comparable unaEtiqueta) {
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


    @Override
    public IElementoAB eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(this.etiqueta) < 0) {
            if (hijoIzq != null) {
                hijoIzq = (ElementoAB<T>) hijoIzq.eliminar(unaEtiqueta);
            }
            return this;
        }

        if (unaEtiqueta.compareTo(this.etiqueta) > 0) {
            if (hijoDer != null) {
                hijoDer = (ElementoAB<T>) hijoDer.eliminar(unaEtiqueta);
            }
            return this;
        }

        return quitarNodo(this);
    }

    @Override
    public ElementoAB quitarNodo(ElementoAB unNodo) {
        if (hijoIzq == null) {
            return hijoDer;
        }

        if (hijoDer == null) {
            return hijoIzq;
        }

        ElementoAB elHijo = hijoIzq;
        ElementoAB elPadre = this;
        while (elHijo.hijoDer != null) {
            elPadre = elHijo;
            elHijo = elHijo.hijoDer;
        }

        if (elPadre != this) {
            elPadre.hijoDer = elHijo.hijoIzq;
            elHijo.hijoIzq = hijoIzq;
        }

        elHijo.hijoIzq = hijoDer;
        return elHijo;
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
            List<T> izq = this.hijoIzq.inOrden();
            listaResultado.addAll(izq);
        }
        if (this.hijoDer != null) {
            this.hijoDer.postOrden();
            List<T> der = this.hijoDer.inOrden();
            listaResultado.addAll(der);
        }
        listaResultado.add(this.getDatos());
        return listaResultado;
    }

    @Override
    public String InOrden() {
        String tempStr = "";
        if (hijoIzq != null) {
            tempStr += hijoIzq.InOrden();
        }
        tempStr += imprimir();
        if (hijoDer != null) {
            tempStr += hijoDer.InOrden();
        }
        return tempStr;
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
}

