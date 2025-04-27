package org.example;

public class TAlumno {

    private int cedula;
    private String nombre;
    private String apellido;

    public TAlumno(int cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getCedula() { return cedula; }

    @Override
    public String toString() {
        return cedula + " - " + nombre + " " + apellido;
    }
}
