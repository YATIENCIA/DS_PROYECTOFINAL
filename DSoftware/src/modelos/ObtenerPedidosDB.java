/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author IYAC
 */
public class ObtenerPedidosDB {
    static public ObservableList<Venta> PedidosPendientes() {
        ObservableList<Venta> list = FXCollections.observableArrayList();
        String query = "{call getPedidosPendientes()}";
        try {
            ResultSet rs = ConexionSQL.getConnection().createStatement().executeQuery(query);
            while (rs.next()) {
                Venta venta=new Venta();
                Producto p=ObtenerDatosDB.getProductoByID(rs.getInt("producto"));
                venta.setComprador(ObtenerDatosDB.getVendedorByID(rs.getString("comprador")));
                venta.setProducto(p);
                venta.setCantidad(rs.getInt("cantidad"));
                if(p.getNombre()!=null)
                     list.add(venta);
            }
        } catch (SQLException ex) {
        }
        return list;
    }
    
}
