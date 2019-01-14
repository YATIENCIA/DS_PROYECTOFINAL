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
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
/**
 *
 * @author IYAC
 */
public class VendedorVista extends CompradorVista{
   // Pane menuV=new Pane();
    Pane VentasPendientes=new Pane();
    Pane MisProductos=new Pane();
    
    
    public VendedorVista(int tamañoVentana, String titulo) {
        super(tamañoVentana, titulo);

    }
    
    @Override
    public void CreateScene()
    {
        super.CreateScene();
        Tab tab_vp = new Tab("Ventas pendientes");
        Tab tab_mp = new Tab("Mis productos");
        tabPane.getTabs().addAll(tab_vp, tab_mp);
        CrearVentasPendientes();
        CrearMisProductos();
        //Final
        tab_vp.setContent(VentasPendientes);
        tab_mp.setContent(MisProductos);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
       // menuC.getChildren().add(tabPane);  
    }
    
       public void CrearMisProductos()
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
        
        MisProductos.getChildren().add(v);
        
    }
    
       
       public void CrearVentasPendientes(){
        VBox v=new VBox();

        TableView table = new TableView();
        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Ventas");
        TableColumn lastNameCol = new TableColumn("Descripción");
        TableColumn emailCol = new TableColumn("Fecha de entrega");
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Ver mapa");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Anular");
        cm.getItems().add(mi2);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    cm.show(table, t.getScreenX(), t.getScreenY());
                }
            }
        });
        
        v.getChildren().addAll(table);
        VentasPendientes.getChildren().add(v);

    }
     
}
