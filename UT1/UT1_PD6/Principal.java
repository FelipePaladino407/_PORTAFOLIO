package org.example.PD6;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {

    /**
     * EJERCICIO #1 - BUCLES ANIDADOS
     */
    public static void imprimirTablero(int largo, int ancho)
    {
       for (int i = 0; i < largo; i++) {
           for (int j = 0; j < ancho; j++) {
               System.out.print("#");
           }
           System.out.print("\n");
       }
    }

    /**
     * EJERCICIO #2 - EMTRADA DE DATOS
     */
    public static void leerEntradaArchivo(String ruta) {
        FileReader fr = null;
        ArrayList<String> lista = new ArrayList<>();

        try {
            fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();

            /*
             * Mientras no haya llegado al fin del archivo,
             * lee y agrega la línea al array
             */
            while (lineaActual != null) {
                lista.add(lineaActual);
                lineaActual = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            int entero =  Integer.valueOf(lista.get(0));
            float flotante = Float.valueOf(lista.get(1));
            String cadena = lista.get(2);

            System.out.println("El entero es: " + entero);
            System.out.println("El float es: " + flotante);
            System.out.println("El String es: " + cadena);

            System.out.println(String.format("¡Hola %s!, la suma de %d y %f es %s", cadena, entero, flotante, entero+flotante));

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }


    }

    public static void leerEntradaStdin() {
        Scanner scanner = new Scanner(System.in);

        try {
            double radio = Double.parseDouble(scanner.nextLine());

            double area = Math.PI * Math.pow(radio, 2);
            double perimetro = 2 * Math.PI * radio;

            System.out.println("El area es: " + area);
            System.out.println("El perimetro es: " + perimetro);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * EJERCICIO 3 - ENTRADA DE DATOS Y MANEJO DE STRINGS
     */
    public static void transformarTextoT9(String rutaArchivo) {
        String contenido = leerArchivo(rutaArchivo).toUpperCase();

        String transformado = codigoT9(contenido);

        escribirArchivo(transformado, "salida.txt");
    }

    public static void transformarT9Texto(String rutaArchivo) {
        String contenido = leerArchivo(rutaArchivo).toUpperCase();
        String textoInvertido = new StringBuilder(contenido).reverse().toString();

        String transformado = codigoT9(textoInvertido);

        escribirArchivo(transformado, "salida.txt");

    }

    private static String codigoT9(String contenido) {
        String t9 = " .ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder codigoTexto = new StringBuilder();

        for (char i : contenido.toCharArray()) {
            String resultante = "";
            int indice = t9.indexOf(i);
            if (indice < 3) {
                codigoTexto.append(indice);
                continue;
            }
            // Calculo mágico que salio de mi cabeza a las 4am
            double resultado = (indice / 3.0 + 1);
            int resultadoEntero = (int) resultado;

            if (resultado > resultadoEntero) {
                resultante = String.valueOf(resultadoEntero).repeat(3);
            } else if (resultado < resultadoEntero) {
                resultante = String.valueOf(resultadoEntero);
            } else {
                resultante = String.valueOf(resultadoEntero).repeat(2);
            }
            codigoTexto.append(resultante);
        }
        return codigoTexto.toString();
    }

    /**
     * EJERCICIO 4 - ARRAYS
     */
    public static int multiplicarVectores(int[] vector1, int[] vector2) {

        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException();
        }

        int resultado = 0;
        for (int i = 0; i < vector1.length; i++) {
            resultado += vector1[i] * vector2[i];
        }

        return resultado;

        /**
         * DESCRIPCIÓN EN LENGUAJE NATURAL:
         * Para que dos vectores se puedan multiplicar, deben tener la misma longitud,
         * y esa es la primera condición que examina el método. El requisito de no devolver un resultado si
         * la multiplicación no se puede dar se alcanza estableciendo una excepción, sin devolver nada.
         * Posteriormente se itera n veces (siendo n la longitud de los vectores), y en cada iteración se multiplican
         * los correspondientes elementos de cada vector, agregandose el resultado a la variable previamente declarada.
         * Al final devuelve un int con el resultado
         */
    }


    /**
     * METODOS AUXILIARES
     */
    private static String leerArchivo(String ruta) {
        FileReader fr = null;
        StringBuilder texto = new StringBuilder();

        try {
            fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();

            /*
             * Mientras no haya llegado al fin del archivo,
             * lee y agrega la línea al array
             */
            while (lineaActual != null) {
                texto.append(lineaActual);
                lineaActual = br.readLine();
            }

            return texto.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirArchivo(String contenido, String rutaSalida) {
        try
         {
            BufferedWriter writer = new BufferedWriter(new FileWriter(rutaSalida));
            writer.write(contenido);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        imprimirTablero(7, 7);
//        leerEntradaArchivo(args[0]);
//        leerEntradaStdin();
//        transformarTextoT9(args[0]);
//        transformarT9Texto(args[0]);
        int[] v1 = {2, 3, 4};
        int[] v2 = {4, 5, 6};
        System.out.println(multiplicarVectores(v1, v2));
    }
}
