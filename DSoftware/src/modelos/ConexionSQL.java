/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionSQL {

    public static Connection cn;

    public static void ConexionSQL() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://192.168.1.1:3306/poliventas", "poliventas", "poliventas");
            System.out.println("Conexión exitosa!");
        } catch (SQLException ex) {
            System.out.println("No se pudo conectar a la base");
        }
    }

    public static Connection getConnection() {
        try {
            if (cn.isClosed()) {
                cn = DriverManager.getConnection("jdbc:mysql://192.168.1.1:3306/poliventas", "poliventas", "poliventas");

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return cn;
    }
    }
