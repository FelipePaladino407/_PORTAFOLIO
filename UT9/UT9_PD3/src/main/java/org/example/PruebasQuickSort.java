package org.example;

import java.util.Arrays;

/**
 * Recordemos, por el amor de Dios, que si el arreglo tiene un LIMITE = 5 o menos, entonces, usa
 * InsertionSort, pues es mas eficiente. De hecho, En Java moderno, el sort interno, que utiliza
 * QuickSort, si la cantidad de elementos es < 47, usa InsertionSort, Pode crer?
 * Evidentemente, yo lo acorto a 5, asi podemos ver QuickSort y no el insertion.
 */
public class PruebasQuickSort {
    public static void main(String[] args) {

        int datos[] = {44, 55, 12, 42, 94, 18, 6, 67};
        System.out.println("Antes de ordenar: " + Arrays.toString(datos));
        QuickSort.quickSort(datos);

        if (datos.length > 5) {
            System.out.println("Ordenado Correctamente con QuickSort: " + Arrays.toString(datos));
        }
        else{
            System.out.println("Ordenado Correctamente con InsertionSort: " + Arrays.toString(datos));
        }
    }
}
