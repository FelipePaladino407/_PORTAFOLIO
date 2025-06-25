// ProgramaExpresion.java
package org.example;

public class ProgramaExpresion {
    public static void main(String[] args) {
        probar("{}{{}}");      // bien formada
        probar("{ {}{{}");    // mal formada
        probar("abc{def}ghi"); // ignora letras → bien formada
        probar("{{}{}}{}");    // bien formada
        probar("{{}");         // mal formada
    }

    private static void probar(String expr) {
        // Convertir String a Lista<Character>
        Lista<Character> lista = new Lista<>();
        for (char c : expr.toCharArray()) {
            lista.insertar(new Nodo<>(c, c));
        }

        boolean ok = Expresion.controlCorchetes(lista);
        System.out.printf("'%s' → %s%n", expr, (ok ? "OK" : "ERROR"));
    }
}
