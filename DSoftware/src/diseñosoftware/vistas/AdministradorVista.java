/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;


import static diseñosoftware.vistas.Vista.NotificacionEliminarProducto;
import static diseñosoftware.vistas.Vista.NotificacionModificarProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import modelos.ConexionSQL;
import modelos.Producto;
import modelos.Usuario;
import recursos.constantes;

/**
 *
 * @author IYAC
 */
public class AdministradorVista extends Vista {

    BorderPane AdminUsu = new BorderPane();
    BorderPane AdminProductos = new BorderPane();

    public AdministradorVista(int tamañoVentana, String titulo) {
        super(tamañoVentana, titulo);

    }

    @Override
    public void CreateScene() {
        Label titulo = new Label("Menú Comprador");
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

    public void CrearAdministrarUsuarios() {
        //CRUD sobre usuarios y producto
        VBox v = new VBox();

        //Creo filas modelo
        ObservableList<Usuario> list =ConexionSQL.TodosLosUsuarios();
        for(Usuario u: list)
        {
            System.out.println(u);
        }
        TableView table = Tablas.CrearUsuario(list);

        Button CrearUsu = new Button("Crear nuevo usuario");
        v.getChildren().addAll(CrearUsu, table);
        v.setSpacing(15);
        CrearUsu.setOnAction(crearNuevoUsuario());

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Modificar");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Eliminar");
        cm.getItems().add(mi2);
       

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(table, t.getScreenX(), t.getScreenY());
                     Usuario usuario = (Usuario) table.getSelectionModel().getSelectedItem();
                     mi1.setOnAction(NotificacionModificarUsuario("usuario", usuario, table));
                     mi2.setOnAction(NotificacionEliminarUsuario("usuario", usuario, table));
                }
            }
        });
//        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
        AdminUsu.setCenter(v);

    }

    

    
    public EventHandler<ActionEvent> crearNuevoUsuario() {
        EventHandler<ActionEvent> e = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                NuevoUsuario nu = new NuevoUsuario(50, "Nuevo Usuario");
                nu.CreateScene();
                BorderPane general = new BorderPane();
                general.setCenter(nu.menu);
                menu.getChildren().clear();
                menu.getChildren().add(general);
                scene.setRoot(menu);
            }

        };
        return e;
    }

    public void CrearAdministrarProductos() {
        //CRUD sobre usuarios y producto
        VBox v = new VBox();
        ObservableList<Producto> list = ConexionSQL.TodosLosProductos();
        TableView table = Tablas.CrearProdPrecDesc(list);
        Button CrearPro = new Button("Crear nuevo producto");
        v.getChildren().addAll(CrearPro, table);

        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Modificar");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Eliminar");
        cm.getItems().add(mi2);


        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    cm.show(table, t.getScreenX(), t.getScreenY());
                     Producto producto = (Producto) table.getSelectionModel().getSelectedItem();
                    mi1.setOnAction(NotificacionModificarProducto("producto",producto, table));
                   mi2.setOnAction(NotificacionEliminarProducto("producto",producto, table));
                }
            }
        });
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
        AdminProductos.setCenter(v);

    }

}
