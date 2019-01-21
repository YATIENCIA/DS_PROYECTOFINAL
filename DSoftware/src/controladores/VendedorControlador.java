/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import diseñosoftware.vistas.VendedorVista;
import java.util.ArrayList;
import modelos.Producto;
import modelos.Vendedor;

/**
 *
 * @author DOTA
 */
public class VendedorControlador {
    
    VendedorVista vista;
    Vendedor vendedor;
    public VendedorControlador(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    
    
    public Producto crearProducto(String nombre, String descripcion, String precio) {
        Producto producto = new Producto(nombre, descripcion, precio);
        return producto;
    }

    //public boolean modificarProducto(String nombre, String descripcion, String precio) {
        
   // }
    
    public boolean borrarProducto(){
        
        return true;
    }
}
