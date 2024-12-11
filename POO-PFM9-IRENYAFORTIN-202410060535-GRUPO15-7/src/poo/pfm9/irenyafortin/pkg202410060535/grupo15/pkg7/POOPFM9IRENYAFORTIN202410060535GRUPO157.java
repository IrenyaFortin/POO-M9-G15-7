/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poo.pfm9.irenyafortin.pkg202410060535.grupo15.pkg7;

import Excepciones.ProductoNoEncontradoException;
import Excepciones.StockInsuficienteException;
import java.util.InputMismatchException;
import java.util.Scanner;
import poo.pfm9.irenyafortin.pkg202410060535.grupo15.pkg7.Clases.Categoria;
import poo.pfm9.irenyafortin.pkg202410060535.grupo15.pkg7.Clases.Inventario;
import poo.pfm9.irenyafortin.pkg202410060535.grupo15.pkg7.Clases.Producto;
import poo.pfm9.irenyafortin.pkg202410060535.grupo15.pkg7.Clases.Proveedor;

/**
 *
 * @author IRENYA FORTIN
 */
public class POOPFM9IRENYAFORTIN202410060535GRUPO157 {

    /**
     * @param args the command line arguments
     */
    private static Inventario inventario = new Inventario();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 7);
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Sistema de Gestion de Inventarios ---");
        System.out.println("1. Agregar producto");
        System.out.println("2. Eliminar producto");
        System.out.println("3. Buscar producto");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Listar productos");
        System.out.println("6. Generar alertas de stock bajo");
        System.out.println("7. Salir");
        System.out.print("Ingrese una opcion: ");
    }

    private static int leerOpcion() {
        int opcion;
        do {
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= 1 && opcion <= 7) {
                    return opcion;
                } else {
                    System.err.println("Opcion invalida. Ingrese un numero entre 1 y 7.");
                    System.out.print("Ingrese una opcion: ");
                }
            } catch (NumberFormatException e) {
                System.err.println("Opcion invalida. Ingrese un numero.");
                System.out.print("Ingrese una opcion: ");
            }
        } while (true);
    }

    private static void procesarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    eliminarProducto();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    listarProductos();
                    break;
                case 6:
                    generarAlertasStockBajo();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
            }
        } catch (StockInsuficienteException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void agregarProducto() {
        System.out.println("\n--- Agregar Producto ---");

        int codigo;
        do {
            codigo = leerEnteroValido("Codigo: ");
            if (inventario.buscarProducto(codigo) == null) {
                break;
            } else {
                System.err.println("Error: Ya existe un producto con ese código.");
            }
        } while (true);

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        double precio;
        do {
            try {
                System.out.print("Precio: ");
                precio = scanner.nextDouble();
                if (precio > 0) {
                    break;
                } else {
                    System.err.println("Error: El precio debe ser un número positivo.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: Ingrese un valor numérico para el precio.");
                scanner.nextLine();
            }
        } while (true);

        int stock;
        do {
            stock = leerEnteroValido("Stock: ");
            if (stock > 2) {
                break;
            } else {
                System.err.println("El stock inicial debe ser mayor que 2. " +
                        "Cuando el stock sea 2 o menor, el producto se enlistara como 'stock bajo'.");
            }
        } while (true);

        System.out.print("Nombre de la categoria: ");
        String nombreCategoria = scanner.nextLine();

        String nombreProveedor;
        do {
            System.out.print("Nombre del Proveedor: ");
            nombreProveedor = scanner.nextLine();
            if (nombreProveedor.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.err.println("Error: El nombre del proveedor solo debe contener letras y espacios.");
            }
        } while (true);

        String contactoProveedor;
        do {
            try {
                System.out.print("Telefono del proveedor : ");
                contactoProveedor = scanner.nextLine();
                Long.parseLong(contactoProveedor);
                break;
            } catch (NumberFormatException e) {
                System.err.println("Error: El contacto del proveedor solo debe contener números.");
            }
        } while (true);

        Categoria categoria = new Categoria(nombreCategoria);
        Proveedor proveedor = new Proveedor(nombreProveedor, contactoProveedor);
        Producto producto = new Producto(codigo, nombre, descripcion, precio, stock, categoria, proveedor);

        inventario.agregarProducto(producto);
        System.out.println("Producto agregado correctamente.");
    }

    private static void eliminarProducto() {
        System.out.println("\n--- Eliminar Producto ---");
        int codigo = leerEnteroValido("Ingrese el codigo del producto a eliminar: ");
        try {
            inventario.eliminarProducto(codigo);
            System.out.println("Producto eliminado correctamente.");
        } catch (ProductoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarProducto() {
        System.out.println("\n--- Buscar Producto ---");
        int codigo = leerEnteroValido("Ingrese el codigo del producto a buscar: ");
        Producto producto = inventario.buscarProducto(codigo);
        if (producto != null) {
            System.out.println(producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void actualizarProducto() {
        System.out.println("\n--- Actualizar Producto ---");
        int codigo = leerEnteroValido("Ingrese el codigo del producto a actualizar: ");

        try {
            Producto productoExistente = inventario.buscarProducto(codigo);
            if (productoExistente == null) {
                throw new ProductoNoEncontradoException("Producto no encontrado con el codigo: " + codigo);
            }

            System.out.print("Nuevo nombre: ");
            String nuevoNombre = scanner.nextLine();
            System.out.print("Nueva descripción: ");
            String nuevaDescripcion = scanner.nextLine();

            double nuevoPrecio;
            do {
                try {
                    System.out.print("Nuevo precio: ");
                    nuevoPrecio = scanner.nextDouble();
                    if (nuevoPrecio > 0) {
                        break;
                    } else {
                        System.err.println("Error: El precio debe ser un numero positivo.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Error: Ingrese un valor numerico para el precio.");
                    scanner.nextLine();
                }
            } while (true);

            System.out.print("Nuevo stock: ");
            int nuevoStock = scanner.nextInt();
            scanner.nextLine();

            productoExistente.setNombre(nuevoNombre);
            productoExistente.setDescripcion(nuevaDescripcion);
            productoExistente.setPrecio(nuevoPrecio);
            productoExistente.setStock(nuevoStock);

            inventario.actualizarProducto(productoExistente);
            System.out.println("Producto actualizado correctamente.");

        } catch (ProductoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarProductos() {
        System.out.println("\n--- Listar Productos ---");
        inventario.listarProductos();
    }

    private static void generarAlertasStockBajo() {
        System.err.println("\n--- Generar Alertas de Stock Bajo ---");
        inventario.generarAlertaStockBajo();
    }

    private static int leerEnteroValido(String mensaje) {
        int valor;
        do {
            try {
                System.out.print(mensaje);
                valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.err.println("Error: Ingrese un valor numerico entero valido.");
                scanner.nextLine();
            }
        } while (true);
    }
}