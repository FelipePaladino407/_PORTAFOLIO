package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio1 {
    public static void main(String[] args) {

        List<TVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice("Artigas"));
        vertices.add(new TVertice("Canelones"));
        vertices.add(new TVertice("Durazno"));
        vertices.add(new TVertice("Florida"));
        vertices.add(new TVertice("Montevideo"));
        vertices.add(new TVertice("Punta del Este"));
        vertices.add(new TVertice("Rocha"));
        vertices.add(new TVertice("Colonia"));

        List<TArista> aristas = new ArrayList<>();
        aristas.add(new TArista("Artigas", "Rocha", 400));
        aristas.add(new TArista("Canelones", "Artigas", 500));
        aristas.add(new TArista("Canelones", "Colonia", 200));
        aristas.add(new TArista("Canelones", "Durazno", 170));
        aristas.add(new TArista("Canelones", "Punta del Este", 90));
        aristas.add(new TArista("Colonia", "Montevideo", 180));
        aristas.add(new TArista("Florida", "Durazno", 60));
        aristas.add(new TArista("Montevideo", "Artigas", 700));
        aristas.add(new TArista("Montevideo", "Canelones", 30));
        aristas.add(new TArista("Montevideo", "Punta del Este", 130));
        aristas.add(new TArista("Punta del Este", "Rocha", 90));
        aristas.add(new TArista("Rocha", "Montevideo", 270));
        aristas.add(new TArista("Florida", "Durazno", 60));


        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(grafo.getVertices());

        UtilGrafos.imprimirMatrizMejorado(matrizCostos, grafo.getVertices(), "Matriz de Adyacencia");
    }
}
