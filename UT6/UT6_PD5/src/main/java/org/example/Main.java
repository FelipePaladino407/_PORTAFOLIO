package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        TTrieHashMap arbolito = new TTrieHashMap();

        arbolito.insertar("casa");
        arbolito.insertar("casamiento");
        arbolito.insertar("casados");
        arbolito.insertar("casco");
        arbolito.insertar("carro");
        arbolito.insertar("camioneta");
        arbolito.insertar("casucuntusmonomunts");

        System.out.println("Buscar 'casa': " + arbolito.buscar("casa"));
        System.out.println("Buscar 'casamiento': " + arbolito.buscar("casamiento"));
        System.out.println("Buscar 'cas (no existe)': " + arbolito.buscar("cas"));

        System.out.println("Autocompletar 'cas': " + arbolito.autocompletar("cas"));

        String testo = "elcasosecarroenuncasamiento";
        List<Integer> posiciones = arbolito.buscarPatrones(testo);
        System.out.println("\nPatrones encontrados en posiciones: " + posiciones);
    }


}
