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
                = new TableColumn<Producto, String>("Categoria");

        NameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        NameCol.setSortType(TableColumn.SortType.DESCENDING);
        table.setItems(list);

        table.getColumns().addAll(NameCol, DescCol);

        return table;
    }
    
     public static TableView<Producto> CrearProdPrec(ObservableList<Producto> list) {
        
        TableView<Producto> table = new TableView<Producto>();
        TableColumn<Producto, String> NameCol //
                = new TableColumn<Producto, String>("Nombre del producto");
        TableColumn<Producto, Double> DescCol//
                = new TableColumn<Producto, Double>("Precio");

        NameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
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
                = new TableColumn<Producto, String>("Categoria");
        TableColumn<Producto, Double> PrecCol//
                = new TableColumn<Producto, Double>("Precio");

        NameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("categoria"));
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
                = new TableColumn<Venta, String>("Producto");
        TableColumn<Venta, Integer> DescCol//
                = new TableColumn<Venta, Integer>("Cantidad");

        
        PedCol.setCellValueFactory(new PropertyValueFactory<>("producto"));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        table.setItems(list);

        table.getColumns().addAll(PedCol, DescCol);

        return table; 
  }
    
  public static TableView<Usuario> CrearUsuario(ObservableList<Usuario> list) 
    {
       
    TableView<Usuario> table = new TableView();
    table.setEditable(true);
     TableColumn<Usuario, String> nomCol//
                = new TableColumn<Usuario, String>("Cédula");
        TableColumn<Usuario, String> rolCol//
                = new TableColumn<Usuario, String>("Usuario");
        TableColumn<Usuario, Boolean> EstadoCol//
                = new TableColumn<Usuario, Boolean>("Tipo");
        
        nomCol.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        rolCol.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        EstadoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
      
        nomCol.setSortType(TableColumn.SortType.DESCENDING);
        table.setItems(list);

        table.getColumns().addAll(nomCol, rolCol, EstadoCol);

        return table; 
  }
    
    
}