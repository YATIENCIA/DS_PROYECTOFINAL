/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diseñosoftware;


import diseñosoftware.vistas.Vista;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelos.ConexionSQL;
import recursos.constantes;

/**
 *
 * @author IYAC
 */
public class main extends Application{
    public static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
       
        String p="   ";
        System.out.println(p.length());
        //Conexion SQL
       ConexionSQL cs=new ConexionSQL();
       cs.ConexionSQL();
       
       
        Vista vista=new Vista(500, "Test");
        vista.CreateLogin();
        stage.setScene(vista.getScene());
        this.stage=stage;
        stage.setHeight(650);
        stage.setWidth(1000);
        stage.setResizable(false);
        
               
        stage.centerOnScreen();
        stage.show();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
    }

  
    
}
