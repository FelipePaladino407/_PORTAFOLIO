package org.example.BubbleSort;

import java.util.Arrays;

import static org.example.BubbleSort.BubbleSort.bubbleSort;

public class PruebasBubbleSort {
    public static void main(String[] args) {
        int[] data = {64, 25, 12, 22, 11, 90, 75, 66};
        System.out.println("Antes, previo de la burbujeada:  " + Arrays.toString(data));
        bubbleSort(data);
        System.out.println("Después, ordenado con la infame burbuja: " + Arrays.toString(data));
    }
}
