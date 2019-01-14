/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    String name = "";
    int c=0;
    protected Scene scene;
    protected int tamañoVentana;
    protected String titulo;
    protected StackPane menu = new StackPane();

    public Vista(int tamañoVentana, String titulo) {

        this.scene = new Scene(menu, 1000, 600);
        this.tamañoVentana = tamañoVentana;
        this.titulo = titulo;
        this.CreateLogin();
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
         c++;
        if (name.equals("") || c!=1) {
            name = tname.getText();
        }
        blogin.setOnAction(BLoginEH());
    }

    //Método utilizado como prueba para ver el menú de cada rol
    public Vista VerificarUsu(String name) {
        System.out.println(name);
        Vista vista = new AdministradorVista(50, "AV");
        switch (name) {
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
    }

    public EventHandler<ActionEvent> BLoginEH() {
        EventHandler<ActionEvent> EH = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Vista vista = VerificarUsu(name);
                Button cerrar = new Button("Cerrar Sesión");
                cerrar.getStylesheets().add(constantes.PathStyles);
                HBox general = new HBox();

                general.getChildren().addAll(vista.menu, cerrar);
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

        this.CreateScene();
    }

    protected void setFondo(StackPane root) {
        root.setStyle("-fx-background-image: url(" + constantes.fondo + ");\n"
                + "    -fx-background-repeat: stretch;\n"
                + "    -fx-background-size:" + scene.getWidth() + " " + scene.getHeight() + ";\n"
                + "    -fx-background-position: center center;");
    }
}
