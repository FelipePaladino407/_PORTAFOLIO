// Programa.java
package org.example;

public class Programa {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java org.example.Programa <idSuero> <idFarmaco1> [idFarmaco2] …");
            return;
        }

        int idSuero = Integer.parseInt(args[0]);
        Lista<Integer> consulta = new Lista<>();
        for (int i = 1; i < args.length; i++) {
            int idF = Integer.parseInt(args[i]);
            consulta.insertar(new Nodo<>(idF, idF));
        }

        UtilFarmacos uf = new UtilFarmacos();

        // Imprimo suero
        Suero s = uf.obtenerSuero(idSuero);
        if (s == null) {
            System.out.println("Suero no válido: " + idSuero);
            return;
        }
        System.out.printf("Suero %d: %s%n", s.getId(), s.getDescripcion());

        // Imprimo fármacos
        Nodo<Integer> aux = consulta.getPrimero();
        while (aux != null) {
            int idF = (int) aux.getEtiqueta();
            Farmaco f = uf.obtenerFarmaco(idF);
            if (f != null) {
                System.out.printf("Farmaco %d: %s%n", f.getId(), f.getDescripcion());
            } else {
                System.out.println("Farmaco no válido: " + idF);
            }
            aux = aux.getSiguiente();
        }

        // Veredicto
        boolean viable = uf.preparadoViable(idSuero, consulta);
        System.out.println( viable ? "VIABLE" : "NO VIABLE" );
    }
}
