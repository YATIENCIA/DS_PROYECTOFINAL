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
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poliventas", "root", "vegeta10");
            System.out.println("Conexión exitosa!");
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if (cn.isClosed()) {
                cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/poliventas", "root", "vegeta10");

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
        String query = "select nombre, tiempoMaxEntrega, categoria, calificacion, vendedor, costo, sq.cantidad as cantidadBusqueda "
                + "from producto join (select count(numero) as cantidad, idProd from busqueda "
                + "group by idProd) sq on producto.idproducto=sq.idProd order by cantidad desc limit 15;";

        try {
            Connection conn = ConexionSQL.getConnection();
            CallableStatement stmt = conn.prepareCall(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(new Categoria(rs.getString("categoria")));
                producto.setCalificacion(new Calificacion(rs.getInt("calificacion")));
                producto.setVendedor(ConexionSQL.getVendedorByID(rs.getString("vendedor")));
                producto.setTiempoMaxEntrega(rs.getString("tiempoMaxEntrega"));
                producto.setPrecio(rs.getDouble("costo"));
                list.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    static public List<Producto> BuscarProductos(String palabras) {
        List<Producto> list = FXCollections.observableArrayList();
        try {

            String SQL = "select nombre, categoria, costo, tiempoMaxEntrega, "
                    + "calificacion, vendedor from producto where (nombre like '%" + palabras + "%' "
                    + "or categoria like '%" + palabras + "%') and eliminado=false;";

            ResultSet rs = ConexionSQL.getConnection().createStatement().executeQuery(SQL);
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setNombre(rs.getString("nombre"));
                producto.setCategoria(new Categoria(rs.getString("categoria")));
                producto.setPrecio(rs.getDouble("costo"));
                producto.setTiempoMaxEntrega(rs.getString("tiempoMaxEntrega"));
                producto.setCalificacion(new Calificacion(rs.getInt("calificacion")));
                producto.setVendedor(ConexionSQL.getVendedorByID(rs.getString("vendedor")));
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
                usuario.setContrasena(rs.getString("contraseña"));
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


    static public Vendedor getVendedorByID(String id) {
        Vendedor v = new Vendedor();
        try {
            String query = "{call getVendedor(?,?,?,?,?,?,?,?,?)}";
            ResultSet rs;
            Connection conn = ConexionSQL.getConnection();
            CallableStatement stmt = conn.prepareCall(query);
            //Set IN parameter
            stmt.setString(1, id);
            stmt.registerOutParameter(2, Types.VARCHAR);
            stmt.registerOutParameter(3, Types.VARCHAR);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.VARCHAR);
            stmt.registerOutParameter(6, Types.VARCHAR);
            stmt.registerOutParameter(7, Types.VARCHAR);
            stmt.registerOutParameter(8, Types.DOUBLE);
            stmt.registerOutParameter(9, Types.VARCHAR);
            rs = stmt.executeQuery();
            String nombres = stmt.getString("nombresin");
            String apellidos = stmt.getString("apellidosin");
            String numero = stmt.getString("numeroin");
            String correo = stmt.getString("correoin");
            String direccion = stmt.getString("direccionin");
            String matricula = stmt.getString("matriculain");
            Double saldo = stmt.getDouble("saldoin");
            String usuario = stmt.getString("usuarioin");
            v.setNombres(nombres);
            v.setApellidos(apellidos);
            v.setCedula(id);
            v.setDireccion(direccion);
            v.setEmail(correo);
            v.setMatricula(matricula);
            v.setTelefono(numero);
            v.setUsuario(usuario);
            //Sañadir saldo a usuario
              } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return v;
    }
    
    public static void ModificarPersonaEnLaBase(String usuario, String nombres, String apellidos, String telefono, String email, String direccion, String cedula, String matricula, boolean whatsapp) {
        String query = "{call modificarUsuario(?,?,?,?,?,?,?,?)}";
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
            stmt.setString(8, usuario);
            rs = stmt.executeQuery();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void ModificarUsuarioEnLaBase(String usuario, String contrasena) {
        String query = "{call modificarDatosCuenta(?,?)}";
        ResultSet rs;
        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            rs = stmt.executeQuery();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void ModificarProductoEnLaBase(String nombre, String tiempo_max_entrega, String categoria, String costo, String cantidad, String vendedor) {
        String query = "{call modificarProducto(?,?,?,?,?,?)}";
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
    
    
    

    static public ObservableList<Venta> PedidosPendientes() {
        ObservableList<Venta> list = FXCollections.observableArrayList();
        String query = "{call getPedidosPendientes()}";
        try {
            ResultSet rs = ConexionSQL.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                Venta venta=new Venta();
                Producto p=ConexionSQL.getProductoByID(rs.getString("producto"));
                venta.setComprador(ConexionSQL.getVendedorByID(rs.getString("comprador")));
                venta.setProducto(p);
                venta.setCantidad(rs.getInt("cantidad"));
                
                list.add(venta);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    
    static public Producto getProductoByID(String id) {
        Producto p=new Producto();
        try {
            String query = "{call getProducto(?,?,?,?,?,?)}";
            ResultSet rs;
            
            Connection conn = ConexionSQL.getConnection();
            CallableStatement stmt = conn.prepareCall(query);
            //Set IN parameter
            stmt.setString(1, id);
            stmt.registerOutParameter(2, Types.VARCHAR);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.registerOutParameter(4, Types.VARCHAR);
            stmt.registerOutParameter(5, Types.DOUBLE);
            stmt.registerOutParameter(6, Types.INTEGER);
           
            rs = stmt.executeQuery();
            String nombres = stmt.getString("nombrein");
            int tiempo = stmt.getInt("tiempoin");
            String categoria = stmt.getString("categoriain");
            double costo = stmt.getDouble("costoin");
            int disponible= stmt.getInt("disponiblein");
            p.setNombre(nombres);
            p.setCategoria(new Categoria(categoria));
            p.setPrecio(costo);
            p.setTiempoMaxEntrega(String.valueOf(tiempo));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    
}
