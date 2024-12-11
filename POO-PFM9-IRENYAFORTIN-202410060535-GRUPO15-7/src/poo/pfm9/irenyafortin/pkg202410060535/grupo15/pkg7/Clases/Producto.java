/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pfm9.irenyafortin.pkg202410060535.grupo15.pkg7.Clases;

import Excepciones.StockInsuficienteException;

/**
 *
 * @author IRENYA FORTIN
 */
public class Producto {
    
    private int codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private Categoria categoria;
    private Proveedor proveedor;

    public Producto(int codigo, String nombre, String descripcion, double precio, int stock, Categoria categoria, Proveedor proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void disminuirStock(int cantidad) {
        if (this.stock >= cantidad) {
            this.stock -= cantidad;
        } else {
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + this.nombre);
        }
    }

    public void aumentarStock(int cantidad) {
        this.stock += cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio= L." + precio +
                ", stock=" + stock +
                ", categoria=" + categoria.getNombre() +
                ", proveedor=" + proveedor.getNombre() +
                '}';
    }
}
