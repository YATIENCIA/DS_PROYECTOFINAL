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
public class Vendedor extends Comprador{
    private Calificacion calificacion_promedio;
    private ArrayList<Calificacion> calificaciones;
    private ArrayList<Venta> ventas;
    private ArrayList<Producto> productos;

    public Vendedor(String nombres, String apellidos) {
        super(nombres, apellidos);
    }
    
    public Vendedor(){}
}
