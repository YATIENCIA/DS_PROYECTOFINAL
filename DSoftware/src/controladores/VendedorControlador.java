/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import diseñosoftware.vistas.VendedorVista;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import modelos.Producto;
import modelos.Usuario;
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
      Producto producto = new Producto();
        return producto;
    }

    //public boolean modificarProducto(String nombre, String descripcion, String precio) {
        
   // }
    
    public boolean borrarProducto(){
        
        return true;
    }
    
    
    
}
