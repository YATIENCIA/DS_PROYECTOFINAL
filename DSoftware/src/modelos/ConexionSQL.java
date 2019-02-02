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
import java.sql.Types;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConexionSQL {

    public static Connection cn;

    public static void ConexionSQL() {
        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poliventas", "root", "123456789");
            System.out.println("Conexión exitosa!");
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if (cn.isClosed()) {
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poliventas", "root", "123456789");

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

    public static void AñadirABusqueda(String palabra) {
        String query = "{call busquedaProd(?)}";
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

    static public ObservableList<Producto> ProductosMasBuscados() {
        ObservableList<Producto> list = FXCollections.observableArrayList();
   String query = "select nombre, costo, sq.cantidad as cantidadBusqueda "
                + "from producto join (select count(numero) as cantidad, idProd from busqueda "
                + "group by idProd) sq on producto.idproducto=sq.idProd order by cantidad desc limit 15;";

        try {
            Connection conn = ConexionSQL.getConnection();
            CallableStatement stmt = conn.prepareCall(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("costo"));
                list.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
/*
    static public ObservableList<Producto> ProductosMasBuscadosQuemado() {

        ObservableList<Producto> list = FXCollections.observableArrayList();

        Producto p1 = new Producto();
        p1.setNombre("Camiseta");
        p1.setCategoria(null);

        Producto p2 = new Producto();
        p2.setNombre("Pantaloneta");
        p2.setCategoria(null);

        list.add(p1);
        list.add(p2);

        return list;

    }
*/
    static public List<Producto> BuscarProductos(String palabras) {
        System.out.println("palabras   "+palabras);
        List<Producto> list = FXCollections.observableArrayList();
        try {

              String SQL = "select nombre, categoria, costo, tiempoMaxEntrega, "
                    + "calificacion, vendedor from producto where (nombre like '%" + palabras + "%' "
                    + "or categoria like '%" + palabras + "%') and eliminado=false;";
            
            ResultSet rs = ConexionSQL.getConnection().createStatement().executeQuery(SQL);
            while (rs.next()) {
                System.out.println("RRKFF"+rs.toString());
                Producto producto = new Producto();
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(new Categoria(rs.getString("categoria")));
                producto.setPrecio(rs.getDouble("costo"));
               
                producto.setTiempoMaxEntrega(rs.getString("tiempoMaxEntrega"));
                producto.setCalificacion(new Calificacion(rs.getInt("calificacion")));
                System.out.println("VENDEDOROROROR"+rs.getString("vendedor"));
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
            String SQL = "select cedula,c.usuario,contraseña,rol from cuenta c, persona p where p.usuario=c.usuario and c.eliminado=false;";
            ResultSet rs = ConexionSQL.getConnection().createStatement().executeQuery(SQL);
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCedula(rs.getString("cedula"));
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
        try {
            String query = "{call ingresarPedido(?,?,?,?,?)}";
            ResultSet rs;
            Connection conn = ConexionSQL.getConnection();
            CallableStatement stmt = conn.prepareCall(query);
            //Set IN parameter
            stmt.setString(1, SistemaPoliVentas.usuario.getCedula());
            stmt.setInt(2, v.getProducto().getId(conn));
            stmt.setInt(3, v.getCantidad());
            stmt.setString(4, v.getEstado().toString());
            stmt.setInt(5, ConexionSQL.getID(v.getEstrategia().toString()));
            rs = stmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println("se cae el programa");
            System.out.println(ex.getMessage());
        }
    }

    static public int getID(String estrategia) {
        int i = 0;
        try {
            String query = "{call obtenerIDestrategia(?,?)}";
            ResultSet rs;
            Connection conn = ConexionSQL.getConnection();
            CallableStatement stmt = conn.prepareCall(query);
            //Set IN parameter
            stmt.setString(1, estrategia);
            stmt.registerOutParameter(2, Types.INTEGER);
            rs = stmt.executeQuery();
            i = stmt.getInt("idout");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
}
