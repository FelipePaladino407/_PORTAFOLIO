package org.example.HeapSort;

import java.util.Arrays;

import static org.example.HeapSort.HeapSort.heapSort;

public class PruebasHeapSort {
    public static void main(String[] args) {
        int[] data = {64, 25, 12, 22, 11, 90, 75, 66, 802, 2, 24};
        System.out.println("Antes:  " + Arrays.toString(data));
        heapSort(data);
        System.out.println("Después:" + Arrays.toString(data));
    }
}
