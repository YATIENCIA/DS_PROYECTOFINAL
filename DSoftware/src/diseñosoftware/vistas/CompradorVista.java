/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;

import controladores.SistemaPoliVentas;
import static diseñosoftware.vistas.ProductosMasBuscadosVista.VerificarUsuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelos.Categoria;
import modelos.Comprador;
import modelos.ConexionSQL;
import modelos.Producto;
import modelos.Usuario;
import modelos.Venta;

/**
 *
 * @author IYAC
 */
public class CompradorVista extends Vista {

    TabPane tabPane = new TabPane();
    BorderPane Busqueda = new BorderPane();
    BorderPane ComprasPend = new BorderPane();
    BorderPane MasBuscados = new BorderPane();

    public CompradorVista(int tamañoVentana, String titulo) {
        super(tamañoVentana, titulo);

    }

    @Override
    public void CreateScene() {

        Tab tab_bs = new Tab("Búsqueda de Productos");
        Tab tab_cp = new Tab("Compras pendientes");
        Tab tab_mb = new Tab("Productos más buscados");
        tabPane.getTabs().addAll(tab_bs, tab_cp, tab_mb);
        CrearBusqueda();
        CrearComprasPendientes();
        CrearMasBuscados();
        //Final
        tab_bs.setContent(Busqueda);
        tab_cp.setContent(ComprasPend);
        tab_mb.setContent(MasBuscados);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        menu.getChildren().add(tabPane);

    }

    public void CrearBusqueda() {
        VBox v = new VBox();
        HBox h = new HBox();
        Label lbuscar = new Label("Nombre del producto: ");
        Button bbuscar = new Button("Buscar");
        TextField tfnombre = new TextField();
        h.getChildren().addAll(lbuscar, tfnombre);
        h.setAlignment(Pos.CENTER);
        v.getChildren().addAll(h, bbuscar);

        /*Cada artículo mostrado debe tener nombre, categoría, precio, tiempo máximo de entrega y
calificación del producto y del vendedor (Escala de 5 estrellas para cada uno). Aquí mismo
deberá permitir comprar dicho artículo./*
        
         */
        bbuscar.setOnAction(e -> {
            PresentarProductos(v, tfnombre.getText());
            ConexionSQL.AñadirABusqueda(tfnombre.getText());
        });
        Busqueda.setCenter(v);
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
    }

    public void PresentarProductos(VBox v, String palabra) {

        ScrollPane scroll = new ScrollPane();
        VBox vproductos = new VBox();
        List<Producto> lista = ConexionSQL.BuscarProductos(palabra);
        for (Producto p : lista) {
            HBox producto = new HBox();
            VBox infoProducto = new VBox();
            Label nombre = new Label("Nombre del producto " + p.getNombre());
            Label cat = new Label("Categoría del producto " + p.getCategoria());
            Label precio = new Label("Precio del producto " + p.getPrecio());
            Label tmaxentr = new Label("Tiempo máximo de entrega del producto " + p.getTiempoMaxEntrega());
            Label calpro = new Label("Calificación del producto " + p.getCalificacion());
            Label calven = new Label("Vendedor  " + p.getVendedor());
            Button comprar = new Button("Comprar producto ");
            infoProducto.setAlignment(Pos.CENTER);
            infoProducto.getChildren().addAll(nombre, cat, precio, tmaxentr, calpro, calven);
            producto.getChildren().addAll(infoProducto, comprar);
            comprar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    StageComprar(p);
                }
            });
            producto.setAlignment(Pos.CENTER);
            producto.setSpacing(110);
            vproductos.getChildren().add(producto);
        }
        vproductos.setAlignment(Pos.CENTER);
        vproductos.setSpacing(15);
        scroll.setMaxWidth(710);
        scroll.setPrefViewportHeight(400);
        scroll.setContent(vproductos);

        super.setFondoTabla(vproductos);
        Busqueda.getChildren().clear();

        Busqueda.setTop(v);
        Busqueda.setCenter(scroll);
    }

    public static void StageComprar(Producto p) {
        VBox bp = new VBox();
        Stage stage = new Stage();
        stage.setHeight(400);
        stage.setWidth(400);
        Label lpro = new Label(p.getNombre() + "- categoria:" + p.getCategoria());
        Label l = new Label("Escoja el método de pago: ");
        ComboBox<String> c_pago = new ComboBox();
        c_pago.setPromptText("Efectivo");
        c_pago.getItems().addAll("Efectivo", "AppMovil");
        Label lc = new Label("Cantidad");
        TextField tc_can = new TextField();

        Button pagar = new Button("Comprar");
        bp.getChildren().addAll(lpro, l, c_pago, lc, tc_can, pagar);
        pagar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                ConexionSQL.ConexionSQL();
                ((Comprador) SistemaPoliVentas.usuario).comprar(p, c_pago.getPromptText(), Integer.parseInt(tc_can.getText()));
            }
        });
        Scene scene = new Scene(bp, 100, 100);
        stage.setScene(scene);
        stage.show();

    }

    public void CrearComprasPendientes() {
        VBox v = new VBox();

        ObservableList<Venta> list = FXCollections.observableArrayList();

        String query = "{call  historialComprasEstado(?,?)}";
        ResultSet rs;
        String cedula = SistemaPoliVentas.usuario.getCedula();

        try (Connection conn = ConexionSQL.getConnection();
                CallableStatement stmt = conn.prepareCall(query)) {
            //Set IN parameter
            stmt.setString(1, cedula);
            stmt.setString(2, "PENDIENTE");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setCantidad(rs.getInt("cantidad"));
                Producto p = new Producto();
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("costo"));
                p.setCategoria(new Categoria("categoria"));
                venta.setProducto(p);
                list.add(venta);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        TableView<Venta> table = Tablas.CrearVentasDescFecha(list);

        Button bbuscar = new Button("Ver historial de pedido");
        v.getChildren().addAll(bbuscar, table);
        ComprasPend.setCenter(v);
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);

    }

    public EventHandler<ActionEvent> EHHistorial() {
        EventHandler<ActionEvent> EH = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //PresentarProductos(v);  
            }
        };
        return EH;
    }

    public void CrearMasBuscados() {
        /*Este listado debe mostrar el nombre y el precio de los 15 artículos más buscados en forma de
tabla*/
        VBox v = new VBox();
        String sql = "";

        ObservableList<Producto> list = ConexionSQL.ProductosMasBuscados();

        //Aqui van los más buscado!!!!!!!
        TableView table = Tablas.CrearProdPrec(list);
        table.setEditable(true);
        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.PRIMARY) {
                    Producto producto = (Producto) table.getSelectionModel().getSelectedItem();
                    VerificarUsuario(producto);
                }
            }
        });
        v.getChildren().addAll(table);
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
        MasBuscados.setCenter(v);

        /*al dar clic sobre uno de los artículos el sistema debe verificar automáticamente si el
usuario ha ingresado al sistema o es un usuario desconocido. En caso de que haya ingresado al
sistema como comprador o vendedor, le deberá aparecer la ventana de consulta del producto
y el botón para comprar, caso contrario, deberá mostrarse la interfaz de Login.
         */
    }

}
