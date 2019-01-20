/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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
        
        TextField c_nombre=new TextField();
        TextField c_apellido=new TextField();
        TextField c_usuario=new TextField();
        TextField c_contraseña=new TextField();
        TextField c_cedula=new TextField();
        TextField c_telefono=new TextField();
        TextField c_email=new TextField();
        TextField c_direccion=new TextField();
         TextField c_mat=new TextField();
         
         
         gp.add(c_nombre,4,0);
         gp.add(c_apellido,4,0);
         gp.add(c_usuario,4,0);
         gp.add(c_contraseña,4,0);
         gp.add(c_cedula,4,0);
         gp.add(c_telefono,4,0);
         gp.add(c_email,4,0);
         gp.add(c_direccion,4,0);
         gp.add(c_mat,4,0);

        
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().add(gp);         
    }
    
    
}
