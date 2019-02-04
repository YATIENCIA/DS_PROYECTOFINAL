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
public class CambiarEstadoDB {
    
    public static void CambiarEstadoCuenta(String usuario) {
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

  

    public static void CambiarEstadoProducto(int codigo) {
        String query = "{call eliminarProducto(?)}";
        ResultSet rs;
        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
