package org.example.UT1_TA4;

import java.util.ArrayList;

public class ContadorPalabras {

    /**
     * El método palabrasComunes recibe dos array de Strings.
     * Al principio se inicializa un ArrayList para guardar las palabras comunes
     * para llevar a cabo esta tarea itera sobre cada palabra del primer array
     * en cada iteración recorre las palabras del segundo array en busca de una
     * coincidencia. En caso de encontrar una, agrega la palabra al ArrayList.
     * Luego devuelve el arrayList convertido a Array.
     */

    public String[] palabrasComunes(String[] palabras1, String[] palabras2)
    {
        ArrayList<String> reps = new ArrayList<>();

        for (String p1 : palabras1) {
            for (String p2 : palabras2) {
                if ((p1.equals(p2)) && (esPalabra(p1)) && (!reps.contains(p1))) {
                    reps.add(p1);
                }
            }
        }

        return reps.toArray(new String[reps.size()]);
    }

    private boolean esPalabra(String palabra) {
        for (int i = 0; i < palabra.length(); i++) {
            if (Character.isLetter(palabra.charAt(0))) {
                return true;
            }
        }
        return false;
    }
}


