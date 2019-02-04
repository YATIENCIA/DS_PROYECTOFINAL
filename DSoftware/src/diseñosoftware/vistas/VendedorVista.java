/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;
import controladores.SistemaPoliVentas;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelos.ConexionSQL;
import modelos.Producto;
import modelos.Usuario;
import modelos.Vendedor;
import modelos.Venta;
import recursos.constantes;
/**
 *
 * @author IYAC
 */
public class VendedorVista extends CompradorVista{
   // Pane menuV=new Pane();
    BorderPane VentasPendientes=new BorderPane();
    BorderPane MisProductos=new BorderPane();
    Tab tab_vp = new Tab("Ventas pendientes");
    Tab tab_mp = new Tab("Mis productos");
    
    public VendedorVista(int tamañoVentana, String titulo) {
        super(tamañoVentana, titulo);

    }
    
    @Override
    public void CreateScene()
    {
        super.CreateScene();
        
        tabPane.getTabs().addAll(tab_vp, tab_mp);
        CrearVentasPendientes();
        CrearMisProductos();
        //Final
        tab_vp.setContent(VentasPendientes);
        tab_mp.setContent(MisProductos);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        PresentarNotificaciones();
        tab_vp.setOnSelectionChanged(e->EliminarNotificaciones());
    }
    
       public void CrearMisProductos()
    {
        //CRUD sobre usuarios y producto
          VBox v=new VBox();

        ObservableList<Producto> list = FXCollections.observableArrayList();
        
        //AQUI VAN LOS PRODUCTOS POR VENDEDOR
   
        TableView table= Tablas.CrearProdDesc(list);
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
                    Producto producto = (Producto) table.getSelectionModel().getSelectedItem();
                    mi1.setOnAction(NotificacionModificarProducto("producto",producto, table));
                   mi2.setOnAction(NotificacionEliminarProducto("producto",producto, table));
                }
            }
        });
        
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
        MisProductos.setCenter(v);
        
    }
    
       
       public void CrearVentasPendientes(){
        VBox v=new VBox();

        ObservableList<Venta> list = ConexionSQL.PedidosPendientes();
        TableView<Venta> table = Tablas.CrearVentasDescFecha(list);
       
        ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Ver mapa");
        cm.getItems().add(mi1);
        MenuItem mi2 = new MenuItem("Anular");
        cm.getItems().add(mi2);
        mi1.setOnAction(verMapa()); 
      //  mi2.setOnAction(Notificacion("anular", "pedido")); 
        
        table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if(t.getButton() == MouseButton.SECONDARY) {
                    cm.show(table, t.getScreenX(), t.getScreenY());
                }
            }
        });
        
        v.getChildren().addAll(table);
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
        VentasPendientes.setCenter(v);

    }
     
       
    private EventHandler<ActionEvent> verMapa()
    {
        EventHandler<ActionEvent> EH;
        EH = new EventHandler() {
            @Override
            public void handle(Event event) {
                Image image = new Image(constantes.mapa);
                ImageView imageView = new ImageView(image);
                Alert alerta=new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Mapa");
                alerta.setContentText("");
                alerta.setGraphic(imageView);
                alerta.showAndWait();
            }};
        
       return EH;
}
    
    public int getNotificaciones()
    {
        int i=0;
        try {
            String query = "{call getNotificacion(?,?)}";
            ResultSet rs;
            Connection conn = ConexionSQL.getConnection();
            CallableStatement stmt = conn.prepareCall(query);
            //Set IN parameter
            stmt.setString(1, SistemaPoliVentas.usuario.getCedula());
            stmt.registerOutParameter(2, Types.INTEGER);
            rs = stmt.executeQuery();
            i = stmt.getInt("cantidadout");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public void PresentarNotificaciones()
    {
        int i=getNotificaciones();
        String not=String.valueOf(i);
        tab_vp.setText("Ventas pendientes "+not);
    }
    
    public void EliminarNotificaciones()
    {
        tab_vp.setText("Ventas pendientes");
        Vendedor v=(Vendedor) SistemaPoliVentas.usuario;
        Venta.EliminarNotVendedor(v);
    }
}
