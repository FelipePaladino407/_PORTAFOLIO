package org.example;

import org.example.Ejercicio3.Abonado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TTrieHashMap implements ITrie{

    private INodoTrie root;

    public TTrieHashMap() {
        root = new TNodoTrieHashMap();
    }

    @Override
    public void insertar(String palabra) {
        root.insertar(palabra);

    }

    @Override
    public boolean buscar(String palabra) {
        return root.buscar(palabra);
    }

    @Override
    public List<String> autocompletar(String prefijo) {
        List<String> resultadongos = new LinkedList<>();
        root.predecir(prefijo, resultadongos);
        return resultadongos;
    }

    @Override
    public List<Integer> buscarPatrones(String texto) {
        List<Integer> posiciones = new LinkedList<>();
        root.buscarPatrones(texto, posiciones);
        return posiciones;
    }

    /** Ejercio 3
     *
     */

    @Override
    public void insertarTelefonos(String telefono, String nombre) {
        root.insertarTelefono(telefono, nombre);
    }

    @Override
    public List<Abonado> buscarTelefonos(String codigoPais, String codigoArea) {
        String prefijo = codigoPais + codigoArea;
        List<Abonado> resultados = new ArrayList<>();
        root.buscarTelefonos(prefijo, resultados);
        Collections.sort(resultados); // orden alfabtico por nombre.
        return resultados;
    }




}
