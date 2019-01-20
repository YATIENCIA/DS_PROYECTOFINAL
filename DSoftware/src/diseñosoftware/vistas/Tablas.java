/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import modelos.Producto;
import modelos.Usuario;
import modelos.Venta;

/**
 *
 * @author IYAC
 */
public class Tablas {

    public static TableView<Producto> CrearProdDesc(ObservableList<Producto> list) {
        
        TableView<Producto> table = new TableView<Producto>();
        TableColumn<Producto, String> NameCol //
                = new TableColumn<Producto, String>("Nombre del producto");
        TableColumn<Producto, String> DescCol//
                = new TableColumn<Producto, String>("Descripción");

        NameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        NameCol.setSortType(TableColumn.SortType.DESCENDING);
        table.setItems(list);

        table.getColumns().addAll(NameCol, DescCol);

        return table;
    }
    
    public static TableView<Producto> CrearProdPrecDesc(ObservableList<Producto> list) {
        
        TableView<Producto> table = new TableView<Producto>();
        TableColumn<Producto, String> NameCol //
                = new TableColumn<Producto, String>("Nombre del producto");
        TableColumn<Producto, String> DescCol//
                = new TableColumn<Producto, String>("Descripción");
        TableColumn<Producto, String> PrecCol//
                = new TableColumn<Producto, String>("Precio");

        NameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        PrecCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
        NameCol.setSortType(TableColumn.SortType.DESCENDING);
        table.setItems(list);

        table.getColumns().addAll(NameCol, DescCol, PrecCol);

        return table;
    }
    
    
    public static TableView<Venta> CrearVentasDescFecha(ObservableList<Venta> list) 
    {
       
    TableView<Venta> table = new TableView();
    table.setEditable(true);
     TableColumn<Venta, String> PedCol//
                = new TableColumn<Venta, String>("Código del pedido");
        TableColumn<Venta, String> DescCol//
                = new TableColumn<Venta, String>("Descripción");
        TableColumn<Venta, String> DateCol//
                = new TableColumn<Venta, String>("Fecha de entrega");
        
        PedCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("FE"));
        DateCol.setSortType(TableColumn.SortType.DESCENDING);
        table.setItems(list);

        table.getColumns().addAll(PedCol, DescCol, DateCol);

        return table; 
  }
    
  public static TableView<Usuario> CrearUsuario(ObservableList<Usuario> list) 
    {
       
    TableView<Usuario> table = new TableView();
    table.setEditable(true);
     TableColumn<Usuario, String> nomCol//
                = new TableColumn<Usuario, String>("Nombre de usuario");
        TableColumn<Usuario, String> rolCol//
                = new TableColumn<Usuario, String>("Rol");
        TableColumn<Usuario, Boolean> EstadoCol//
                = new TableColumn<Usuario, Boolean>("Estado");
        
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        rolCol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        EstadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));
      
        nomCol.setSortType(TableColumn.SortType.DESCENDING);
        table.setItems(list);

        table.getColumns().addAll(nomCol, rolCol, EstadoCol);

        return table; 
  }
    
    
}