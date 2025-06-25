// Farmaco.java
package org.example;

public class Farmaco {
    private final int id;
    private final String descripcion;
    public Farmaco(int id, String desc) {
        this.id = id;
        this.descripcion = desc;
    }
    public int getId()            { return id; }
    public String getDescripcion(){ return descripcion; }
}
