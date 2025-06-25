package org.example;

import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public interface IArbolTrie {

    void imprimir();
    int buscar(String palabra);

    void insertar(String palabra);

    LinkedList<String> predecir(String prefijo);

}