package org.example.BubbleSort;

public class BubbleSort {

    /**
     * Ordena el arreglo arr[] en orden ascendente usando Bubble Sort.
     * En este caso es un bubbleSort con bandera.
     * @param arr el arreglo de enteros a ordenar
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        // Recorremos el arreglo n-1 veces
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // En cada pasada, el elemento más grande "burbujea" al final:
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            // Si en una pasada no hubo ningún intercambio, el arreglo ya está ordenado
            if (!swapped) {
                break;
            }
        }
    }

    /** Intercambia arr[i] y arr[j]. */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
