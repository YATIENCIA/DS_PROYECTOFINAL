/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author IYAC
 */
public class Productos {
 private SimpleStringProperty nombre;
 private SimpleStringProperty descripcion;
 private SimpleStringProperty precio;

    public Productos(String nombre, String descripcion, String precio) {
        this.nombre=new SimpleStringProperty(nombre);
        this.descripcion=new SimpleStringProperty(descripcion);
        this.precio=new SimpleStringProperty(precio);
    }
}
