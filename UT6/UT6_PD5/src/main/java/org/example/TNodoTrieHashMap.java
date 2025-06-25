package org.example;

import org.example.Ejercicio3.Abonado;

import java.util.HashMap;
import java.util.List;

public class TNodoTrieHashMap implements INodoTrie{

    private HashMap<Character, TNodoTrieHashMap> hijos;
    public boolean esPalabra;

    private Abonado abonado;  // Ejercicio 3.

    TNodoTrieHashMap() {
        hijos = new HashMap<>();
        esPalabra = false;
        abonado = null;
    }

    @Override
    public void insertar(String palabra) {
        TNodoTrieHashMap nodo = this;
        for (char pp : palabra.toCharArray()) {
            nodo = nodo.hijos.computeIfAbsent(pp, k -> new TNodoTrieHashMap());
        }
        nodo.esPalabra = true;

    }

    @Override
    public boolean buscar(String palabra) {
        TNodoTrieHashMap nodo = this;
        for (char pp : palabra.toCharArray()) {
            nodo = nodo.hijos.get(pp);

            if (nodo == null) {
                return false;
            }
        }
        return nodo.esPalabra;

    }

    @Override
    public void predecir(String prefijo, List<String> resultados) {
        TNodoTrieHashMap nodo = this;
        for (char pp : prefijo.toCharArray()) {
            nodo = nodo.hijos.get(pp);
            if (nodo == null) {
                return;
            }
        }
        nodo.colectarPalabritas(prefijo, resultados);

    }

    private void colectarPalabritas(String prefijo, List<String> resultados) {
        if (esPalabra) {
            resultados.add(prefijo);
        }
        for (var entrada : hijos.entrySet()) {
            entrada.getValue().colectarPalabritas(prefijo + entrada.getKey(), resultados);
        }

    }

    @Override
    public void buscarPatrones(String texto, List<Integer> posiciones) {
        for (int i = 0; i <texto.length(); i++) {
            TNodoTrieHashMap nodo = this;
            int j = i;
            while (j < texto.length() && nodo.hijos.containsKey(texto.charAt(j))) {
                nodo = nodo.hijos.get(texto.charAt(j));
                if (nodo.esPalabra) {
                    posiciones.add(i);
                }
                j++;
            }
        }

    }

    /** Ejercicio 3
     */

    @Override
    public void insertarTelefono(String telefono, String nombre) {
        TNodoTrieHashMap nodo = this;
        for (char pp : telefono.toCharArray()) {
            nodo = nodo.hijos.computeIfAbsent(pp, k -> new TNodoTrieHashMap());
        }
        nodo.esPalabra = true;
        nodo.abonado = new Abonado(nombre, telefono);

    }

    public void buscarTelefonos(String prefijo, List<Abonado> resultados) {
        TNodoTrieHashMap nodo = this;
        for (char c : prefijo.toCharArray()) {
            nodo = nodo.hijos.get(c);
            if (nodo == null) return;
        }
        nodo.colectarAbonados(resultados);
    }

    private void colectarAbonados(List<Abonado> resultados) {
        if (abonado != null) {
            resultados.add(abonado);
        }
        for (TNodoTrieHashMap hijo : hijos.values()) {
            hijo.colectarAbonados(resultados);
        }
    }
}
