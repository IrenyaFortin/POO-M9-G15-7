/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.pfm9.irenyafortin.pkg202410060535.grupo15.pkg7.Clases;

import Interfaces.AlertaStock;

/**
 *
 * @author IRENYA FORTIN
 */
public class AlertaStockBajo implements AlertaStock {
  @Override
    public void notificar(Producto producto) {
        System.out.println("Leyendo productos...");
        try {
            Thread.sleep(1000); // Esperar 1 segundo
            System.out.print("Enviando datos");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500); // Esperar 0.5 segundos
                System.out.print(".");
            }
            Thread.sleep(1000); // Esperar 1 segundo

            if (producto.getStock() <= 2) {
                System.err.println("\nStock bajo para el producto " + producto.getNombre() +
                        " (Código: " + producto.getCodigo() + "). Stock actual: " + producto.getStock());
            } else {
                System.out.println("\nLos productos tienen el stock dentro del valor mínimo registrado.");
            }

        } catch (InterruptedException e) {
            System.err.println("Error al cargar datos.");
            e.printStackTrace();
        }
    }
}