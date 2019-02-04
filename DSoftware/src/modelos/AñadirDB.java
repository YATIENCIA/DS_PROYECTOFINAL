/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author IYAC
 */
public class AñadirDB {
      public static void AñadirABusqueda(String palabra) {
        String query = "{call busquedaProdTB(?)}";
        ResultSet rs;
        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setString(1, palabra);
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
     public static void AñadirPersonaALaBase(String usuario, String contraseña, String nombres, String apellidos, String telefono, String email, String direccion, String cedula, String matricula, boolean whatsapp) {
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

    public static void AñadirCuentaALaBase(String usuario, String contraseña, String perfil) {
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
    
    public static void AnadirProductoEnLaBase(String nombre, String tiempo_max_entrega, String categoria, String costo, String cantidad, String vendedor) {
        String query = "{call ingresarProducto(?,?,?,?,?,?)}";
        ResultSet rs;
        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setString(1, nombre);
            stmt.setInt(2, Integer.parseInt(tiempo_max_entrega));
            stmt.setString(3, categoria);
            stmt.setDouble(4, Double.parseDouble(costo));
            stmt.setInt(5, Integer.parseInt(cantidad));
            stmt.setString(6, vendedor);
            rs = stmt.executeQuery();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
