/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

/**
 *
 * @author Miguel Angel
 */
public class Helper {
    
    /**
     * 
     * @param cadena
     * @return comprueba si la cadena que se pasa como parametro tiene solo numeros
     */
   public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
   
    public static boolean isNumericDouble(String cadena) {

        boolean resultado;

        try {
            Double.parseDouble(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
   
   
   
   
    public static boolean VerificacionDatosIngresados(String usuario, String contrasena, String nombres, String apellidos, String telefono, String email, String direccion, String cedula, String matricula){
        boolean afirmacion = (!usuario.equals("")&&!contrasena.equals("")&&!nombres.equals("")&&!apellidos.equals("")&&!telefono.equals("")&&!email.equals("")&&!direccion.equals("")&&!cedula.equals("")&&!matricula.equals(""));
        
        return afirmacion;
    
    }
    
    public static boolean VerificacionDatosIngresados(String nombre, String descripcion, String precio, String tiempo_max_entrega, String categoria, String cantidad){
        boolean afirmacion = (!nombre.equals("")&&!descripcion.equals("")&&isNumericDouble(precio)&&!tiempo_max_entrega.equals("")&&!categoria.equals("")&&!cantidad.equals("")&&isNumeric(cantidad)&& isNumeric(tiempo_max_entrega));
        
        return afirmacion;
    
    }
    
    
    
}
