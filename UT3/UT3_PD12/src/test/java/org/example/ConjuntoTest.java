package org.example;

import java.util.*;
import java.io.*;

class ConjuntoTest {      // Arreglar si se se puede.

    public static void main(String[] args) {
        // Test 1: Unión de dos conjuntos
        Conjunto A = new Conjunto();
        A.getElementos().addAll(Arrays.asList("LJOSE", "KLEMM"));

        Conjunto B = new Conjunto();
        B.getElementos().addAll(Arrays.asList("KLEMM", "MARIA"));

        Conjunto union = (Conjunto) A.union(B);
        assert union.getElementos().containsAll(Arrays.asList("LJOSE", "KLEMM", "MARIA"));

        // Test 2: Intersección de dos conjuntos
        Conjunto interseccion = (Conjunto) A.interseccion(B);
        assert interseccion.getElementos().contains("KLEMM");
        assert interseccion.getElementos().size() == 1;

        System.out.println("._.");
    }
}