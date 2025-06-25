package org.example.PD5;

import org.example.TGrafoDirigido;
import org.example.UtilGrafos;

import java.util.List;

public class Pruebas_tieneCiclos {
    public static void main(String[] args) {
        TGrafoDirigido grafo = UtilGrafos.cargarGrafo(
                "/home/felipe/Documents/AED/UT7_PD5/src/main/java/org/example/aereopuertos.txt",
                "/home/felipe/Documents/AED/UT7_PD5/src/main/java/org/example/conexiones.txt",
                false,
                TGrafoDirigido.class

        );

        System.out.println(grafo.tieneCiclos());

}}
