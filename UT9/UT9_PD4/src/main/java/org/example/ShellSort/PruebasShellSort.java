package org.example.ShellSort;

import static org.example.ShellSort.ShellSort.shellSort;

public class PruebasShellSort
{
    public static void main(String[] args) {
    int[] datos = { 64, 25, 12, 22, 11, 90, 75, 66, 802, 2, 24 };
    System.out.println("Antes: " + java.util.Arrays.toString(datos));
    shellSort(datos);
    System.out.println("Después: " + java.util.Arrays.toString(datos));
}
}

