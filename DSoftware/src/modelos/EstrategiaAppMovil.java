/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import controladores.SistemaPoliVentas;

/**
 *
 * @author IYAC
 */
public class EstrategiaAppMovil implements PagoEstrategia{
    private String tipo="AppMovil";
    private String telefono;

     @Override
    public void pago(int cantpro, Producto p) {
        System.out.println("Pago en AppMovil");
        Venta venta=new Venta(true, EstadoVenta.PENDIENTE, p.getVendedor(), (Comprador) SistemaPoliVentas.usuario, p, cantpro, this);
        Venta.GuardarVenta(venta);
    
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTelefono() {
        return telefono;
    }
    
     @Override
    public String toString() {
        return  tipo ;
    }
}
