package org.example.Ejercicio3;

public class Abonado implements Comparable<Abonado> {
    private String nombre;
    private String telefono;

    public Abonado(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return nombre + "," + telefono;
    }

    @Override
    public int compareTo(Abonado otro) {
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }
}
