/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;

import controladores.SistemaPoliVentas;
import diseñosoftware.main;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    private void Top() {

        regresar = new Button("Regresar");
        regresar.getStylesheets().add(constantes.PathStyles);
        border.setTop(regresar);
        regresar.setOnAction(e -> {

            Vista vista = new Vista(500, "Test");
            vista.CreateLogin();
            main.stage.setScene(vista.getScene());
            //main.stage=stage;
            main.stage.setHeight(650);
            main.stage.setWidth(1000);

        });
    }

    private void Center() {

        ObservableList<Producto> lis = BuscarProductosDB.ProductosMasBuscados();
        //lis = FXCollections.observableArrayList();
        tabla = Tablas.CrearProdPrec(lis);

        border.setCenter(tabla);

        tabla.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.PRIMARY) {
                    Producto producto = (Producto) tabla.getSelectionModel().getSelectedItem();
                    VerificarUsuario(producto);
                }
            }
        });

    }

    public static void VerificarUsuario(Producto p) {
        if (SistemaPoliVentas.usuario == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cuadro de información");
            alert.setHeaderText("");
            alert.setContentText("El usuario no se encuentra registrado!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                SistemaPoliVentas.vista = new Vista(50, "Vista");
                SistemaPoliVentas.vista.CreateLogin();
                main.stage.setScene(SistemaPoliVentas.vista.getScene());
            }
        } else {
            if (SistemaPoliVentas.usuario instanceof Comprador || SistemaPoliVentas.usuario instanceof Vendedor) {

                Stage s = new Stage();
                HBox producto = new HBox();
                VBox infoProducto = new VBox();
                Label nombre = new Label("Nombre del producto: " + p.getNombre());
                Label cat = new Label("Categoría del producto: " + p.getCategoria());
                Label precio = new Label("Precio del producto: " + p.getPrecio());
                Label tmaxentr = new Label("Tiempo máximo de entrega del producto: " + p.getTiempoMaxEntrega());
                Label calpro = new Label("Calificación del producto: " + p.getCalificacion());
                Label calven = new Label("Vendedor:  " + p.getVendedor());
                Button comprar = new Button("Comprar producto ");
                infoProducto.setAlignment(Pos.CENTER);
                infoProducto.getChildren().addAll(nombre, cat, precio, tmaxentr, calpro, calven);
                producto.getChildren().addAll(infoProducto, comprar);
                producto.setAlignment(Pos.CENTER);
                s.setScene(new Scene(producto, 200, 400));
                s.setHeight(200);
                s.setWidth(450);
                s.show();
                comprar.setOnAction(e
                        -> {
                    CompradorVista.StageComprar(p);
                    s.close();
                });
            }
        }

    }

    public BorderPane getBorder() {
        return border;
    }

    public void setBorder(BorderPane border) {
        this.border = border;
    }

}
