package org.example;

public class Main {
    public static void main(String[] args) {
        // 1) Crear el ArbolBB con su diccionario (x→5, y→4, z→3)
        ArbolBB<Integer> arbol = new ArbolBB<>();

        // 2) Construir el árbol de expresión:     (*)
        //                                       /   \
        //                                     (+)   (-)
        //                                    /  \   /  \
        //                                  x     2 y    3
        ElementoAB<Integer> root  = new ElementoAB<>("*", 0);
        ElementoAB<Integer> plus  = new ElementoAB<>("+", 0);
        ElementoAB<Integer> minus = new ElementoAB<>("-", 0);

        // enlazamos
        root.setHijoIzq(plus);
        root.setHijoDer(minus);

        // hojas del +
        plus.setHijoIzq(new ElementoAB<>("x",   0));
        plus.setHijoDer(new ElementoAB<>("2",   2));

        // hojas del –
        minus.setHijoIzq(new ElementoAB<>("y",  0));
        minus.setHijoDer(new ElementoAB<>("3",3);

        // 3) Asignar al árbol
        arbol.raiz = root;

        // 4) Mostrar en-orden antes de sustituir
        System.out.println("InOrden antes:  " + arbol.inOrdenStr());
        //    → debería imprimir algo como: "+ x 2 * y 3"

        // 5) Sustituir variables por constantes
        arbol.sustituir();

        // 6) Mostrar en-orden luego de sustituir
        System.out.println("InOrden después: " + arbol.inOrdenStr());
        //    → "+ 5 2 * 4 3"

        // 7) Evaluar la expresión numérica
        int resultado = arbol.evaluar();
        System.out.println("Resultado de evaluar(): " + resultado);
        //    → (5 + 2) * (4 - 3) = 7 * 1 = 7
    }
}