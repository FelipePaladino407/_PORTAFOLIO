package org.example.PD8;

public class Marcapasos {
    private short presionSanguinea; // 2 bytes
    private short frecuenciaCardiaca; // 2 bytes
    private short azucarEnSangre; // 2 bytes

    private int maximaFuerzaExpuesto; // 4 bytes
    private float tiempoEntreLatidos; // 4 bytes
    private double bateriaRestante; // 8 bytes
    private char[] codigoFabricante = new char[8]; // char(2bytes) * 8 = 16 bytes

    public Marcapasos() {
        // Inicializar variables, etc.
    }

    // No voy a escribir los getters y setters porque no tengo ganas

    /**
     * CANTIDAD DE MEMORIA QUE OCUPA
     * short + short + short = 6 bytes
     * int (4 bytes) + float (4 bytes) + double (8 bytes) = 16 bytes
     * char[8] (cada char ocupa 2 bytes) = 16 bytes
     * ------------------
     * TOTAL = 38 bytes
     */
}
