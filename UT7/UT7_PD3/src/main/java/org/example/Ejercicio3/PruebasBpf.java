package org.example.Ejercicio3;

import org.example.TGrafoDirigido;
import org.example.UtilGrafos;

public class PruebasBpf {
    public static void main(String[] args) {
        TGrafoDirigido grafito = UtilGrafos.cargarGrafo(
                "/home/felipe/Documents/AED/UT7_PD3/src/main/java/org/example/aereopuertos.txt",
                "/home/felipe/Documents/AED/UT7_PD3/src/main/java/org/example/conexiones.txt",
                false,
                TGrafoDirigido.class);

        grafito.bpf("Asuncion");
        System.out.println("================");
        System.out.println(grafito.obtenerTodosLosCaminos("Montevideo", "Porto_Alegre"));
        }
    }

