/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;

import controladores.SistemaPoliVentas;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import recursos.constantes;

/**
 *
 * @author IYAC
 */
public class Vista {

    TextField tname = new TextField();
    TextField tcontra = new TextField();
    protected Scene scene;
    protected int tamañoVentana;
    protected String titulo;
    protected StackPane menu = new StackPane();

    public Vista(int tamañoVentana, String titulo) {

        this.scene = new Scene(menu, 1000, 600);
        this.tamañoVentana = tamañoVentana;
        this.titulo = titulo;
        this.setFondo(menu);

    }

    public Scene getScene() {
        return scene;
    }

    public void CreateLogin() {
        StackPane pane = new StackPane();
        VBox login = new VBox();
        HBox names = new HBox();
        HBox contras = new HBox();
        Button blogin = new Button("Login");

        blogin.getStylesheets().add(constantes.PathStyles);

        Label lname = new Label("Ingrese su usuario: ");
        Label lcontra = new Label("Ingrese su contraseña: ");

        lname.getStylesheets().add(constantes.PathStyles);
        lcontra.getStylesheets().add(constantes.PathStyles);
        names.getChildren().addAll(lname, tname);
        contras.getChildren().addAll(lcontra, tcontra);
        login.getChildren().addAll(names, contras, blogin);

        login.setAlignment(Pos.CENTER);
        names.setAlignment(Pos.CENTER);
        contras.setAlignment(Pos.CENTER);
        blogin.setAlignment(Pos.CENTER);
        pane.getChildren().add(login);
        this.setFondo(pane);
        scene.setRoot(pane);
        blogin.setOnAction(BLoginEH());
    }

    //Método utilizado como prueba para ver el menú de cada rol
   /* public Vista VerificarUsu() {
        //Vista vista = new AdministradorVista(50, "AV");
        Vista vista=new Vista(50,"AV");
        switch (tname.getText()) {
            case "a":
                AdministradorVista av = new AdministradorVista(50, "AV");
                vista = av;
                break;

            case "v":
                VendedorVista vv = new VendedorVista(50, "CV");
                vista = vv;
                break;
            case "c":
                CompradorVista cv = new CompradorVista(50, "CV");
                vista = cv;
                break;
            default:
                break;
        }
        vista.CreateScene();
        return vista;
    }*/

    public EventHandler<ActionEvent> BLoginEH() {
        EventHandler<ActionEvent> EH = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SistemaPoliVentas.IngresarAlSistema(tname.getText(), tcontra.getText());
                Vista vista = SistemaPoliVentas.vista;
                
                Button cerrar = new Button("Cerrar Sesión");
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
    public static EventHandler<ActionEvent>  Notificacion(String tipo, String elemento){
        EventHandler<ActionEvent> EH=new EventHandler() {
            @Override
            public void handle(Event event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cuadro de confirmación");
                alert.setHeaderText("");
                alert.setContentText("¿Está seguro que desea "+tipo+" el "+elemento+"?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    // ... user chose OK
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
                }
        };
        return EH;     
    }
}

