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
public class Venta {
    private boolean pago;
    private EstadoVenta estado;
    private Vendedor vendedor;
    private Comprador comprador;
    private Producto producto;
    private int cantidad;
    private PagoEstrategia estrategia;

    public Venta(){}
    public Venta(boolean pago, EstadoVenta estado, Vendedor vendedor, Comprador comprador, Producto producto, int cantidad, PagoEstrategia estrategia) {
        this.pago = pago;
        this.estado = estado;
        this.vendedor = vendedor;
        this.comprador = comprador;
        this.producto = producto;
        this.cantidad = cantidad;
        this.estrategia = estrategia;
    }

    
    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

  
    public void setEstrategia(PagoEstrategia estrategia) {
        this.estrategia = estrategia;
    }

    public boolean isPago() {
        return pago;
    }

    public EstadoVenta getEstado() {
        return estado;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    

    public PagoEstrategia getEstrategia() {
        return estrategia;
    }

   
    public static void NotificarVendedor(Vendedor v)
    {
        int i = 0;
        try {
            String query = "{call AddNotificacion(?)}";
            ResultSet rs;
            Connection conn = ConexionSQL.getConnection();
            CallableStatement stmt = conn.prepareCall(query);
            //Set IN parameter
            stmt.setString(1, v.getCedula());
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void EliminarNotVendedor(Vendedor v)
    {
 
        try {
            String query = "{call RemoveNotificacion(?)}";
            ResultSet rs;
            Connection conn = ConexionSQL.getConnection();
            CallableStatement stmt = conn.prepareCall(query);
            //Set IN parameter
            stmt.setString(1, v.getCedula());
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
