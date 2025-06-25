// Main.java
package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();

        // Ejercicio #1: procesar altas.txt
        double totalCompras = procesarAltas("altas.txt", almacen);
        System.out.printf("Monto total gastado en compras: %.2f%n", totalCompras);

        // Ejercicio #2: procesar ventas.txt
        double totalVentas = procesarVentas("ventas.txt", almacen);
        System.out.printf("Monto total vendido: %.2f%n", totalVentas);

        // Listado final de productos ordenados
        System.out.println("\nProductos registrados (ordenados por nombre):");
        List<Producto> listado = almacen.listarOrdenadosPorNombre();
        for (Producto p : listado) {
            System.out.printf("%s (Código: %s) - Stock: %d%n",
                    p.getDescripcion(), p.getCodigo(), p.getCantidad());
        }
    }

    private static double procesarAltas(String archivo, Almacen almacen) {
        double total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                String codigo = campos[0].trim();
                String desc   = campos[1].trim();
                double precio = Double.parseDouble(campos[2].trim());
                int cantidad  = Integer.parseInt(campos[3].trim());
                total += almacen.incorporar(codigo, desc, precio, cantidad);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo altas.txt: " + e.getMessage());
        }
        return total;
    }

    private static double procesarVentas(String archivo, Almacen almacen) {
        double total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                String codigo = campos[0].trim();
                int cantidad  = Integer.parseInt(campos[1].trim());
                total += almacen.vender(codigo, cantidad);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo ventas.txt: " + e.getMessage());
        }
        return total;
    }
}
