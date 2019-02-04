/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;

import controladores.SistemaPoliVentas;
import diseñosoftware.main;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modelos.ConexionSQL;
import modelos.Producto;
import modelos.Usuario;
import recursos.constantes;

/**
 *
 * @author IYAC
 */
public class Vista {

    TextField tname = new TextField();
    PasswordField tcontra = new PasswordField();
    
    protected Scene scene;
    protected int tamañoVentana;
    protected String titulo;
    protected StackPane menu = new StackPane();
    public boolean isLogin;
    
    public Vista(int tamañoVentana, String titulo) {

        this.scene = new Scene(menu, 1000, 600);
        this.tamañoVentana = tamañoVentana;
        this.titulo = titulo;
        this.setFondo(menu);

    }

    public Scene getScene() {
        return scene;
    }
    
  
    public  void CreateLogin() {

        StackPane pane = new StackPane();
        VBox login = new VBox();
        HBox names = new HBox();
        HBox contras = new HBox();
        Button blogin = new Button("Login");
        Button productosMasBuscados = new Button("Productos Mas buscados");

        blogin.getStylesheets().add(constantes.PathStyles);
        productosMasBuscados.getStylesheets().add(constantes.PathStyles);
        Label lname = new Label("Ingrese su usuario: ");
        Label lcontra = new Label("Ingrese su contraseña: ");

        lname.getStylesheets().add(constantes.PathStyles);
        lcontra.getStylesheets().add(constantes.PathStyles);
        names.getChildren().addAll(lname, tname);
        contras.getChildren().addAll(lcontra, tcontra);
        login.getChildren().addAll(names, contras, blogin,productosMasBuscados);

        login.setAlignment(Pos.CENTER);
        login.setSpacing(5);
        names.setAlignment(Pos.CENTER);
        contras.setAlignment(Pos.CENTER);
        blogin.setAlignment(Pos.CENTER);
        pane.getChildren().add(login);
        this.setFondo(pane);
        scene.setRoot(pane);
        blogin.setOnAction(BLoginEH());
        
        
        
        productosMasBuscados.setOnAction(e->{
        
        
            ProductosMasBuscadosVista pmbx = new ProductosMasBuscadosVista();
            scene.setRoot(pmbx.getBorder());
            
            
        
        
        });
        
        
        
        
    }

    public EventHandler<ActionEvent> BLoginEH() {
        EventHandler<ActionEvent> EH = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SistemaPoliVentas.IngresarAlSistema(tname.getText(), tcontra.getText());
                Vista vista = SistemaPoliVentas.vista;
                if(vista!=null)
                {
                    tname.setText("");
                    tcontra.setText("");
                    Button cerrar = new Button("Salir");
                    cerrar.getStylesheets().add(constantes.PathStyles);
                    HBox cerrar_hbox = new HBox();
                    cerrar_hbox.getChildren().add(cerrar);
                    cerrar_hbox.setAlignment(Pos.TOP_RIGHT);
                    BorderPane general = new BorderPane();
                    general.setCenter(vista.menu);
                    general.setTop(cerrar_hbox);

                    cerrar.setOnAction(CerrarSesionEH());
                    menu.getChildren().clear();
                    menu.getChildren().add(general);
                    scene.setRoot(menu);
                }
            }
        };
        return EH;
    }

    public EventHandler<ActionEvent> CerrarSesionEH() {
        EventHandler<ActionEvent> EH = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreateLogin();
            }
        };
        return EH;
    }

    public void CreateScene() {
    }

    protected void setFondoTabla(ScrollPane root) {
        root.setStyle("-fx-background-image: url(" + constantes.tabla + ");\n"
                + "    -fx-background-repeat: stretch;\n"
                + "    -fx-background-size:" + scene.getWidth() + " " + scene.getHeight() + ";\n"
                + "    -fx-background-position: center center;");
    }
    protected void setFondoTabla(VBox root) {
        root.setStyle("-fx-background-image: url(" + constantes.tabla + ");\n"
                + "    -fx-background-repeat: stretch;\n"
                + "    -fx-background-size:" + scene.getWidth() + " " + scene.getHeight() + ";\n"
                + "    -fx-background-position: center center;");
    }
    
    protected void setFondo(StackPane root) {
        root.setStyle("-fx-background-image: url(" + constantes.fondo + ");\n"
                + "    -fx-background-repeat: stretch;\n"
                + "    -fx-background-size:" + scene.getWidth() + " " + scene.getHeight() + ";\n"
                + "    -fx-background-position: center center;");
    }
    
    
    public static EventHandler<ActionEvent>  NotificacionEliminarProducto(String elemento, Producto producto,TableView table){
        EventHandler<ActionEvent> EH=new EventHandler() {
            @Override
            public void handle(Event event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cuadro de confirmación");
                alert.setHeaderText("");
                alert.setContentText("¿Está seguro que desea eliminar el "+elemento+"?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    ConexionSQL.CambiarEstadoProducto(producto.getNombre());
                    table.getItems().remove(producto);
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
                }
        };
        return EH;     
    }
    public static EventHandler<ActionEvent>  NotificacionEliminarUsuario(String elemento, Usuario usuario, TableView table){
        EventHandler<ActionEvent> EH=new EventHandler() {
            @Override
            public void handle(Event event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cuadro de confirmación");
                alert.setHeaderText("");
                alert.setContentText("¿Está seguro que desea eliminar el "+elemento+"?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    ConexionSQL.CambiarEstadoCuenta(usuario.getUsuario());
                    table.getItems().remove(usuario);
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
                }
        };
        return EH;     
    }
    
    
 
    
    
    
    public static EventHandler<ActionEvent>  NotificacionModificarUsuario(String elemento, Usuario usuario, TableView table){
        EventHandler<ActionEvent> EH=new EventHandler() {
            @Override
            public void handle(Event event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cuadro de confirmación");
                alert.setHeaderText("");
                alert.setContentText("¿Está seguro que desea modificar el "+elemento+"?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    System.out.println("Usuario "+usuario.getUsuario()+" ha sido modificado!");
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
                }
        };
        return EH;     
    }
    
    public static EventHandler<ActionEvent>  NotificacionModificarProducto(String elemento, Producto producto, TableView table){
        EventHandler<ActionEvent> EH=new EventHandler() {
            @Override
            public void handle(Event event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cuadro de confirmación");
                alert.setHeaderText("");
                alert.setContentText("¿Está seguro que desea modificar el "+elemento+"?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                     System.out.println("Producto "+producto.getNombre()+" ha sido modificado!");
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
                }
        };
        return EH;     
    }
}

