/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author IYAC
 */
public class AdministradorVista extends Vista{

    Pane AdminUsu=new Pane();
    Pane AdminProductos=new Pane();
    
    public AdministradorVista(int tamañoVentana, String titulo) {
        super(tamañoVentana, titulo);
  
    }
    
    @Override
    public void CreateScene()
    {
        Label titulo=new Label("Menú Comprador");
        TabPane tabPane = new TabPane();
        Tab tab_au = new Tab("Administrar usuarios");
        Tab tab_ap = new Tab("Administrar productos");
        tabPane.getTabs().addAll(tab_au, tab_ap);
        CrearAdministrarUsuarios();
        CrearAdministrarProductos();
        //Final
        tab_au.setContent(AdminUsu);
        tab_ap.setContent(AdminProductos);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        menu.getChildren().add(tabPane);   
    }
    
    public void CrearAdministrarUsuarios()
    {
        //CRUD sobre usuarios y producto
          VBox v=new VBox();

        TableView table = new TableView();
        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Usuarios");
        TableColumn lastNameCol = new TableColumn("Descripción");
        table.getColumns().addAll(firstNameCol, lastNameCol);
        
        Button CrearUsu=new Button("Crear nuevo usuario");
        v.getChildren().addAll(CrearUsu, table);
        
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Modificar");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Eliminar");
        cm.getItems().add(mi2);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    cm.show(table, t.getScreenX(), t.getScreenY());
                }
            }
        });
        
        AdminUsu.getChildren().add(v);
        
    }
    
      public void CrearAdministrarProductos()
    {
        //CRUD sobre usuarios y producto
          VBox v=new VBox();

        TableView table = new TableView();
        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Productos");
        TableColumn lastNameCol = new TableColumn("Descripción");
        table.getColumns().addAll(firstNameCol, lastNameCol);
        
        Button CrearPro=new Button("Crear nuevo producto");
        v.getChildren().addAll(CrearPro, table);
        
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Modificar");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Eliminar");
        cm.getItems().add(mi2);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    cm.show(table, t.getScreenX(), t.getScreenY());
                }
            }
        });
        
        AdminProductos.getChildren().add(v);
        
    }
}
