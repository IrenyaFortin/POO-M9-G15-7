/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pfm9.irenyafortin.pkg202410060535.grupo15.pkg7.Clases;
import Excepciones.ProductoNoEncontradoException;
import Interfaces.AlertaStock;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author IRENYA FORTIN
 */
public class Inventario {
    
private List<Producto> productos;
private AlertaStock alertaStock;

    public Inventario() {
        this.productos = new ArrayList<>();
        this.alertaStock = new AlertaStockBajo(); 
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void eliminarProducto(int codigo) throws ProductoNoEncontradoException {
        Producto producto = buscarProducto(codigo);
        if (producto != null) {
            this.productos.remove(producto);
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado con el codigo: " + codigo);
        }
    }

    public Producto buscarProducto(int codigo) {
        for (Producto producto : this.productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    public void actualizarProducto(Producto producto) throws ProductoNoEncontradoException {
        int index = this.productos.indexOf(buscarProducto(producto.getCodigo()));
        if (index != -1) {
            this.productos.set(index, producto);
        } else {
            throw new ProductoNoEncontradoException("Producto no encontrado con el codigo: " + producto.getCodigo());
        }
    }

    public void listarProductos() {
    if (this.productos.isEmpty()) {
        System.out.println("No hay productos registrados aun.");
    } else {
        for (Producto producto : this.productos) {
            System.out.println(producto);
        }
    }
}

    public void generarAlertaStockBajo() {
    boolean hayStockBajo = false; 

    for (Producto producto : this.productos) {
        if (producto.getStock() <= 2) {
            alertaStock.notificar(producto);
            hayStockBajo = true; 
        }
    }

    if (!hayStockBajo) {
        System.out.println("Todos los productos estan sobre el minimo de stock registrado.");
    }
}
}
