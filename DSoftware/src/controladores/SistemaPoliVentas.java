/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import diseñosoftware.vistas.AdministradorVista;
import diseñosoftware.vistas.CompradorVista;
import diseñosoftware.vistas.NuevoUsuario;
import diseñosoftware.vistas.VendedorVista;
import diseñosoftware.vistas.Vista;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import modelos.Administrador;
import modelos.Comprador;
import modelos.ConexionSQL;
import modelos.Usuario;
import modelos.Vendedor;

/**
 *
 * @author IYAC
 */
public class SistemaPoliVentas {
    
   public static  Usuario usuario;
   public static Vista vista;
    
        
    public static void IngresarAlSistema(String Usuario, String contraseña)
    {
        //Búsqueda en la base
        usuario=ObtenerUsuario(Usuario, contraseña);
        //Vista vista = new AdministradorVista(50, "AV");
        vista=new Vista(50,"AV");
        if(usuario instanceof Comprador)
        {
            CompradorVista cv = new CompradorVista(50, "CV");
                vista = cv;
        }
        if(usuario instanceof Vendedor)
        {
             VendedorVista vv = new VendedorVista(50, "CV");
                vista = vv;
        }
        if(usuario instanceof Administrador)
        {
            AdministradorVista av = new AdministradorVista(50, "AV");
            vista = av;
        }
        if(usuario==null)
        {
            NuevoUsuario nu=new NuevoUsuario(50,"av");
            vista=nu;
        }
        vista.CreateScene(); 
    }
    
    public static Usuario ObtenerUsuario(String usuario, String contraseña)
    {
        String query = "{call obtenerRol(?,?,?)}";
        ResultSet rs;
        Usuario u =new Usuario();
        
        try (Connection conn = ConexionSQL.getConnection();
            CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setString(1, usuario);  
            stmt.setString(2, contraseña);  
            //Set OUT parameter
            stmt.registerOutParameter(3, Types.VARCHAR);
            rs = stmt.executeQuery();
            String rol=stmt.getString(3);
            u=getUsuario(rol);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }
    
    private static Usuario  getUsuario(String rol)
    {
        Usuario usuario=new Usuario();
        switch(rol)
        {
            case "Vendedor":
                usuario=new Vendedor();
                break;
            case "Comprador":
                usuario=new Comprador();
                break;
            case "Administrador":
                usuario=new Administrador();
                break;
        }
        return usuario;
    }
}
