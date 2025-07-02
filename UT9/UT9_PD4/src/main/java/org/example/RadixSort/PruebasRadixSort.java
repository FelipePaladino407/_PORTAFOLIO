package org.example.RadixSort;

import java.util.Arrays;

import static org.example.RadixSort.RadixSort.radixSort;

public class PruebasRadixSort {
    public static void main(String[] args) {
        int[] data = { 170, 45, 75, 90, 802, 24, 2, 66 };
        System.out.println("Antes:  " + Arrays.toString(data));
        radixSort(data);
        System.out.println("Después: " + Arrays.toString(data));
    }
}
