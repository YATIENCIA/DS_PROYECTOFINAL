/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import diseñosoftware.vistas.AdministradorVista;
import modelos.Administrador;
import modelos.Producto;
import modelos.Usuario;

/**
 *
 * @author Luiggi
 */
public class AdministradorControlador {
    Administrador admin;
    AdministradorVista vista;
    
    
    public void modificarUsuario(Usuario usuario,String[] info){
        System.out.println("VIEJO USUARIO:\n"+usuario);
        usuario.setUsuario(info[0]);
        usuario.setContraseña(info[1]);
        usuario.setNombres(info[2]);
        usuario.setApellidos(info[3]);
        usuario.setTelefono(info[4]);
        usuario.setEmail(info[5]); 
        usuario.setDireccion(info[6]);
        usuario.setCedula(info[7]);
        usuario.setMatricula(info[8]);
        System.out.println("NUEVO USUARIO:\n"+usuario);
    }
    
    
    public void modificarProducto(Producto producto, String[] info){
        
        
    }
}
