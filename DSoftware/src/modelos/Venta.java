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
public class Venta {
    private boolean pago;
    private EstadoVenta estado;
    private Vendedor vendedor;
    private Comprador comprador;
    private Producto producto;
    private int cantidad;
    private PagoEstrategia estrategia;

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

   
}
