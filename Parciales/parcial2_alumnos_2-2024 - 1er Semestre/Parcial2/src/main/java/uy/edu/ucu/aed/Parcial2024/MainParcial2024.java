package uy.edu.ucu.aed.Parcial2024;

import java.util.List;

public class MainParcial2024 {
    public static void main(String[] args) {

        SistemaTransporte sistema = new SistemaTransporte();
        sistema.agregarRuta("A", "B", 5);
        sistema.agregarRuta("A", "C", 10);
        sistema.agregarRuta("B", "C", 2);
        sistema.agregarRuta("B", "D", 4);
        sistema.agregarRuta("C", "D", 1);

        int tiempo = sistema.consultaTiempoMinimo("A", "C");
        System.out.println("Tiempo minimo entre A y D: " + tiempo);

        /**
         * Problema 2.
         */
        List<SistemaTransporte.RutaConectada> red = sistema.redDeMantenimiento();

        System.out.println("Red de mantenimiento: ");
        for (SistemaTransporte.RutaConectada ruta : red) {
            System.out.println("Origen: " + ruta.origen + " Destino: "
                    + ruta.destino + " || Duracion: " + ruta.tiempo + " minutos.");
        }
    }
}
