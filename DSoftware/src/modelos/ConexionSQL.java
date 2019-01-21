/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import controladores.SistemaPoliVentas;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public static void CambiarEstadoProducto(String codigo) {
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

   static public List<Producto> BuscarProductos(String palabras) {
        List<Producto> list = FXCollections.observableArrayList();
        try {

            //String nombre, Categoria categoria, double precio, String TiempoMaxEntrega, Calificacion calificacion, Vendedor vendedor
            String SQL = "select nombre, categoria, costo, tiempoMaxEntrega, calificacion, vendedor from producto where (nombre like CONCAT('%'" + palabras + "'%') or categoria like CONCAT('%'" + palabras + "'%')) and eliminado=false;";
            ResultSet rs = ConexionSQL.getConnection().createStatement().executeQuery(SQL);
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(new Categoria(rs.getString("categoria")));
                producto.setPrecio(rs.getDouble("costo"));
                producto.setTiempoMaxEntrega(rs.getString("tiempoMaxEntrega"));
                producto.setCalificacion(new Calificacion(rs.getInt("calificacion")));
                producto.setVendedor(new Vendedor(rs.getString("vendedor")));
                list.add(producto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

   
   static public ObservableList<Producto> TodosLosProductos() {
        ObservableList<Producto> list = FXCollections.observableArrayList();
        try {

            //String nombre, Categoria categoria, double precio, String TiempoMaxEntrega, Calificacion calificacion, Vendedor vendedor
            String SQL = "select nombre, categoria, costo, TiempoMaxEntrega, calificacion, vendedor from producto where eliminado=false;";
            ResultSet rs = ConexionSQL.getConnection().createStatement().executeQuery(SQL);
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(new Categoria(rs.getString("categoria")));
                producto.setPrecio(rs.getDouble("costo"));
                producto.setTiempoMaxEntrega(rs.getString("TiempoMaxEntrega"));
                producto.setCalificacion(new Calificacion(rs.getInt("calificacion")));
                producto.setVendedor(new Vendedor(rs.getString("nombre")));
                list.add(producto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   
   
   static public ObservableList<Usuario> TodosLosUsuarios() {
        ObservableList<Usuario> list = FXCollections.observableArrayList();
        try {

            //String nombre, Categoria categoria, double precio, String TiempoMaxEntrega, Calificacion calificacion, Vendedor vendedor
            String SQL = "select usuario,contraseña,rol from cuenta where eliminado=false;";
            ResultSet rs = ConexionSQL.getConnection().createStatement().executeQuery(SQL);
            while (rs.next()) {
                Usuario usuario=new Usuario();
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setTipo(rs.getString("rol"));
                list.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   
   static public void GuardarVenta(Venta v) {
   String query = "{call ingresarVenta(?,?,?,?,?)}";
        ResultSet rs;
        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter

            stmt.setString(1, SistemaPoliVentas.usuario.getCedula());
            stmt.setInt(2, v.getProducto().getId());
            stmt.setInt(3, v.getCantidad());
            stmt.setString(4, v.getEstado().toString());
            stmt.setString(5, v.getEstrategia().toString());
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}