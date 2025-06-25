package org.example.PD7;
import org.example.*;
import java.util.*;

public class Proyecto {
    public static void main(String[] args) {

        String[] lineasTareas = ManejadorArchivosGenerico.leerArchivo("/home/felipe/Documents/AED/UT7/UT7_PD7/src/main/java/org/example/PD7/tareas.txt", true);
        String[] lineasAristas = ManejadorArchivosGenerico.leerArchivo("/home/felipe/Documents/AED/UT7/UT7_PD7/src/main/java/org/example/PD7/precedesncias.txt", true);

        List<IVertice> listaVertices = new LinkedList<>();
        for (String linea : lineasTareas) {
            String[] partes = linea.split(",");
            if (partes.length >= 2) {
                String cod = partes[0].trim();
                int tiempo = Integer.parseInt(partes[1].trim());
                Tarea tarea = new Tarea(cod, tiempo);
                TVertice<Tarea> vertice = new TVertice<>(cod);
                vertice.setDatos(tarea);
                listaVertices.add(vertice);
            }
        }

        List<IArista> listaAristas = new LinkedList<>();
        for (String linea : lineasAristas) {
            String[] partes = linea.split(",");
            if (partes.length >= 2) {
                listaAristas.add(new TArista(partes[0].trim(), partes[1].trim(), 1)); // el costo no importa acá
            }
        }

        TGrafoDirigido grafo = new TGrafoDirigido(listaVertices, listaAristas);

        LinkedList<Tarea> orden = grafo.ordenParcial();

        grafo.listarTareas(orden);

        guardarOrdenEnArchivo(orden, "orden.txt");
    }

    public static void guardarOrdenEnArchivo(LinkedList<Tarea> orden, String archivoSalida) {
        List<String> lineas = new ArrayList<>();
        for (Tarea tarea : orden) {
            lineas.add(tarea.getCodigo());
        }
        ManejadorArchivosGenerico.escribirArchivo(archivoSalida, lineas.toArray(new String[0]));
    }
}



