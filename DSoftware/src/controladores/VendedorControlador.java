/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import diseñosoftware.vistas.VendedorVista;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import modelos.Producto;
import modelos.Usuario;
import modelos.Vendedor;

/**
 *
 * @author DOTA
 */
public class VendedorControlador {
    
    VendedorVista vista;
    Vendedor vendedor;
    public VendedorControlador(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    
    
    public Producto crearProducto(String nombre, String descripcion, String precio) {
      Producto producto = new Producto();
        return producto;
    }

    //public boolean modificarProducto(String nombre, String descripcion, String precio) {
        
   // }
    
    public boolean borrarProducto(){
        
        return true;
    }
    
    public void modificarUsuarioVista(Usuario user){
        GridPane grid= new GridPane();
        Button modificar = new Button("Modificar");
        String usuario= user.getUsuario();
        String contraseña=user.getContraseña();
        String nombres=user.getNombres();
        String apellidos=user.getApellidos();
        String telefono=user.getTelefono();
        String email=user.getEmail();
        String direccion=user.getDireccion();
        String cedula=user.getCedula();
        String matricula=user.getMatricula();
        
        Label lusuario = new Label("Usuario");
        //Label lcontraseña = new Label("Contraeña ");
        Label lnombres = new Label("Nombres");
        Label lapellidos = new Label("Apellidos");
        Label ltelefono = new Label("Telefono");
        Label lemail = new Label("Email");
        Label ldireccion = new Label("Direccion");
        Label lcedula = new Label("Cedula");
        Label lmatricula = new Label("Matricula");
        
        TextField tusuario= new TextField(usuario);
        TextField tnombres= new TextField(nombres);
        TextField tapellidos= new TextField(apellidos);
        TextField ttelefono= new TextField(telefono);
        TextField temail= new TextField(email);
        TextField tdireccion= new TextField(direccion);
        TextField tcedula= new TextField(cedula);
        TextField tmatricula= new TextField(matricula);
        
        
        grid.add(lusuario, 0, 0);
        grid.add(lnombres, 0, 1);
        grid.add(lapellidos, 0, 2);
        grid.add(ltelefono, 0, 3);
        grid.add(lemail, 0, 4);
        grid.add(ldireccion, 0, 5);
        grid.add(lcedula, 0, 6);
        grid.add(lmatricula, 0, 7);
        
        
        grid.add(tusuario, 1, 0);
        grid.add(tnombres, 1, 1);
        grid.add(tapellidos, 1, 2);
        grid.add(ttelefono, 1, 3);
        grid.add(temail, 1, 4);
        grid.add(tdireccion, 1, 5);
        grid.add(tcedula, 1, 6);
        grid.add(tmatricula, 1, 7);
        
        grid.add(modificar, 0, 9);
        
        modificar.setOnAction(e ->modificarUsuarioControlador(grid,user));
    }
    
    public void modificarUsuarioControlador(GridPane grid, Usuario user){
        Button aceptar = new Button("Aceptar");
        
        Label lusuario = new Label("Usuario");
        Label lcontraseña = new Label("Contraeña ");
        Label lnombres = new Label("Nombres");
        Label lapellidos = new Label("Apellidos");
        Label ltelefono = new Label("Telefono");
        Label lemail = new Label("Email");
        Label ldireccion = new Label("Direccion");
        Label lcedula = new Label("Cedula");
        Label lmatricula = new Label("Matricula");
        
        TextField tusuario= new TextField();
        TextField tcontraseña= new TextField();
        TextField tnombres= new TextField();
        TextField tapellidos= new TextField();
        TextField ttelefono= new TextField();
        TextField temail= new TextField();
        TextField tdireccion= new TextField();
        TextField tcedula= new TextField();
        TextField tmatricula= new TextField();
        
        grid.add(lusuario, 0, 0);
        grid.add(lcontraseña, 0, 1);
        grid.add(lnombres, 0, 2);
        grid.add(lapellidos, 0, 3);
        grid.add(ltelefono, 0, 4);
        grid.add(lemail, 0, 5);
        grid.add(ldireccion, 0, 6);
        grid.add(lcedula, 0, 7);
        grid.add(lmatricula, 0, 8);
        
        
        grid.add(tusuario, 1, 0);
        grid.add(tcontraseña, 1, 1);
        grid.add(tnombres, 1, 2);
        grid.add(tapellidos, 1, 3);
        grid.add(ttelefono, 1, 4);
        grid.add(temail, 1, 5);
        grid.add(tdireccion, 1, 6);
        grid.add(tcedula, 1, 7);
        grid.add(tmatricula, 1, 8);
        
        grid.add(aceptar,0,10);
        
        
        user.setUsuario(tusuario.getText());
        user.setContraseña(tcontraseña.getText());
        user.setNombres(tnombres.getText());
        user.setApellidos(tapellidos.getText());
        user.setTelefono(ttelefono.getText());
        user.setEmail(temail.getText());
        user.setDireccion(tdireccion.getText());
        user.setCedula(tcedula.getText());
        user.setMatricula(tmatricula.getText());
        //aceptar.setOnAction(value);
        
    }
    
}
