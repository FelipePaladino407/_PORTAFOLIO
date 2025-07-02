package org.example.ShellSort;

public class ShellSort {

    /**
     * Ordena el arreglo usando Shell Sort con incrementos de Sedgwick.
     * @param arr el arreglo de enteros a ordenar
     */
    public static void shellSort(int[] arr) {
        int n = arr.length;
        // Secuencia de Sedgwick (ordenada de mayor a menor, terminando en 1)
        int[] sedgwickGaps = {109, 41, 19, 5, 1};

        for (int gap : sedgwickGaps) {
            if (gap >= n) {
                // Si el salto es mayor al tamaño, lo saltamos
                continue;
            }
            // Insertion sort "h‑secuencial"
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                // desplazar elementos mayores que temp hacia la derecha
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }
}
