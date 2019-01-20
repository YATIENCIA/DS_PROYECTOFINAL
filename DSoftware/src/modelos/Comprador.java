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
public class Comprador extends Usuario{
    private ArrayList<Producto> carrito;

    public Comprador(String nombres, String apellidos) {
        super(nombres, apellidos);
    }

    public void setCarrito(ArrayList<Producto> carrito) {
        this.carrito = carrito;
    }

    public ArrayList<Producto> getCarrito() {
        return carrito;
    }
    
    public Comprador(){}
    
}
