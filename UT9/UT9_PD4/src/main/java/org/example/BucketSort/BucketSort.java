package org.example.BucketSort;

import java.util.ArrayList;
import java.util.List;

public class BucketSort {

    /**
     * Ordena el arreglo usando Bucket Sort.
     * @param arr         el arreglo de doubles en [0,1)
     * @param numBuckets  número de cubetas a utilizar
     */
    public static void bucketSort(double[] arr, int numBuckets) {
        int n = arr.length;
        // Crear e inicializar cubetas
        List<Double>[] buckets = new ArrayList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Distribuyo cada valor a buckets [(int)(valor * numBuckets)]
        for (double v : arr) {
            int idx = (int) (v * numBuckets);
            // Por si las moscas v == 0:
            if (idx >= numBuckets) idx = numBuckets - 1;
            buckets[idx].add(v);
        }

        // Ordeno cada cubeta con InsertionSort:
        for (List<Double> bucket : buckets) {
            insertionSort(bucket);
        }

        // Concateno de vualnta en arr (el arreglo):
        int pos = 0;
        for (List<Double> bucket : buckets) {
            for (double v : bucket) {
                arr[pos++] = v;
            }
        }



    }

    /**
     * Insertion sort para listas de Double, estable y eficiente en listas pequeñas.
     */
    private static void insertionSort(List<Double> list) {
        for (int i = 1; i < list.size(); i++) {
            double key = list.get(i);
            int j = i - 1;
            // desplazar los mayores que key
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}
