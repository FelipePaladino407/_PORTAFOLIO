package org.example.Ejercicio1;

import java.util.HashMap;

public class LimpiarMapa {
    public static void main(String[] args) {
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("huevos", "100");
        mapa.put("huevos cocidos", null);
        mapa.put("huevos fritos", "115");
        mapa.put("huevos revueltos", null);

        System.out.println("Mapa con valores null: " + "\n" + mapa);

        // Aca extermino todas las claves con valor nulo.
        mapa.entrySet().removeIf(entry -> entry.getValue() == null);

        System.out.println("\n" + "Mapa sin valores null: " + "\n" + mapa);

    }
}
