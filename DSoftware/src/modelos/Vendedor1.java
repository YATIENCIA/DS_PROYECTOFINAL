/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import controladores.VendedorControlador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author IYAC
 */
public class Vendedor1 extends Comprador{
    private Calificacion calificacion_promedio;
    private ArrayList<Calificacion> calificaciones;
    private ArrayList<Venta> ventas;
    private ArrayList<Producto> productos;

    public Vendedor1(String nombres, String apellidos) {
        super(nombres, apellidos);
    }

    private Vendedor1(String usuario, String contraseña, String nombres, String apellidos, String telefono, String email, String direccion, String cedula, String matricula, boolean whatsapp) {
        super(usuario, contraseña, nombres, apellidos, telefono, email, direccion, cedula, matricula, whatsapp);
        this.calificaciones = new ArrayList();
        this.ventas = new ArrayList();
        this.productos = new ArrayList();
    }
    
    public Vendedor1(){}
    
    
    public static void CrearNuevoVendedor(String usuario, String contraseña, String nombres, String apellidos, String telefono, String email, String direccion, String cedula, String matricula, boolean whatsapp) {
     {
        Vendedor1 v=new Vendedor1(usuario,contraseña, nombres, apellidos, telefono, email, direccion, cedula,  matricula,  whatsapp);
        ConexionSQL.AñadirCuentaALaBase(usuario, contraseña, "Vendedor");
        ConexionSQL.AñadirPersonaALaBase(usuario, contraseña, nombres, apellidos, telefono, email, direccion, cedula, matricula, whatsapp);
    }

    }
    
    public boolean addProducto(Producto producto){
        productos.add(producto);
        return true;
    }

    public Producto buscarProducto(String nombre, String descripcion, String precio){
       boolean nombreIgual;
        boolean descripcionIgual;
        boolean precioIgual;
        ArrayList<Producto> productos = this.productos;
        Producto producto=null;
        for(Producto p : productos){
            nombreIgual=nombre.equals(p.getNombre());
            descripcionIgual = descripcion.equals(p.getDescripcion());
            precioIgual = precio.equals(p.getPrecio());
            
            if(nombreIgual && descripcionIgual && precioIgual){
                producto=p;
            }
        }
        return producto;
    }
        
    public Calificacion getCalificacion_promedio() {
        return calificacion_promedio;
    }

    public void setCalificacion_promedio(Calificacion calificacion_promedio) {
        this.calificacion_promedio = calificacion_promedio;
    }

    public ArrayList<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    
    
}
