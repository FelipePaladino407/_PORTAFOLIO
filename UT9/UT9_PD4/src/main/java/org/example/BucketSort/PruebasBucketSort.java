package org.example.BucketSort;

import static org.example.BucketSort.BucketSort.bucketSort;

public class PruebasBucketSort {
    public static void main(String[] args) {
        double[] data = {0.42, 0.32, 0.33, 0.52, 0.37, 0.47, 0.51, 0.99, 0.01, 0.15};
        System.out.print("Antes:  ");
        for (double v : data) System.out.printf("%.2f ", v);
        System.out.println();

        // Usamos, por ejemplo, 5 cubetas:
        bucketSort(data, 5);

        System.out.print("Después:");
        for (double v : data) System.out.printf(" %.2f", v);
        System.out.println();
    }
}

