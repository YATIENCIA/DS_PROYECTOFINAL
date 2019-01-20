/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionSQL {

    public static Connection cn;

    public void ConexionSQL() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/poliventas", "root", "123456789");
            System.out.println("Conexión exitosa!");
        } catch (SQLException ex) {
            System.out.println("ERROR");
        }
    }

    public static Connection getConnection() {
        try {
            if (cn.isClosed()) {
               cn = DriverManager.getConnection("jdbc:mysql://localhost/poliventas", "root", "123456789");            
            }
        } catch (SQLException ex) {

        }
        return cn;
    }
}
