package org.example.PD9;

public class Principal {
    public static int factorial(int num) {
        if (num < 0) {
            throw new ArithmeticException("No puede ser negativo");
        }

        if (num == 0) {
            return 1;
        }

        int resultado = num;
        for (int i = num-1; i > 1; i--) {
            resultado = resultado * i;
        }

        return resultado;
    }

    public static int calculoSuma(int num) {
        int inicial = 0;
        int temp = 0;
        if (esPrimo(num)) {
           inicial = 0;
        }
        else {
            inicial = 1;
        }

       while (inicial <= num) {
           temp += inicial;
           inicial += 2;
       }
       return temp;
    }


    public static boolean esPrimo(int num) {
        boolean esPrimo;

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
//        System.out.println(factorial(-2));
        System.out.println(calculoSuma(9));
    }
}
