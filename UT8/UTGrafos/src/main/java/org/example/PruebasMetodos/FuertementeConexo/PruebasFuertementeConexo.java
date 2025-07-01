package org.example.PruebasMetodos.FuertementeConexo;

import org.example.IGrafoDirigido;
import org.example.IGrafoNoDirigido;
import org.example.TGrafoDirigido;
import org.example.Utils.UtilGrafos;

public class PruebasFuertementeConexo {
    public static void main(String[] args) {

        IGrafoDirigido g = UtilGrafos.cargarGrafo("/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UTGrafos/src/main/java/org/example/PruebasMetodos/FuertementeConexo/vertices.txt",
                "/home/felipe/Documents/AED/_PORTAFOLIO/UT8/UTGrafos/src/main/java/org/example/PruebasMetodos/FuertementeConexo/aristas.txt",
                false, TGrafoDirigido.class);

        System.out.println(g.esFuertementeConexo());
    }
}
