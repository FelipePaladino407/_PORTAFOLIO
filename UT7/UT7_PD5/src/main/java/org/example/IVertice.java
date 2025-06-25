package org.example;

import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ernesto
 */
public interface IVertice {

    IAdyacencia buscarAdyacencia(IVertice verticeDestino);

    IAdyacencia buscarAdyacencia(Comparable etiquetaDestino);

    boolean eliminarAdyacencia(Comparable nomVerticeDestino);

    LinkedList<IAdyacencia> getAdyacentes();

    Object getDatos();

    Comparable getEtiqueta();

    boolean getVisitado();

    boolean insertarAdyacencia(Double costo, IVertice verticeDestino);

    Double obtenerCostoAdyacencia(IVertice verticeDestino);

    IVertice primerAdyacente();

    void setVisitado(boolean valor);

    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos
            todosLosCaminos);
  
}
