/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;

/**
 *
 * @author IYAC
 */
public class Comprador extends Usuario {

    private ArrayList<Producto> carrito;

    public void comprar(Producto p, String metodo, int cantidad) {
        PagoEstrategia ef;
        if (metodo.equalsIgnoreCase("efectivo")) {
            ef = new EstrategiaEfectivo();
        } else {
            ef = new EstrategiaAppMovil();
        }

        ef.pago(cantidad, p);
        
    }

    public Comprador(String nombres, String apellidos) {
        super(nombres, apellidos);
    }

    public Comprador(String usuario, String contraseña, String nombres, String apellidos, String telefono, String email, String direccion, String cedula, String matricula, boolean whatsapp) {
        super(usuario, contraseña, nombres, apellidos, telefono, email, direccion, cedula, matricula, whatsapp);
        carrito = new ArrayList();
    }

    public void setCarrito(ArrayList<Producto> carrito) {
        this.carrito = carrito;
    }

    public ArrayList<Producto> getCarrito() {
        return carrito;
    }

    public Comprador() {
    }

    public static void CrearNuevoComprador(String usuario, String contraseña, String nombres, String apellidos, String telefono, String email, String direccion, String cedula, String matricula, boolean whatsapp) {

        Comprador v = new Comprador(usuario, contraseña, nombres, apellidos, telefono, email, direccion, cedula, matricula, whatsapp);
        ConexionSQL.AñadirCuentaALaBase(usuario, contraseña, "Comprador");
        ConexionSQL.AñadirPersonaALaBase(usuario, contraseña, nombres, apellidos, telefono, email, direccion, cedula, matricula, whatsapp);
        
        }

}
