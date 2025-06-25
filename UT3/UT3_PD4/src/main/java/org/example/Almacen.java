
package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Almacen {
    private final Lista<Producto> productos;

    public Almacen() {
        this.productos = new Lista<>();
    }

    /**
     * Incorpora un producto nuevo o aumenta stock de uno existente.
     * @return monto gastado (precioUnitario * cantidad)
     */
    public double incorporar(String codigo, String descripcion, double precio, int cantidad) {
        Nodo<Producto> nodo = productos.buscar(codigo);
        if (nodo != null) {
            // ya existe → sumar stock
            Producto p = nodo.getDato();
            p.setCantidad(p.getCantidad() + cantidad);
        } else {
            // nuevo producto → insertar al final
            Producto p = new Producto(codigo, descripcion, precio, cantidad);
            Nodo<Producto> nuevo = new Nodo<>(codigo, p);
            productos.insertar(nuevo);
        }
        return precio * cantidad;
    }

    /**
     * Vende hasta 'cantidad' unidades del producto dado.
     * @return monto vendido (precioUnitario * unidades realmente vendidas)
     */
    public double vender(String codigo, int cantidad) {
        Nodo<Producto> nodo = productos.buscar(codigo);
        if (nodo == null) return 0;
        Producto p = nodo.getDato();
        int disponibles = p.getCantidad();
        int vendidos = Math.min(disponibles, cantidad);
        p.setCantidad(disponibles - vendidos);
        return p.getPrecioUnitario() * vendidos;
    }

    /** Elimina un producto por código. */
    public boolean eliminarProducto(String codigo) {
        return productos.eliminar(codigo);
    }

    /** Retorna stock actual; 0 si no existe. */
    public int consultarStock(String codigo) {
        Nodo<Producto> nodo = productos.buscar(codigo);
        return nodo != null ? nodo.getDato().getCantidad() : 0;
    }

    /**
     * Devuelve una lista de productos ordenada alfabéticamente por descripción.
     */
    public List<Producto> listarOrdenadosPorNombre() {
        List<Producto> lista = new ArrayList<>();
        Nodo<Producto> aux = productos.getPrimero();
        while (aux != null) {
            lista.add(aux.getDato());
            aux = aux.getSiguiente();
        }
        Collections.sort(lista, Comparator.comparing(Producto::getDescripcion));
        return lista;
    }
}
