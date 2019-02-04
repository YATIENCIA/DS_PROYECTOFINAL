/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

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
public class Vendedor extends Comprador {

    private Calificacion calificacion_promedio;
    private ArrayList<Calificacion> calificaciones;
    private ArrayList<Venta> ventas;
    private ArrayList<Producto> productos;

    public Vendedor(String nombres) {
        super.nombres = nombres;
    }

    private Vendedor(String usuario, String contraseña, String nombres, String apellidos, String telefono, String email, String direccion, String cedula, String matricula, boolean whatsapp) {
        super(usuario, contraseña, nombres, apellidos, telefono, email, direccion, cedula, matricula, whatsapp);
        this.calificaciones = new ArrayList();
        this.ventas = new ArrayList();
        this.productos = new ArrayList();
    }

    public Vendedor() {
    }

    public static void CrearNuevoVendedor(String usuario, String contraseña, String nombres, String apellidos, String telefono, String email, String direccion, String cedula, String matricula, boolean whatsapp) {

        Vendedor v = new Vendedor(usuario, contraseña, nombres, apellidos, telefono, email, direccion, cedula, matricula, whatsapp);
        
       AñadirDB.AñadirCuentaALaBase(usuario, contraseña, "Vendedor");
        AñadirDB.AñadirPersonaALaBase(usuario, contraseña, nombres, apellidos, telefono, email, direccion, cedula, matricula, whatsapp);
        
    }

    public boolean addProducto(Producto producto) {
        productos.add(producto);
        return true;
    }

    public Producto buscarProducto(String nombre, String descripcion, String precio) {
        boolean nombreIgual;
        boolean descripcionIgual;
        boolean precioIgual;
        ArrayList<Producto> productos = this.productos;
        Producto producto = null;
        for (Producto p : productos) {
            nombreIgual = nombre.equals(p.getNombre());
            descripcionIgual = descripcion.equals(p.getCategoria().toString());
            precioIgual = precio.equals(p.getPrecio());

            if (nombreIgual && descripcionIgual && precioIgual) {
                producto = p;
            }
        }
        return producto;
    }

  
}
