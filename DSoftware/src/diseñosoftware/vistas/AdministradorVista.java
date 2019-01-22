/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;


import controladores.AdministradorControlador;
import static diseñosoftware.vistas.Vista.NotificacionEliminarProducto;
import static diseñosoftware.vistas.Vista.NotificacionEliminarUsuario;
import static diseñosoftware.vistas.Vista.NotificacionModificarProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    AdministradorControlador controlador;

    public AdministradorVista(int tamañoVentana, String titulo) {
        super(tamañoVentana, titulo);
        controlador = new AdministradorControlador();

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
                     //mi1.setOnAction(NotificacionModificarUsuario("usuario", usuario, table));
                     mi1.setOnAction(e-> bmodificarUsuario(usuario));
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
    
    
    public void bmodificarUsuario(Usuario usuario){
        Stage stageDialog = new Stage();
         //crear el stageDialog para la ventana de dialogo
        stageDialog.setTitle("Modificar USUARIO");
        //decirle al stageDialog que se comporte como un pop-up (Modal)
        //stageDialog.initModality(Modality.WINDOW_MODAL);
        
        GridPane grid= new GridPane();
        
        Button aceptar = new Button("ACEPTAR");
        //aceptar.setOnAction(e->controlador.modificarUsuario(usuario));

        grid.add(aceptar,0,4);
        
        Scene scene2 = new Scene(grid,250,250);
        
        stageDialog.setScene(scene2);
        
        modificarUsuarioVista(usuario,scene2);
        // Mostrar el dialogo y esperar hasta que el usuario cierra la venta
        stageDialog.showAndWait();
        
       
        
        
    }
    
    public void modificarUsuarioVista(Usuario user, Scene scene){
        GridPane grid= new GridPane();
        Button modificar = new Button("Modificar");
           

        grid.add(new Label("Nombre:"), 1, 0);
        grid.add(new Label("Apellidos:"), 1, 1);
        grid.add(new Label("Usuario:"), 1, 2);
        grid.add(new Label("Contraseña:"), 1, 3);
        grid.add(new Label("Cedula:"), 1, 4);
        grid.add(new Label("Teléfono:"), 1, 5);
        grid.add(new Label("email:"), 1, 6);
        grid.add(new Label("Dirección:"), 1, 7);
        grid.add(new Label("Whatsapp:"), 1, 8);
        grid.add(new Label("Matricula:"), 1, 9);
        grid.add(new Label("Perfil:"), 1, 10);
        
        TextField c_nombre = new TextField();
        TextField c_apellido = new TextField();
        TextField c_usuario = new TextField();
        TextField c_contraseña = new TextField();
        TextField c_cedula = new TextField();
        TextField c_telefono = new TextField();
        TextField c_email = new TextField();
        TextField c_direccion = new TextField();
        TextField c_mat = new TextField();
        
        grid.add(c_nombre, 4, 0);
        grid.add(c_apellido, 4, 1);
        grid.add(c_usuario, 4, 2);
        grid.add(c_contraseña, 4, 3);
        grid.add(c_cedula, 4, 4);
        grid.add(c_telefono, 4, 5);
        grid.add(c_email, 4, 6);
        grid.add(c_direccion, 4, 7);
        grid.add(c_mat, 4, 9);

        grid.add(modificar, 0, 9);

        modificar.setOnAction(e-> {
            String tnombres= c_nombre.getText();
            String tapellidos=c_apellido.getText();
            String tusuario=c_usuario.getText();
            String tcontraseña=c_contraseña.getText();
            String tcedula=c_cedula.getText();
            String ttelefono=c_telefono.getText();
            String temail=c_email.getText();
            String tdireccion=c_direccion.getText();
            String tmat=c_mat.getText();
            String[] info ={tusuario, tcontraseña, tnombres, tapellidos, ttelefono, temail, tdireccion, tcedula, tmat};

            controlador.modificarUsuario(user,info);
            //stageDialog.close();
                });
        
        scene.setRoot(grid);
    }

    public void bModificarProducto(Producto producto){
        Stage stageDialog = new Stage();
         //crear el stageDialog para la ventana de dialogo
        stageDialog.setTitle("MODIFICAR PRODUCTO");
        //decirle al stageDialog que se comporte como un pop-up (Modal)
        //stageDialog.initModality(Modality.WINDOW_MODAL);
        
        GridPane grid= new GridPane();
        Label lNombreProducto = new Label("Nombre");
        Label lDescripcionProducto = new Label("Descripcion");
        Label lPecioProducto = new Label("Precio");
        
        TextField tNombreProducto= new TextField();
        TextField tDescripcionProducto= new TextField();
        TextField tPrecioProducto= new TextField();
        
        grid.add(lNombreProducto, 0, 0);
        grid.add(lDescripcionProducto, 0, 1);
        grid.add(lPecioProducto, 0, 2);
        grid.add(tNombreProducto, 1, 0);
        grid.add(tDescripcionProducto, 1, 1);
        grid.add(tPrecioProducto, 1, 2);
        
        Button aceptar = new Button("ACEPTAR");
        aceptar.setOnAction(e->{
            String nombre= tNombreProducto.getText();
            String descripcion=tDescripcionProducto.getText();
            String precio=tPrecioProducto.getText();
            
            String[] info= {nombre,descripcion,precio};
            controlador.modificarProducto(producto, info);
            
                });

        grid.add(aceptar,0,4);
        
        Scene scene2 = new Scene(grid,250,250);

        stageDialog.setScene(scene2);
                
        // Mostrar el dialogo y esperar hasta que el usuario cierra la venta
        stageDialog.showAndWait();
        
       
        
        
    }
    
}
