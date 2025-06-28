package uy.edu.ucu.aed;

public class P2 {
    public static void main(String[] args)
    {


        // 3 - Leer y cargar archivo mediciones.txt
        TDato[] datos = P2.cargarMediciones("/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Segundo parcial 2023/parcial2/parcial2/src/main/mediciones.txt");

        // 4 - Obtener dato de mayor medicion.
        TMedidor medidor = new TMedidor();
        TDato mayorMedicion = medidor.obtenerMayorMedicion(datos);

        // 5 - Emitir archivo de salida salida.txt
        // COMPLETAR CÓDIGO
        // ManejadorArchivosGenerico.escribirArchivo("src/main/java/salida.txt", vector con lineas del archivo);

        String[] salida = new String[1];
        salida[0] = String.format("Mayor medición: %.2f - Fecha: %d", mayorMedicion.getValor(), mayorMedicion.getFecha());
        ManejadorArchivosGenerico.escribirArchivo("/home/felipe/Documents/AED/_PORTAFOLIO/Parciales/Segundo parcial 2023/parcial2/parcial2/src/main/salida.txt", salida);
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

