package org.example;

import java.util.Arrays;

import static org.example.InsertionSort.insertionSort;

public class PruebasInsertionSort {
    public static void main(String[] args) {

        int[] lista = {3, 9, 0, 15, 10, 11, 1, 2, 4};
        insertionSort(lista);
        System.out.println(Arrays.toString(lista));
    }
}
