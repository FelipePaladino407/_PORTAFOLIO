package org.example;

/**
 * Clase extra, mas irrelevante, nada mas para probar lo de del nivel maximo nivel de profundidad
 * recursiva alcanzada en quickSort.
 * Que, escencialmente, es O(n) si elegis un mal pivote (inicio o final del arreglo), o log n en el mejor caso
 * donde tendrias subarreglos de tamanio n/2 aproximadamente, en vez de n.
 */
public class QuickSortWithDepth {

    // Variable estática para rastrear la máxima profundidad:
    private static int maxDepth;

    public static void quickSort(int[] array) {
        if (array == null || array.length < 2) return;
        maxDepth = 0;
        // Arrancamos la recursión en profundidad 1:
        quickSort(array, 0, array.length - 1, 1);

        System.out.println("Profundidad máxima del infame QuickSort: " + maxDepth);
    }

    // Ahora recibio un parametro 'depth' que incrementamos en cada llamada:
    private static void quickSort(int[] array, int start, int end, int depth) {
        // Actualizamos maxDepth si esta llamada esta más profunda
        if (depth > maxDepth) {
            maxDepth = depth;
        }

        final int UMBRAL = 5;
        if (end - start + 1 <= UMBRAL) {
            insertionSort(array, start, end);
            return;
        }

        // Mediana de tres y particion…
        int pivot = medianaDeTres(array, start, end);
        int i = start, j = end - 1;
        while (i < j) {
            while (array[++i] < pivot) { }
            while (array[--j] > pivot) { }
            if (i < j) {
                swap(array, i, j);
            }
        }
        swap(array, i, end - 1);

        // Acua pasamos depth+1 a las llamadas hijas:
        quickSort(array, start,   i - 1, depth + 1);
        quickSort(array, i + 1,   end,   depth + 1);
    }

    private static int medianaDeTres(int[] a, int lo, int hi) {
        int mid = (lo + hi) >>> 1;
        if (a[lo] > a[mid])  swap(a, lo, mid);
        if (a[lo] > a[hi])   swap(a, lo, hi);
        if (a[mid] > a[hi])  swap(a, mid, hi);
        swap(a, mid, hi - 1);
        return a[hi - 1];
    }

    private static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int tmp = a[i], j = i;
            while (j > lo && a[j - 1] > tmp) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = tmp;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
}
