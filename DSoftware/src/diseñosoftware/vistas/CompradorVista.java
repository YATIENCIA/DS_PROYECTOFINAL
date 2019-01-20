/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelos.Producto;
import modelos.Venta;

/**
 *
 * @author IYAC
 */
public class CompradorVista extends Vista{

    TabPane tabPane = new TabPane();
    BorderPane Busqueda=new BorderPane();
    BorderPane ComprasPend=new BorderPane();
    BorderPane MasBuscados=new BorderPane();
    
    public CompradorVista(int tamañoVentana, String titulo) {
        super(tamañoVentana, titulo);

    }
    
    @Override
    public void CreateScene()
    {
        

        Tab tab_bs = new Tab("Búsqueda de Productos");
        Tab tab_cp = new Tab("Compras pendientes");
        Tab tab_mb = new Tab("Productos más buscados")  ;
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
    
    public void CrearBusqueda(){
        VBox v=new VBox();
        HBox h=new HBox();
        HBox h2=new HBox();
        Label lbuscar=new Label("Nombre del producto: ");
        Label ldesc=new Label("Descripción del producto: ");
        Button bbuscar=new Button("Buscar");
        TextField tfnombre=new TextField();
        TextField tfdesc=new TextField();
        h.getChildren().addAll(lbuscar, tfnombre);
        h2.getChildren().addAll(ldesc, tfdesc);
        h.setAlignment(Pos.CENTER);
        h2.setAlignment(Pos.CENTER);
        v.getChildren().addAll(h,h2,bbuscar);
        
        /*Cada artículo mostrado debe tener nombre, categoría, precio, tiempo máximo de entrega y
calificación del producto y del vendedor (Escala de 5 estrellas para cada uno). Aquí mismo
deberá permitir comprar dicho artículo./*
        
        */
        bbuscar.setOnAction(EHBuscarProducto(v));
        Busqueda.setCenter(v);
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
    }
    
    public EventHandler<ActionEvent> EHBuscarProducto(VBox v){
        EventHandler<ActionEvent> EH=new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  PresentarProductos(v);  
            }
        };
        return EH;
    }
    
    public void PresentarProductos(VBox v)
    {
        VBox vproductos=new VBox();
        
        for(int i=0; i<5; i++){
            HBox producto=new HBox();

            Label ip=new Label("Imagen del producto "+i);
       
            VBox infoProducto=new VBox();
            Label nombre=new Label("Nombre del producto "+i);
            Label cat=new Label("Categoría del producto "+i);
            Label precio=new Label("Precio del producto "+i);
            Label tmaxentr=new Label("Tiempo máximo de entrega del producto "+i);
            Label calpro=new Label("Calificación del producto "+i);
            Label calven=new Label("Calificación del vendedor");
            Button comprar=new Button("Comprar producto "+i);
            infoProducto.setAlignment(Pos.CENTER);
            infoProducto.getChildren().addAll(nombre, cat,precio,tmaxentr,calpro,calven);
            producto.getChildren().addAll(ip, infoProducto, comprar);
            producto.setAlignment(Pos.CENTER);
            producto.setSpacing(110);
            vproductos.getChildren().add(producto);
        }
        vproductos.setAlignment(Pos.CENTER);
        vproductos.setSpacing(15);
        
        
       ScrollPane scroll = new ScrollPane();
       scroll.setMaxWidth(710);
        scroll.setPrefViewportHeight(400);
       scroll.setContent(vproductos); 

        super.setFondoTabla(vproductos);
        Busqueda.getChildren().clear();
        v.getChildren().addAll(scroll);  
        v.setAlignment(Pos.CENTER);
        Busqueda.setCenter(v);
    }
    
     public void CrearComprasPendientes(){
         VBox v=new VBox();
         
        ObservableList<Venta> list = FXCollections.observableArrayList
            (new Venta(),
            new Venta());
        TableView<Venta> table = Tablas.CrearVentasDescFecha(list);
        
        Button bbuscar=new Button("Ver historial de pedido");
        v.getChildren().addAll(bbuscar, table);
        ComprasPend.setCenter(v);
        v.setSpacing(20);
        v.setAlignment(Pos.CENTER);
        
    }
     
    public EventHandler<ActionEvent> EHHistorial(){
        EventHandler<ActionEvent> EH=new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  //PresentarProductos(v);  
            }
        };
        return EH;
    }
    
    
    public void CrearMasBuscados()
    {
        /*Este listado debe mostrar el nombre y el precio de los 15 artículos más buscados en forma de
tabla*/
        VBox v=new VBox();
        
        
        ObservableList<Producto> list = FXCollections.observableArrayList(new Producto("Cuaderno", "Azul universitario", "10.99"),
            new Producto("Cuaderno", "Verde universitario", "4.99"));
        
        
        TableView table = Tablas.CrearProdPrecDesc(list);
        table.setEditable(true);
        
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
