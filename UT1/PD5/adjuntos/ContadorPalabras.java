package org.example.UT1_TA3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class ContadorPalabras {

    /**
     * Cuenta el número de vocales, consonantes y palabras en la frase.
     */
    public int[] contarPalabras(String frase) {
        int consonantes = 0;
        int vocales = 0;
        int contador = 0;

        for (int i = 0; i < frase.length(); i++) /* i++ incrementa cuando termina */ {
            char ch = frase.charAt(i);
            Letra letra = Letra.getTipo(ch);

            if (Character.isLetter(ch)){
                switch (letra) {
                    case VOCAL -> vocales++;
                    case CONSONANTE -> consonantes++;
                }
                contador++;
            }
        }
        return new int[]{vocales, consonantes, contador};
    }

    /**
     * Lee un archivo especificado y agrega el contenido de cada línea a un array de Strings.
     */
    public String[] obtenerLineas(String ruta) {
        FileReader fr = null;
        ArrayList<String> lista = new ArrayList<>();
        int lineasLeidas = 0;

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
                lineasLeidas++;
            }
            return lista.toArray(new String[lista.size()]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recibe un array de Strings y cuenta cuántas son palabras
     */
    public int cantPalabras(String[] palabras) {
        int contador = 0;
        for (String palabra : palabras) {
            if (esPalabra(palabra)) {
               contador++;
            }
        }
        return contador;
    }

    /**
     * Obtiene la cantidad de palabras con largo mayor al especificado en el parámetro 'largo'
     */
    public int largoPalabra(String frase, int largo) {
        int contador = 0;
        int longitudPalabra = 0;
        boolean isPalabra = false;

        for (int i = 0; i < frase.length(); i++) {
            char letra = frase.charAt(i);
            if (Character.isLetter(letra) || (isPalabra && Character.isDigit(letra))) {
                if (!isPalabra) {
                    isPalabra = true;
                }
                longitudPalabra++;
            } else {
                if (isPalabra && longitudPalabra > largo) {
                    contador++;
                }
                longitudPalabra = 0;
                isPalabra = false;

            }
        }
    if (isPalabra && longitudPalabra > largo) {
        contador++;
    }
    return contador;
    }


    /**
     * Recibe dos array de palabras(Strings). Itera sobre un
     */
    public String[] palabrasComunes(String[] palabras1, String[] palabras2)
    {
        ArrayList<String> reps = new ArrayList<>();

        for (String p1 : palabras1) {
            for (String p2 : palabras2) {
                if ((p1.equals(p2)) && (esPalabra(p1)) && (!reps.contains(p1))) {
                    reps.add(p1);
                }
            }
        }

        return reps.toArray(new String[reps.size()]);
    }

    /**
     * Método auxiliar
     */
    private boolean esPalabra(String palabra) {
        for (int i =0; i < palabra.length(); i++) {
            if (Character.isLetter(palabra.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método auxiliar. Recibe un vector de Strings y
     * separa las palabras por espacio
     */
    private String[] separarLineas(String[] lista) {
        ArrayList<String> listaSeparadas = new ArrayList<>();

        for (String linea : lista) {
            String[] lineaSep = linea.split(" ");
            listaSeparadas.addAll(Arrays.asList(lineaSep));
        }
        return listaSeparadas.toArray(new String[listaSeparadas.size()]);
    }
}