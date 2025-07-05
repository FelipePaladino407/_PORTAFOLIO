package org.example.PD4_EJERCICIO_3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class disjointSet {

    /** UT9_PD4 - Ejercicio 3:
     * Comprueba si dos colecciones son disjuntas (no comparten elementos) de forma eficiente.
     *
     * Enfoque:
     * 1. Garantiza que A sea la colección de menor tamaño (m ≤ n).
     * 2. Ordena A en O(m log m) usando Collections.sort().
     * 3. Para cada elemento x en B (n elementos), realiza una búsqueda binaria en A en O(log m).
     *    - Si se encuentra x, devuelve false (no son disjuntos).
     *    - Si tras recorrer todo B no hay coincidencias, devuelve true (son disjuntos).
     *
     * Complejidad:
     *   • Ordenación de A:         O(m log m)
     *   • n búsquedas binarias en A: O(n log m)
     *   ⇒ Tiempo total: O(m log m + n log m)
     *
     * @param <T> tipo de elemento, debe implementar Comparable<? super T>
     * @param A   primera colección de elementos.
     * @param B   segunda colección de elementos.
     * @return    true si A y B no comparten ningún elemento; false en caso contrario.
     */
    public static <T extends Comparable<? super T>> boolean sonDisjuntos(List<T> A, List<T> B) {

        // Nos aseguramos de que "A" sea la lista mas chiquita:
        if (A.size() > B.size()) {
            return sonDisjuntos(B, A);
        }
        // Copio y ordeno "A":
        List<T> chiquito = new java.util.ArrayList<>(A);
        Collections.sort(chiquito);  // O(m log m)

        // Para cada elemento de B, buscar en small:
        for (T elem : B) {        // n iteraciones
            if (Collections.binarySearch(chiquito, elem) >= 0) {  // O(log m)
                return false;  // Se encontró intersección.
            }
        }
        return true;  // Ningún elemento común.
    }

    // Pruebongas:
    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(5, 1, 9, 3);
        List<Integer> B = Arrays.asList(2, 7, 3, 8);

        boolean disjuntos = sonDisjuntos(A, B);
        System.out.println("¿Son disjuntos? " + disjuntos);
        // Imprime: ¿Son disjuntos? false (porque el 3 está en ambos)

        List<Integer> Pepe = Arrays.asList(5, 1, 9, 3);
        List<Integer> Julio = Arrays.asList(2, 7, 0, 8);

        boolean siDisjuntos = sonDisjuntos(Pepe, Julio);
        System.out.println("\n¿Son disjuntos? " + siDisjuntos);
        // Imprime: ¿Son disjuntos? true (pues no se repiten elementos)

    }
}
