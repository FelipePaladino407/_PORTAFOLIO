package org.example;


public class Producto {
    private final String codigo;
    private final String descripcion;
    private final double precioUnitario;
    private int cantidad;

    public Producto(String codigo, String descripcion, double precioUnitario, int cantidad) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
