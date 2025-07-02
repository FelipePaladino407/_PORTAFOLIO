package org.example.CountingSort;

import java.util.Arrays;

public class CountingSort {

    /**
     * Realiza Counting Sort sobre el arreglo A, con claves en el rango [0..k].
     * @param A el arreglo de entrada (no se modifica)
     * @param k el valor máximo posible en A (las claves deben estar en 0..k)
     * @return un nuevo arreglo con los mismos elementos de A, pero ordenados
     */
    public static int[] countingSort(int[] A, int k) {
        int n = A.length;
        int[] C = new int[k + 1];   // arreglo de conteos
        int[] B = new int[n];       // arreglo de salida

        // (1) Contar ocurrencias
        for (int j : A) {
            C[j]++;
        }

        // (2) Acumular para posiciones finales
        for (int i = 1; i <= k; i++) {
            C[i] += C[i - 1];
        }

        // (3) Construir B de derecha a izquierda para mantener estabilidad
        for (int i = n - 1; i >= 0; i--) {
            int key = A[i];
            B[ C[key] - 1 ] = key;
            C[key]--;
        }

        return B;
    }


    /**
     * for (int i = 0; i < n; i++) {    Del parte 1), pero sin for mejorado.
     *             C[A[i]]++;
     *         }
     */
}