package org.example;// Main para probar el funcionamiento de los conjuntos. No es EJERCICIO #3

public class Main {
    public static void main(String[] args) {

        Conjunto<String> conjuntete = new Conjunto<>();
        Conjunto<String> conjuntetete = new Conjunto<>();

        conjuntete.insertar("90", 10);
        conjuntete.insertar("91", 20);
        conjuntete.insertar("92", 30);
        conjuntete.insertar("93", 30);     // Al tener la misma clave, no deberia de insertarse.

        System.out.println(conjuntete.imprimir());
        System.out.println(conjuntete.imprimir(" -- "));

        conjuntetete.insertar("91", 20);
        conjuntetete.insertar("100", 45);

        System.out.println("\n");

        IConjunto<String> union = conjuntete.union(conjuntetete);
        IConjunto<String> interseccion = conjuntete.intersection(conjuntetete);

        System.out.println("Conjunto A: " + conjuntete.imprimir(", "));
        System.out.println("Conjunto B: " + conjuntetete.imprimir(", "));
        System.out.println("Unión: " + union.imprimir(", "));
        System.out.println("Intersección: " + interseccion.imprimir(", "));    // Funcionando joya.


    }
}