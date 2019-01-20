/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;

import controladores.SistemaPoliVentas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import modelos.Comprador;
import modelos.Usuario;
import modelos.Vendedor;

/**
 *
 * @author IYAC
 */
public class NuevoUsuario extends Vista{
    
    public NuevoUsuario(int tamañoVentana, String titulo) {
        super(tamañoVentana, titulo);
    }
    
    
    @Override
    public void CreateScene()
    {
       BorderPane general=new BorderPane();
        GridPane gp=new GridPane();
        gp.add(new Label("Nombre:"), 1, 0);
        gp.add(new Label("Apellidos:"), 1, 1);
        gp.add(new Label("Usuario:"), 1, 2);
        gp.add(new Label("Contraseña:"), 1,3);
        gp.add(new Label("Cedula:"), 1, 4);
        gp.add(new Label("Teléfono:"), 1, 5);
        gp.add(new Label("email:"), 1, 6);
        gp.add(new Label("Dirección:"), 1, 7);
        gp.add(new Label("Whatsapp:"), 1, 8);
        gp.add(new Label("Matricula:"), 1, 9);
         gp.add(new Label("Perfil:"), 1, 10);
        
        TextField c_nombre=new TextField();
        TextField c_apellido=new TextField();
        TextField c_usuario=new TextField();
        TextField c_contraseña=new TextField();
        TextField c_cedula=new TextField();
        TextField c_telefono=new TextField();
        TextField c_email=new TextField();
        TextField c_direccion=new TextField();
        CheckBox c_whatsapp=new CheckBox();
         c_whatsapp.setIndeterminate(false);
         
         
         TextField c_mat=new TextField();
         ComboBox<String> c_perfil=new ComboBox();
         c_perfil.getItems().addAll("Vendedor", "Comprador");
         c_perfil.setPromptText("Comprador");
         gp.add(c_nombre,4,0);
         gp.add(c_apellido,4,1);
         gp.add(c_usuario,4,2);
         gp.add(c_contraseña,4,3);
         gp.add(c_cedula,4,4);
         gp.add(c_telefono,4,5);
         gp.add(c_email,4,6);
         gp.add(c_direccion,4,7);
         gp.add(c_whatsapp,4,8);
         gp.add(c_mat,4,9);
         gp.add(c_perfil,4,10);

        gp.setAlignment(Pos.CENTER);
        
        general.setTop(gp);
        
        Button b=new Button("Registrar");
       
        b.setOnAction(Ingreso(c_usuario.getText(), c_contraseña.getText(), c_nombre.getText(), c_apellido.getText(), 
        c_telefono.getText(),c_email.getText(),c_direccion.getText(),c_cedula.getText(),c_mat.getText(),c_whatsapp.isSelected(),
        c_perfil.getPromptText()));
        
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(general, b);         
    }
    
    
    public EventHandler<ActionEvent> Ingreso(String usuario,String contraseña,String nombre, String apellido, 
        String telefono,String email,String direccion,String cedula,String mat,boolean whatsapp,
        String perfil){
        EventHandler<ActionEvent> e=new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
              //Crear el nuevo usuario
            switch(perfil)
            {
                case "Vendedor":
                    Vendedor.CrearNuevoVendedor(usuario,contraseña, nombre, apellido, telefono, email, direccion, cedula,  mat,  whatsapp);
                    break;
                case "Comprador":
                    Comprador.CrearNuevoComprador(usuario,contraseña, nombre, apellido, telefono, email, direccion, cedula,  mat,  whatsapp);
                    break;
            }
            SistemaPoliVentas.IngresarAlSistema(usuario, contraseña);
            }
            
        };
        return e;
    }
    
}
