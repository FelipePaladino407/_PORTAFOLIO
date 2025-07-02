package org.example.RadixSort;

public class RadixSort {

    /**
     * Realiza Radix Sort (LSD) sobre un arreglo de enteros no negativos.
     * @param arr el arreglo de enteros a ordenar
     */
    public static void radixSort(int[] arr) {
        int n = arr.length;
        if (n == 0) return;

        // (1) Encontrar el valor máximo para saber cuántos dígitos tiene
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // (2) Hacer Counting Sort para cada dígito (unidades, decenas, centenas, …)
        //    exp = 1 → dígito de las unidades
        //    exp = 10 → dígito de las decenas
        //    exp = 100 → dígito de las centenas
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    /**
     * Counting Sort estable que ordena arr[] de acuerdo al dígito que
     * corresponde a exp (1 = unidades, 10 = decenas, ...).
     */
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];  // para dígitos 0..9

        // (1) Contar ocurrencias de cada dígito
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // (2) Transformar count[i] en posición acumulada
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // (3) Construir output recorriendo arr de derecha a izquierda (estabilidad)
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // (4) Copiar output[] de vuelta a arr[]:
        System.arraycopy(output, 0, arr, 0, n);
    }
}
