package uy.edu.ucu.aed;

import java.util.LinkedList;

public class Parcial2
{    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // 1 - Cargar el Grafo
        TGrafoRedDatos grafo = UtilGrafos.cargarGrafo("/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Segundo parcial 2023/parcial2/parcial2/src/main/dispositivos.txt",
                "/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Segundo parcial 2023/parcial2/parcial2/src/main/conexiones.txt",
                false, TGrafoRedDatos.class);

        // 2 - Verificar que los componentes se encuentren conectados
        //boolean conectados = grafo.conectados("nodoX","nodoY");

        IVertice nodoX = grafo.getVertices().get("CS10");
        IVertice nodoY = grafo.getVertices().get("CS60");

        IVertice nodoA = grafo.getVertices().get("CS30");
        IVertice nodoB = grafo.getVertices().get("CS80");

        IVertice nodoP = grafo.getVertices().get("CS90");

        boolean conectado = grafo.conectados(nodoX, nodoY);
        boolean conectado2 = grafo.conectados(nodoA, nodoB);
        boolean conectado3 = grafo.conectados(nodoB, nodoP);
        System.out.println(conectado);
        System.out.println(conectado2);
        System.out.println(conectado3);



}
}
