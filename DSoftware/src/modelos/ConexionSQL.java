/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionSQL {

    public static Connection cn;

    public void ConexionSQL() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/poliventas", "root", "root");
            System.out.println("Conexión exitosa!");
        } catch (SQLException ex) {
            System.out.println("ERROR");
        }
    }

    public static Connection getConnection() {
        try {
            if (cn.isClosed()) {
               cn = DriverManager.getConnection("jdbc:mysql://localhost/poliventas", "root", "root");            
            }
        } catch (SQLException ex) {

        }
        return cn;
    }
    
    
    public static void AñadirPersonaALaBase(String usuario, String contraseña, String nombres, String apellidos, String telefono, String email, String direccion, String cedula, String matricula, boolean whatsapp)
    {
        String query = "{call ingresarPersona(?,?,?,?,?,?,?,?,?)}";
        ResultSet rs;
        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setString(1, cedula);
            stmt.setString(2, nombres);
            stmt.setString(3, apellidos);
            stmt.setString(4, telefono);
            stmt.setString(5, email);
            stmt.setString(6, direccion);
            stmt.setString(7, matricula);
            stmt.setDouble(8, 0.0);
            stmt.setString(9, usuario);
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public static void AñadirCuentaALaBase(String usuario, String contraseña, String perfil)
    {
        String query = "{call ingresarCuenta(?,?,?)}";
        ResultSet rs;
        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);
            stmt.setString(3, perfil);
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
      public static void CambiarEstadoCuenta(String usuario)
    {
        String query = "{call eliminarCuenta(?)}";
        ResultSet rs;
        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
      
       public static void CambiarEstadoProducto(String codigo)
    {
        String query = "{call eliminarProducto(?)}";
        ResultSet rs;
        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
