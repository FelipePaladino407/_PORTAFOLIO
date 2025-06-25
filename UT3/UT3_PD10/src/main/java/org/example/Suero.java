// Suero.java
package org.example;

public class Suero {
    private final int id;
    private final String descripcion;
    public Suero(int id, String desc) {
        this.id = id;
        this.descripcion = desc;
    }
    public int getId()            { return id; }
    public String getDescripcion(){ return descripcion; }
}
