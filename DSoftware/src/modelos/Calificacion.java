/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.logging.Logger;

/**
 *
 * @author IYAC
 */
public class Calificacion {
    private int calificacion;

    public Calificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }
    @Override
    public String toString() {
        return Integer.toString(calificacion);
    }
    
}
