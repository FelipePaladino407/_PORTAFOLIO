package org.example;

import org.example.Ejercicio3.Abonado;

import java.util.List;

public interface ITrie {

    void insertar(String palabra);

    boolean buscar(String palabra);

    List<String> autocompletar(String prefijo);

    List<Integer> buscarPatrones(String texto);

    void insertarTelefonos(String telefono, String nombre);

    List<Abonado> buscarTelefonos(String codigoPais, String codigoArea);

}
