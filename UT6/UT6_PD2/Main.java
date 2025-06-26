package org.example.UT6.PD2;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Factor  |  Prom Insert  |  Prom Busq Exit | Prom Busq Sin Exito");

        double[] factores = {0.70, 0.75, 0.80, 0.85, 0.90, 0.91, 0.92, 0.93, 0.94, 0.95, 0.96, 0.97, 0.98, 0.99};

        for (double factor : factores) {
            evaluarFactor(factor);
        }
    }

    static void evaluarFactor(double factor) {
        THash<Integer, String> tabla = new THash<>(100);
        Random random = new Random();

        // llenar tablita hasta que explote
        int elementosObjetivo = (int)(100 * factor);
        int[] clavesInsertadas = new int[elementosObjetivo];
        int totalCompInsert = 0;
        int insertados = 0;

        while (insertados < elementosObjetivo) {
            int clave = random.nextInt(10000);
            int comparaciones = tabla.insertarComparaciones(clave, "valor");

            if (tabla.getFactorCarga() > (double)insertados / 100) {
                clavesInsertadas[insertados] = clave;
                totalCompInsert += comparaciones;
                insertados++;
            }
        }

        double promInsert = (double)totalCompInsert / insertados;

        int totalCompExitosa = 0;
        for (int i = 0; i < 50; i++) {
            int indiceAleatorio = random.nextInt(insertados);
            int clave = clavesInsertadas[indiceAleatorio];
            totalCompExitosa += tabla.buscarComparaciones(clave);
        }
        double promExitosa = (double)totalCompExitosa / 50;

        int totalCompSinExito = 0;
        for (int i = 0; i < 50; i++) {
            int clave = random.nextInt(10000) + 20000; // Clave que no existe
            totalCompSinExito += tabla.buscarComparaciones(clave);
        }
        double promSinExito = (double)totalCompSinExito / 50;

        System.out.printf("     %.0f%%    |    %.2f     |       %.2f        |       %.2f\n",
                factor * 100, promInsert, promExitosa, promSinExito);
    }

}