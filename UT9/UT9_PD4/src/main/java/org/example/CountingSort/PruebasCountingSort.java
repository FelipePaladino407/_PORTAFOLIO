package org.example.CountingSort;

import java.util.Arrays;

import static org.example.CountingSort.CountingSort.countingSort;

public class PruebasCountingSort {
    public static void main(String[] args) {
        int[] A = {4, 2, 2, 8, 3, 3, 1};
        int k = Arrays.stream(A).max().orElse(0);  // encontrar el máximo en A.
        System.out.println("Antes:  " + Arrays.toString(A));
        int[] sorted = countingSort(A, k);
        System.out.println("Después:" + Arrays.toString(sorted));
    }
}
