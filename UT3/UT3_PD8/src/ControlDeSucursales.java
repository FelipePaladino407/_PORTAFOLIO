import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ControlDeSucursales {
    private ArrayList<Sucursal> arreySucursales;

    public ControlDeSucursales() {
        arreySucursales = new ArrayList<>();
    }

    // Complejidad O(n), donde n es la cantidad de líneas no vacias del archivo. No es muy relavante en comparacion al resto de los metodos.
    public void cargarSucursalesDesdeArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Estudiante UCU\\Documents\\prog3\\UT3_PD8\\src\\suc1.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    // Procede a crear la sucursal con el nombre que lee en el buffersito.
                    Sucursal sucursal = new Sucursal(linea);
                    agregarSucursal(sucursal);
                }
            }
        } catch (IOException e) {
            System.err.println("Error fatal al leer el archivo: " + e.getMessage());
        }
    }

    // Complejidad O(1), ArrayList agrega al final del array.
    public void agregarSucursal(Sucursal sucursal) {
        arreySucursales.add(sucursal);
        System.out.println("Sucursal agregada: " + sucursal.getNombre());
    }

    /**
     * Nuevo metodo particular de los arrays. Agrega una sucursaleta dado un indice proporcionado.
     * No es muy eficiente, de hecho, todo lo contrario. Naturalmente el de insertar es O(1).
     * Este tiene una complejidad de O(n) en el peor caso, pues al insertar un elemento con un indice que
     * ya usa otro, se tiene que hacer un desplazamiento.
     * Igual lo agrego, ya que es algo que se puede hacer con arrays y no con linkedList.
     */
    public void insertarSucursalEnIndice(int indice, Sucursal sucursal) {
        if (indice < 0 || indice > arreySucursales.size()) {
            System.out.println("Índice fuera de rango. Por desgracia no se pudo insertar tu sucursal.");
        } else {
            arreySucursales.add(indice, sucursal);
            System.out.println("Sucursal insertada en índice " + indice + ": " + sucursal.getNombre());
        }
    }


    // En el peor caso hablamos de O(n), por la búsqueda lineal y desplazamiento de elementos.
    public boolean eliminarSucursal(String nombre) {
        for (Sucursal sucu : arreySucursales) {
            if (sucu.getNombre().equals(nombre)) {
                arreySucursales.remove(sucu);
                System.out.println("Sucursal exterminada: " + sucu.getNombre());
                return true;
            }
        }
        System.out.println("Sucursal, cuyo nombre es " + nombre + " no fue encontrada.");
        return false;
    }

    /**
     * Nuevo metodo particular de los arrays. Elimina la sucursal por su índice, no por busqueda lineal.
     * Complejidad: O(n) – por el desplazamiento de elementos al eliminar.
     * No llega a ser, lamentablemente complejidad O(1), pues los elementos se tienen que desplazar igual.
     * De todas formas, es más rapido que el buscar lineal, pues se ahorra esa tediosa búsqueda.
     */
    public boolean eliminarSucursalPorIndice(int indice) {
        if (indice >= 0 && indice < arreySucursales.size()) {
            Sucursal eliminada = arreySucursales.remove(indice);
            System.out.println("Sucursal eliminada por índice: " + eliminada.getNombre());
            return true;
        } else {
            System.out.println("Índice inválido: " + indice);
            return false;
        }
    }

    // Complejidad O(n), por busqueda secuencial.
    public Sucursal buscarSucursal(String nombre) {
        for (Sucursal sucu : arreySucursales) {
            if (sucu.getNombre().equals(nombre)) {
                return sucu;
            }
        }
        return null;
    }

    /**
     * Nuevo metodo particular de los arrays. Busca la sucursal por su índice.
     * Es de compeljidad O(1).
     */
    public Sucursal buscarSucursalPorIndice(int indice) {
        if (indice >= 0 && indice < arreySucursales.size()) {
            return arreySucursales.get(indice);
        } else {
            System.out.println("Índice fuera de rango: " + indice);
            return null;
        }
    }

    // Complejidad O(n), pues recorre todo el array.
    public void listarSucursales() {
        if (arreySucursales.isEmpty()) {
            System.out.println("No hay sucursales actualmente registardas, vuelva mas tarde.");
        }
        else{
            System.out.println("== Lista de sucursales ==");
            for (Sucursal sucu : arreySucursales) {
                System.out.println(sucu.getNombre());
            }
        }
    }

    // Complejidad O(1).
    public int getCantidadSucursales() {
        return arreySucursales.size();
    }

    // Complejidad O(1).
    public boolean esVacio(){
        return arreySucursales.isEmpty();
    }
}
