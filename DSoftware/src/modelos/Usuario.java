/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author IYAC
 */
public class Usuario {
    protected String usuario;
    protected String contraseña;
    protected String nombres;
    protected String apellidos;
    protected String telefono;
    protected String email;
    protected String direccion;
    protected String cedula;
    protected String matricula;
    protected boolean whatsapp;

    public Usuario(){
        
    }
    public Usuario(String nombres, String apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public String getMatricula() {
        return matricula;
    }

    public boolean isWhatsapp() {
        return whatsapp;
    }
    

}
