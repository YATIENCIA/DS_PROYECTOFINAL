/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import modelos.Productos;
import modelos.Usuarios;


/**
 *
 * @author IYAC
 */
public class AdministradorVista extends Vista{

    BorderPane AdminUsu=new BorderPane();
    BorderPane AdminProductos=new BorderPane();
    
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
        
        menu.setAlignment(Pos.CENTER);
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
        mi1.setOnAction(Notificacion("modificar", "usuario"));
        mi2.setOnAction(Notificacion("eliminar", "usuario"));
        
        //Creo filas modelo
        ObservableList<Usuarios> usuarios = FXCollections.observableArrayList(
            new Usuarios("David", "Vendedor"),
            new Usuarios("Kira", "Comprador"));
        table.setItems(usuarios);
        
        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    cm.show(table, t.getScreenX(), t.getScreenY());
                }
            }
        });
//        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
        AdminUsu.setCenter(v);
      
        
    }
    
      public void CrearAdministrarProductos()
    {
        //CRUD sobre usuarios y producto
          VBox v=new VBox();

        TableView<Productos> table = new TableView();
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
        
        mi1.setOnAction(Notificacion("modificar", "producto"));
        mi2.setOnAction(Notificacion("eliminar", "producto"));
        
         //Creo filas modelo
        ObservableList<Productos> productos = FXCollections.observableArrayList(
            new Productos("Cuaderno", "Azul universitario", "10.99"),
            new Productos("Cuaderno", "Verde universitario", "4.99"));
        table.setItems(productos);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    cm.show(table, t.getScreenX(), t.getScreenY());
                }
            }
        });
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
        AdminProductos.setCenter(v);
        
    }
      
}
