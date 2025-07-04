package uy.edu.ucu.aed;

import java.util.LinkedList;

public class Parcial2
{    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String dispositivos = "/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Segundo parcial 2023 - 2nd intento/parcial2/parcial2/src/main/dispositivos.txt";
        String conexiones = "/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Segundo parcial 2023 - 2nd intento/parcial2/parcial2/src/main/conexiones.txt";
        String mediciones = "/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Segundo parcial 2023 - 2nd intento/parcial2/parcial2/src/main/mediciones.txt";

        // 1 - Cargar el Grafo
        TGrafoRedDatos grafo = UtilGrafos.cargarGrafo(dispositivos, conexiones, false, TGrafoRedDatos.class);

        // 2 - Verificar que los componentes se encuentren conectados
        //boolean conectados = grafo.conectados("nodoX","nodoY");
        boolean conectados = grafo.conectados("CS10", "CS60");
        boolean conectados2 = grafo.conectados("CS30", "CS80");
        boolean conectados3 = grafo.conectados("CS80", "CS90");

        if (!conectados3){
            System.out.println("Ambos vertices estan conectados");
        }
        else{
            System.out.println("No estan conectados.");
        }
        
        // 3 - Leer y cargar archivo mediciones.txt
        TDato[] datos = Parcial2.cargarMediciones(mediciones);
        
        // 4 - Obtener dato de mayor medicion.
        TMedidor medidor = new TMedidor();
        TDato mayorMedicion = medidor.obtenerMayorMedicion(datos);
        
        // 5 - Emitir archivo de salida salida.txt
        // COMPLETAR CÓDIGO
        // ManejadorArchivosGenerico.escribirArchivo("src/main/java/salida.txt", vector con lineas del archivo);

        String[] salida = new String[1];
        salida[0] = String.format("Mayor medición: %.2f - Fecha: %d", mayorMedicion.getValor(),
                mayorMedicion.getFecha());

        String output = "/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Segundo parcial 2023 - 2nd intento/parcial2/parcial2/src/main/salida.txt";
        ManejadorArchivosGenerico.escribirArchivo(output, salida);
    }

    private static TDato[] cargarMediciones(String rutaAlArchivo) {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(rutaAlArchivo, false);
        
        TDato[] mediciones = new TDato[lineas.length];
        for (int i = 0; i < lineas.length; i++) {
            String[] datos = lineas[i].split(",");
            mediciones[i] = new TDato(Double.parseDouble(datos[1]), Integer.parseInt(datos[0]));
        }

        return mediciones;
    }
}
