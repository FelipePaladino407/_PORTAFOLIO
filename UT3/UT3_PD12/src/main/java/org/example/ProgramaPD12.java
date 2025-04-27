package org.example;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProgramaPD12 {

        public static void main(String[] args) {
            try {
                // 1. Cargar cursos básicos desde archivos
                Conjunto basicoIng = new Conjunto();
                basicoIng.cargarDesdeArchivo("C:\\Users\\Estudiante UCU\\Documents\\prog3\\UT3_PD12\\src\\main\\java\\org\\example\\basico-ing.txt");

                Conjunto basicoEmp = new Conjunto();
                basicoEmp.cargarDesdeArchivo("C:\\Users\\Estudiante UCU\\Documents\\prog3\\UT3_PD12\\src\\main\\java\\org\\example\\basico-emp.txt");

                // 2. Crear cursos usando operaciones de conjunto
                Conjunto integrador101 = (Conjunto) basicoIng.union(basicoEmp);
                integrador101.guardarEnArchivo("C:\\Users\\Estudiante UCU\\Documents\\prog3\\UT3_PD12\\src\\main\\java\\org\\example\\Integrador101.txt");

                Conjunto exigente102 = (Conjunto) basicoIng.interseccion(basicoEmp);
                exigente102.guardarEnArchivo("C:\\Users\\Estudiante UCU\\Documents\\prog3\\UT3_PD12\\src\\main\\java\\org\\example\\Exigente102.txt");

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

