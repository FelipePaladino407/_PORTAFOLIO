package org.example.Ejercicio1;

public class Ejercicio1 {

    /**
     * Para un Integer, el hashCode() devuelve su valor numerico, simplemente.
     * return "Value".
     *
     * Para un String, java utiliza una formula polinomica para caluclar el hash.
     * s[0]*31ⁿ⁻¹ + s[1]*31ⁿ⁻² + ... + s[n-1]
     * Donde S[i] es el caracter en la posicion i.
     *
     * Hablando de la clase Object, la implementacion ppr defecto en java devuelve un valor
     * entero que representa la direccion en memeoria del objeto.
     *
     *
     * Por que son diferentes???
     * Porque cada clase necesita un criterio diferente de identidad.
     * Object: por instancia/memoria.
     * String: valor contenido, sensible al orden y caracteres.
     * Integer: valor numerico exacto.
     */

}
