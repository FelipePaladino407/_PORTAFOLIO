package org.example.Ejercicio2;

import java.util.HashMap;
import java.util.Map;

public class DarVuelta {

    public static Map<String, String> inverir(Map<String, String> original){
        Map<String, String> invertido = new HashMap<>();
        for (Map.Entry<String, String> entry : original.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            if (invertido.containsKey(value)) {
                throw new IllegalArgumentException("ATENCION!!!, valor duplicado: " + value);

            }
            invertido.put(value, key);
        }
        return invertido;

    }

    public static void main(String[] args) {
        Map<String, String> mapita = new HashMap<>();

        mapita.put("BMW", "330i");
        mapita.put("Mercedes Benz", "C350");
        mapita.put("Audi", "S5");
        mapita.put("Fiat", "500");
        // mapita.put("Abarth", "500");

        System.out.println("Mapa original: " + mapita + "\n");

        try{
            Map<String, String> invertido = inverir(mapita);
            System.out.println("Mapa invertido: " + invertido);

        }
        catch (IllegalArgumentException pepe){
            System.out.println("Error: " + pepe.getMessage());
        }


    }



}
