package org.example;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Conjunto.java
import java.util.*;
import java.io.*;

public class Conjunto implements IConjunto {
    private Set<String> elementos;

    public Conjunto() {
        elementos = new HashSet<>();
    }

    // Va a cargar datos del archivo (formato: "ALUMNO / FECHA")
    public void cargarDesdeArchivo(String nombreArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String alumno = linea.split("/")[0].trim();
                elementos.add(alumno);  // HashSet ignora duplicados automáticamente.
            }
        }
    }

    // Guarda datettes en archivo, le tenes que poner un nombre a un archivo tipo file.
    public void guardarEnArchivo(String nombreArchivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String alumno : elementos) {
                bw.write(alumno + " / " + java.time.LocalDate.now());
                bw.newLine();
            }
        }
    }


    public Set<String> getElementos() {
        return elementos;
    }

    @Override
    public Conjunto union(Conjunto otroConjunto) {
        Conjunto resultado = new Conjunto();
        resultado.elementos.addAll(this.elementos);
        resultado.elementos.addAll(((Conjunto) otroConjunto).elementos); // AddAll ignora duplicados
        return resultado;
    }

    @Override
    public Conjunto interseccion(Conjunto otroConjunto) {
        Conjunto resultado = new Conjunto();
        for (String elemento : elementos) {
            if (otroConjunto.elementos.contains(elemento)) {
                resultado.elementos.add(elemento);
            }
        }
        return resultado;
    }

}