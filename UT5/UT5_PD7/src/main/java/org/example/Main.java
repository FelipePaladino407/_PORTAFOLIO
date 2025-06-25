package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        TArbolTrieTelefonos trieAbonados = new TArbolTrieTelefonos();

        String[] lineasAbonados = ManejadorArchivosGenerico.leerArchivo("abonados.txt");
        for (String linea : lineasAbonados) {
            String[] partes = linea.split(",", 2);
            if (partes.length == 2) {
                String telefono = partes[0];
                String nombre = partes[1];
                TAbonado abonado = new TAbonado(nombre, telefono);
                trieAbonados.insertar(abonado);
            }
        }


        String[] lineasCodigos = ManejadorArchivosGenerico.leerArchivo("codigos1.txt");
        String codigoPais = "";
        String codigoArea = "";
        for (String linea : lineasCodigos) {
            if (linea.toUpperCase().contains("PAIS")) {
                codigoPais = linea.replaceAll("[^0-9]", ""); // solo dígitos
            } else if (linea.toUpperCase().contains("AREA")) {
                codigoArea = linea.replaceAll("[^0-9]", ""); // solo dígitos
            }
        }


        LinkedList<TAbonado> resultados = trieAbonados.buscarTelefonos(codigoPais, codigoArea);


        String[] salida = new String[resultados.size()];
        int i = 0;
        for (TAbonado abonado : resultados) {
            salida[i++] = abonado.getNombre() + "," + abonado.getTelefono();
        }

        ManejadorArchivosGenerico.escribirArchivo("salida.txt", salida);

        System.out.println("Búsqueda terminada. Resultados escritos en salida.txt");
    }
}
