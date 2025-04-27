import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ControlDeSucursales {
    private LinkedList<Sucursal> listaSucursales;

    public ControlDeSucursales() {
        listaSucursales = new LinkedList<>();
    }


    public void cargarSucursalesDesdeArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Estudiante UCU\\Documents\\prog3\\UT3_PD6\\src\\suc1.txt"))) {
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

    // Complejidad O(1), puesto a que se aniade al final de la lista.
    public void agregarSucursal(Sucursal sucursal) {
        listaSucursales.add(sucursal);
        System.out.println("Sucursal agregada: " + sucursal.getNombre());
    }

    // Complejidad O(n), propio de que puede llegar a recorren toda la lista en el peor caso.
    public Sucursal buscarSucursal(String nombre) {
        for (Sucursal s : listaSucursales) {
            if (s.getNombre().equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }


    // Complejidad: O(n), por la búsqueda + eliminación del nodo.
    public boolean eliminarSucursal(String nombre) {
        for (Sucursal s : listaSucursales) {
            if (s.getNombre().equalsIgnoreCase(nombre)) {
                listaSucursales.remove(s);
                System.out.println("Sucursal exterminada: " + nombre);
                return true;
            }
        }
        System.out.println("Sucursal con nombre '" + nombre + "' no encontrada.");
        return false;
    }

    // Complejidad: O(n), pues recorre la LinkedList una vez.
    public void listarSucursales() {
        if (listaSucursales.isEmpty()) {
            System.out.println("No hay sucursales registradas.");
        } else {
            System.out.println("Listado de Sucursales:");
            for (Sucursal s : listaSucursales) {
                System.out.println(s);  // s.toString().
            }
        }
    }

    // Complejidad: O(1), esto es dado a que la clase LinkedList de Java, parece manejar un contador interno.
    public int getCantidadSucursales() {
        return listaSucursales.size();
    }

    // Complejidad: O(1) (LinkedList.isEmpty() es inmediato, esta implicito).
    public boolean estaVacio() {
        return listaSucursales.isEmpty();
    }
}
