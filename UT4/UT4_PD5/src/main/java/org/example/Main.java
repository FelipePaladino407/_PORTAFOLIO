package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
       ArbolBB<Integer> arbolito = new ArbolBB<>();

       System.out.println(arbolito.esVacio());
       arbolito.insertar("5", 5);
       System.out.println(arbolito.esVacio());
       arbolito.insertar("6", 6);
       arbolito.insertar("8", 8);
       arbolito.insertar("4", 4);
       arbolito.insertar("7", 7);
       arbolito.insertar("1", 1);
       arbolito.insertar("2", 2);
       arbolito.insertar("9", 9);
       arbolito.insertar("3", 3);

       System.out.println("-------");
       // System.out.println(arbolito.cantidadNodosNiveles(2));
       System.out.println("-------");


       System.out.println(arbolito.menorClave());
       System.out.println(arbolito.mayorClave());
       System.out.println(arbolito.hojas());
       System.out.println(arbolito.inOrdenStr());
       System.out.println(arbolito.postOrdenStr());

       System.out.println(arbolito.dibujarEstructura());
       System.out.println("-------");
       arbolito.eliminar("2");
       arbolito.eliminar("1");
       arbolito.eliminar("8");
       arbolito.eliminar("5");
       arbolito.insertar("9.9", 9);
       arbolito.insertar("5", 5);
       System.out.println(arbolito.preOrden());
       System.out.println(arbolito.dibujarEstructura());




      System.out.println("-------");
       // Ojo, esto siempre teniendo en cuenta que la raiz esta en el nivel 0.
       Map<Comparable, Integer> hojitas = arbolito.hojasPorNivel();
       hojitas.forEach((clave, nivel) -> System.out.println("Hoja: " + clave + " -- Nivel: " + nivel));
       System.out.println("-------");
       System.out.println(arbolito.cantidadNodosNiveles(1));





    }
}