package org.example;

public class PruebasQuickSortConProfundidad {
    public static void main(String[] args) {
        int[] datos = {44, 55, 12, 42, 94, 18, 6, 67};
        System.out.println("Antes de ordenar: " + java.util.Arrays.toString(datos));
        QuickSortWithDepth.quickSort(datos);
        System.out.println("Después:          " + java.util.Arrays.toString(datos));
    }
}

