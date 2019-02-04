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
public class EstrategiaEfectivo implements PagoEstrategia{
    private String tipo="efectivo";
    private double costo;

    @Override
    public void pago(int cantpro, Producto p) {
        System.out.println("Pago en efectivo"+p.getNombre());
        Venta venta=new Venta(true, EstadoVenta.PENDIENTE, p.getVendedor(), (Comprador) SistemaPoliVentas.usuario, p, cantpro, this);
        Venta.GuardarVenta(venta);
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCosto() {
        return costo;
    }

    @Override
    public String toString() {
        return  tipo ;
    }
    
}
