/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;

import diseñosoftware.main;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelos.ConexionSQL;
import modelos.*;
import recursos.constantes;

/**
 *
 * @author Miguel Angel
 */
public class ProductosMasBuscadosVista {
    
    
    BorderPane border;
    Button regresar;
    TableView<Producto> tabla;

    public ProductosMasBuscadosVista() {
        border = new BorderPane();
        Center();
        Top();
    }
    
    private void Top(){
    
        regresar = new Button("Regresar");
        regresar.getStylesheets().add(constantes.PathStyles);
        border.setTop(regresar);
        regresar.setOnAction(e->{
        
        Vista vista=new Vista(500, "Test");
        vista.CreateLogin();
        main.stage.setScene(vista.getScene());
        //main.stage=stage;
        main.stage.setHeight(650);
        main.stage.setWidth(1000);
        
        
        });   
    }
    
    private void Center(){
    
        
        ObservableList<Producto> lis = ConexionSQL.ProductosMasBuscadosQuemado();
        //lis = FXCollections.observableArrayList();
        tabla = Tablas.CrearProdDesc(lis);
        
        border.setCenter(tabla);
        
    
    
    }

    public BorderPane getBorder() {
        return border;
    }

    public void setBorder(BorderPane border) {
        this.border = border;
    }
    
    
    
    
    
    
    
}
