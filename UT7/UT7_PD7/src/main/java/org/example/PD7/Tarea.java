package org.example.PD7;

public class Tarea {

    private final String codigo;
    private final int tiempo;

    public Tarea(String codigo, int tiempo) {
        this.codigo = codigo;
        this.tiempo = tiempo;
    }

    public String getCodigo() {
        return codigo;
    }
    public int getTiempo() {
        return tiempo;
    }
    @Override
    public String toString() {
        return codigo + ": " + tiempo;
    }
}
