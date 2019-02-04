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
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author IYAC
 */
public class ObtenerDatosDB {
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
        }
        return v;
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
        }
        return p;
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
        }
        return list;
    }

}
