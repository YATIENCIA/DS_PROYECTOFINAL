/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author IYAC
 */
public class Producto1 {

    private String nombre;
    private String descripcion;
    private String precio;

    public Producto1() {
    }

    
    
    public Producto1(String nombre, String descripcion, String precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public boolean equals(Producto1 producto) {
        boolean nombreIgual = producto.getNombre().equals(this.nombre);
        boolean descripcionIgual = producto.getDescripcion().equals(this.descripcion);
        boolean precioIgual = producto.getPrecio().equals(this.precio);

        return nombreIgual && descripcionIgual && precioIgual;

    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

}
