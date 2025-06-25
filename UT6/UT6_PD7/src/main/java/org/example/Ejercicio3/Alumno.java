package org.example.Ejercicio3;

import java.util.Objects;

public class Alumno {

    private int ID;
    private String nombre;
    private String email;

    public Alumno(int ID, String nombre, String email) {
        this.ID = ID;
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno felipe = (Alumno) o;
        return ID == felipe.ID && Objects.equals(nombre, felipe.nombre)
                && Objects.equals(email, felipe.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, nombre, email);
    }


    /**
     * Requisitos:
     * Si dos objetos son iguales segun equals(), entonces deben tener el mismo HashCode()
     * Dos objetos con el mismo HashCode() no son necesariamente iguales.
     * El HashCode() debe siempre devolver el mismo valor para un mismo objeto durante su vida,
     * a excepecion de que ese objeto cambie de estado.
     */
}
