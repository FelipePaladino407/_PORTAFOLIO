import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ControlDeSucursales gestion = new ControlDeSucursales();

        String rutaArchivo = "C:\\Users\\Estudiante UCU\\Documents\\prog3\\UT3_PD6\\src\\suc1.txt";

        // Se cargan las sucursales desde el archivo.
        gestion.cargarSucursalesDesdeArchivo(rutaArchivo);

        // Se verifica que si la lista esta vacia.
        if (gestion.esVacio()) {
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
            System.out.println("7. Buscar Sucursal por su indice");
            System.out.println("8. Eliminar Sucursal por su indice");
            System.out.println("9. Insertar una Sucursal dado un indice");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpia el buffer.

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
                    if (gestion.esVacio()) {
                        System.out.println("El directorio está vacío.");
                    } else {
                        System.out.println("El directorio NO está vacío.");
                    }
                    break;
                case 6:
                    System.out.println("Cantidad de sucursales: " + gestion.getCantidadSucursales());
                    break;

                    // Casos unicos del array. Andando barbaro.
                case 7:
                    System.out.print("Ingrese el índice de la sucursal a buscar: ");
                    int indice = sc.nextInt();
                    sc.nextLine(); // limpia el buffer.
                    Sucursal sucPorIndice = gestion.buscarSucursalPorIndice(indice);
                    if (sucPorIndice != null) {
                        System.out.println("Sucursal encontrada en índice " + indice + ": " + sucPorIndice.getNombre());
                    } else {
                        System.out.println("No hay sucursal en ese índice.");
                    }
                    break;

                case 8:
                    System.out.print("Ingrese el índice de la sucursal a trapear (eliminar): ");
                    int indiceEliminar = sc.nextInt();
                    sc.nextLine(); // limpia el buffer.
                    boolean eliminada = gestion.eliminarSucursalPorIndice(indiceEliminar);
                    if (eliminada) {
                        System.out.println("Sucursal en índice " + indiceEliminar + " fue sucumbida.");
                    } else {
                        System.out.println("Índice inválido. No se pudo eliminar la sucursal.");
                    }
                    break;

                case 9:
                    System.out.print("Ingrese el índice donde desea insertar la sucursal: ");
                    int indiceInsertar = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    System.out.print("Ingrese el nombre de la nueva sucursal: ");
                    String nombreSucursal = sc.nextLine();

                    gestion.insertarSucursalEnIndice(indiceInsertar, new Sucursal(nombreSucursal));
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
