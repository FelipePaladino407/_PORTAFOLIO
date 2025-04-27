import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ControlDeSucursales gestion = new ControlDeSucursales();

        String rutaArchivo = "C:\\Users\\Estudiante UCU\\Documents\\prog3\\UT3_PD6\\src\\suc1.txt";

        // Se cargan las sucursales desde el archivo.
        gestion.cargarSucursalesDesdeArchivo(rutaArchivo);

        // Se verifica que si la lista esta vacia.
        if (gestion.estaVacio()) {
            System.out.println("El directorio de sucursales está vacío.");
        } else {
            System.out.println("El directorio de sucursales tiene "
                    + gestion.getCantidadSucursales() + " elementos.");
        }


        // gestion.listarSucursales();   // Se procede a listar las sucursales cargadas en el txt.

        // Implementacion de Menu que me llevo 1 hora hacer.
        Scanner sc = new Scanner(System.in);
        int opcion;                             // Si no pones un entero, se rompe todito.
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar Sucursal");
            System.out.println("2. Buscar Sucursal");
            System.out.println("3. Eliminar Sucursal");
            System.out.println("4. Listar Sucursales");
            System.out.println("5. Verificar si está vacío");
            System.out.println("6. Mostrar cantidad de Sucursales");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer.

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de la nueva sucursal: ");
                    String nuevoNombre = sc.nextLine();
                    gestion.agregarSucursal(new Sucursal(nuevoNombre));
                    break;
                case 2:
                    System.out.print("Nombre de la sucursal a buscar: ");
                    String nombreBuscar = sc.nextLine();
                    Sucursal sEncontrada = gestion.buscarSucursal(nombreBuscar);
                    if (sEncontrada != null) {
                        System.out.println("Sucursal encontrada: " + sEncontrada);
                    } else {
                        System.out.println("No se encontró sucursal con ese infame nombre.");
                    }
                    break;
                case 3:
                    System.out.print("Nombre de la sucursal a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    gestion.eliminarSucursal(nombreEliminar);
                    break;
                case 4:
                    gestion.listarSucursales();
                    break;
                case 5:
                    if (gestion.estaVacio()) {
                        System.out.println("El directorio está vacío.");
                    } else {
                        System.out.println("El directorio NO está vacío.");
                    }
                    break;
                case 6:
                    System.out.println("Cantidad de sucursales: " + gestion.getCantidadSucursales());
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    System.out.println("Saliste, Buenas noches.");
                    break;
                default:
                    System.out.println("Opción inválida ._.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
