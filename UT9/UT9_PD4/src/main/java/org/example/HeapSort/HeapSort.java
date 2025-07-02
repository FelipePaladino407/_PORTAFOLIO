package org.example.HeapSort;

public class HeapSort {

    /**
     * Ordena el arreglo arr[] en orden ascendente usando Heap Sort in‑place.
     * @param arr el arreglo de enteros a ordenar
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // (1) Construir el heap máximo (max‑heap) en arr[0..n-1]
        //    Partimos desde el último padre: índice (n/2 - 1)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }

        // (2) Extraer el máximo uno a uno y reajustar heap
        for (int end = n - 1; end > 0; end--) {
            // Llevar el máximo (arr[0]) al final:
            swap(arr, 0, end);
            // Restaurar heap en arr[0..end-1]:
            heapify(arr, 0, end);
        }
    }

    /**
     * Desciende el elemento en raíz hasta restaurar la propiedad de max‑heap
     * en el subarreglo arr[root..end-1].
     * @param arr  el arreglo completo
     * @param root índice de la raíz del sub‑heap
     * @param end  tamaño del heap (elementos válidos en [0..end-1])
     */
    private static void heapify(int[] arr, int root, int end) {
        while (true) {
            int leftChild = 2 * root + 1;
            if (leftChild >= end) {
                // No hay hijos, terminamos
                break;
            }
            // Elegir el hijo mayor
            int largerChild = leftChild;
            int rightChild = leftChild + 1;
            if (rightChild < end && arr[rightChild] > arr[leftChild]) {
                largerChild = rightChild;
            }
            // Si el padre ya es mayor o igual, el heap está bien
            if (arr[root] >= arr[largerChild]) {
                break;
            }
            // Si no, swap y continuar descendiendo
            swap(arr, root, largerChild);
            root = largerChild;
        }
    }

    /** Intercambia arr[i] y arr[j]. */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
