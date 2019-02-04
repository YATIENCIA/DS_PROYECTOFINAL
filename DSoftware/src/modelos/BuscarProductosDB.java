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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author IYAC
 */
public class BuscarProductosDB {
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
                producto.setVendedor(ObtenerDatosDB.getVendedorByID(rs.getString("vendedor")));
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
                producto.setVendedor(ObtenerDatosDB.getVendedorByID(rs.getString("vendedor")));
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
}
