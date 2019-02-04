/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import diseñosoftware.main;
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
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    public static Usuario usuario;
    public static Vista vista;

    public static void IngresarAlSistema(String Usuario, String contraseña) {
        vista=null;
        //Búsqueda en la base
        usuario = ObtenerUsuario(Usuario, contraseña);
        if (usuario instanceof Vendedor) {
            VendedorVista vv = new VendedorVista(50, "CV");
            vista = vv;
            vista.CreateScene();
        }
        else if (usuario instanceof Comprador) {
            CompradorVista vv = new CompradorVista(50, "CV");
            vista = vv;
            vista.CreateScene();
        }
        else if (usuario instanceof Administrador) {
            AdministradorVista av = new AdministradorVista(50, "AV");
            vista = av;
            vista.CreateScene();
        }
        else 
        {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cuadro de confirmación");
                alert.setHeaderText("");
                alert.setContentText("¿Desea registrarse en PoliVentas?");
                Optional<ButtonType> result = alert.showAndWait();
                if (!(result.get() == ButtonType.OK)){
                     SistemaPoliVentas.vista=new Vista(50, "Vista");
                    SistemaPoliVentas.vista.CreateLogin();
                    main.stage.setScene(SistemaPoliVentas.vista.getScene());
                } 
                else{
                    NuevoUsuario nu = new NuevoUsuario(50, "av");
                    vista = nu;
                    vista.CreateScene();
                }
                
        }
        
    }
    
    

    public static Usuario ObtenerUsuario(String usuario, String contraseña) {
        String query = "{call obtenerRol(?,?,?)}";
        ResultSet rs;

        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);
            //Set OUT parameter
            stmt.registerOutParameter(3, Types.VARCHAR);
            rs = stmt.executeQuery();
            String rol = stmt.getString(3);
            Usuario u = getUsuario(rol, usuario, contraseña);
            return u;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private static Usuario getUsuario(String rol, String usu, String contraseña) {
        Usuario usuario=new Usuario();
        if (rol != null) {
            switch (rol) {
                case "Vendedor":
                    usuario = new Vendedor();
                    break;
                case "Comprador":
                    usuario = new Comprador();
                    break;
                case "Administrador":
                    usuario = new Administrador();
                    break;
            }
        }
        usuario.setUsuario(usu);
        usuario.setContrasena(contraseña);
        usuario=getByUsuario(usuario);
        return usuario;
    }
    
    
    private static Usuario getByUsuario(Usuario usuario)
    {
        String query = "{call obtenerCedula(?,?,?,?)}";
        ResultSet rs;
        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setString(1, usuario.getUsuario());
            stmt.registerOutParameter(2, Types.VARCHAR);
            stmt.registerOutParameter(3, Types.VARCHAR);
            stmt.registerOutParameter(4, Types.VARCHAR);
            
            rs = stmt.executeQuery();
            String cedula=stmt.getString(2);
            String nombres=stmt.getString(3);
            String apellidos=stmt.getString(4);
            usuario.setCedula(cedula);
            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return usuario;
    }
}
