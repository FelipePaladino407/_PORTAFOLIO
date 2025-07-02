package org.example.SelectionSort;

import java.util.Arrays;
import org.example.SelectionSort.SelectionSort;

import static org.example.SelectionSort.SelectionSort.selectionSort;

public class PruebasSelectionSort {
    public static void main(String[] args) {

        int[] arrey = {2, 5, 19, 15, 7, 1, 0, 20, 16};

        System.out.println("Arreglo base: " + Arrays.toString(arrey));
        selectionSort(arrey);
        System.out.println("Ordenado con selectionSort: " + Arrays.toString(arrey));
    }
}
