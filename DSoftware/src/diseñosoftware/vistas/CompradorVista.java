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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author IYAC
 */
public class CompradorVista extends Vista{

    TabPane tabPane = new TabPane();
    Pane Busqueda=new Pane();
    Pane ComprasPend=new Pane();
    Pane MasBuscados=new Pane();
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
        v.getChildren().addAll(h,h2,bbuscar);
        
        /*Cada artículo mostrado debe tener nombre, categoría, precio, tiempo máximo de entrega y
calificación del producto y del vendedor (Escala de 5 estrellas para cada uno). Aquí mismo
deberá permitir comprar dicho artículo./*
        
        */
        bbuscar.setOnAction(EHBuscarProducto(v));
        Busqueda.getChildren().add(v);
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
            Pane imagenproducto=new Pane(new Label("Imagen del producto "+i));
            VBox infoProducto=new VBox();
            Label nombre=new Label("Nombre del producto "+i);
            Label cat=new Label("Categoría del producto "+i);
            Label precio=new Label("Precio del producto "+i);
            Label tmaxentr=new Label("Tiempo máximo de entrega del producto "+i);
            Label calpro=new Label("Calificación del producto "+i);
            Label calven=new Label("Calificación del vendedor");
            Button comprar=new Button("Comprar producto "+i);
            infoProducto.getChildren().addAll(nombre, cat,precio,tmaxentr,calpro,calven);
            producto.getChildren().addAll(imagenproducto, infoProducto, comprar);
            vproductos.getChildren().add(producto);
        }
        Busqueda.getChildren().clear();
        v.getChildren().addAll(vproductos);      
        Busqueda.getChildren().add(v);
    }
    
     public void CrearComprasPendientes(){
         VBox v=new VBox();
         /*Esta opción muestra el listado de los pedidos pendientes, es decir, aquellos que aún no han sido
recibidos. Además, se podrá visualizar un historial de todos los pedidos realizados, organizarlos
por fecha, por costo y filtrarlos por nombre del producto o por vendedor. Finalmente, esto
permitirá generar un PDF con la información filtrada, la cual será enviada al correo electrónico
del usuario actual.
Aquí se tiene la posibilidad de calificar el producto y el vendedor utilizando estrellas. Además,
se puede indicar si el producto fue entregado a tiempo o no, incluso se puede anular el pedido.
Finalmente, cuando el vendedor llega con el producto pedido, el comprador debe indicar que
recibió el producto, caso contrario el vendedor no le entregará dicho producto y venta se anula./*
         
         */
        TableView table = new TableView();
        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Pedidos");
        TableColumn lastNameCol = new TableColumn("Descripción");
        TableColumn emailCol = new TableColumn("Fecha de entrega");
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        
        Button bbuscar=new Button("Ver historial de pedido");
        v.getChildren().addAll(bbuscar, table);
        ComprasPend.getChildren().add(v);

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

        TableView table = new TableView();
        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Producto");
        TableColumn lastNameCol = new TableColumn("Precio");
        table.getColumns().addAll(firstNameCol, lastNameCol);
        v.getChildren().addAll(table);
        MasBuscados.getChildren().add(v);

        /*al dar clic sobre uno de los artículos el sistema debe verificar automáticamente si el
usuario ha ingresado al sistema o es un usuario desconocido. En caso de que haya ingresado al
sistema como comprador o vendedor, le deberá aparecer la ventana de consulta del producto
y el botón para comprar, caso contrario, deberá mostrarse la interfaz de Login.
*/
    }
}
