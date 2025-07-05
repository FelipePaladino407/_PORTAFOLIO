package org.example.InsertionSort;

public class InsertionSort {

    public static void insertionSort(int[] V) {
        for (int i = 1; i < V.length; i++) {
            int aux = V[i];
            int j = i - 1;

            while(j >= 0 && V[j] > aux) {
                V[j+1] = V[j];
                j = j - 1;
            }
            V[j+1] = aux;
        }
    }
}
