package org.example;

/**
 * Para UT9_PD3
 * Clase QuickSort, tomando la mediana de 3, metodo de el libro de Weiss.
 * Si el arreglo es muy corto, ni me molesto en QuickSort, hago InsertionSort, que es mas rapido, en ese caso.
 */
public class QuickSort {

    private static final int LIMITE = 5; // QuickSort o InsertionSort.

    public static void quickSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        quickSort(array, 0, array.length -1);
    }

    private static void quickSort(int[] array, int start, int end) {

        // Si el arreglo es pequenito, mejor uso InsertionSort, manejate:
        if (end - start + 1 <= LIMITE) {
            insertionSort(array, start, end);
            return;
        }

        int pivot = medianaDeTres(array, start, end);

        int i = start;
        int j = end - 1;

        while (i < j) {
            while (array[++i] < pivot) {/* avanza i */}
            while (array[--j] > pivot) {/* retrocede j */}
            if (i < j) {
                swap(array, i, j);
            }
        }
        // Coloco el pivotete en su lugarsete:
        swap(array, i, end -1);

        quickSort(array, start, i - 1);  // Ordena menor al pivot.
        quickSort(array, i + 1, end);   // Ordena mayor al pivot.


    }

    /**
     * Metodo de la mediana de 3, extraido del libro de Allan Weiss.
     * @param array
     * @param start
     * @param end
     * @return
     */
    private static int medianaDeTres(int[] array, int start, int end) {

        int middle = (start + end) >>> 1;

        if (array[start] > array[middle]) {swap(array, start, middle);}
        if (array[start] > array[end]) {swap(array, start, end);}
        if (array[middle] > array[end]) {swap(array, middle, end);}

        swap(array, middle, end - 1);
        return array[end - 1];

    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * El infame insertionSort. Big O(n**2) en el peor y caso promedio. Si da la casualidad
     * de que ya estaba ordenado o se aproxima a ello, se puede hacer en casi O(n).
     * @param array
     * @param start
     * @param end
     */
    private static void insertionSort(int[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int aux = array[i], j = i;
            while (j > start && array[j - 1] > aux) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = aux;
        }

    }
}
