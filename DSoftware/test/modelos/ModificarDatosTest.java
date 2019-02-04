/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author IYAC
 */
public class ModificarDatosTest {
    
    public ModificarDatosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ModificarPersonaEnLaBase method, of class ModificarDatos.
     */
    @Test
    public void testModificarPersonaEnLaBase() {
        System.out.println("ModificarPersonaEnLaBase");
        ConexionSQL.ConexionSQL();
        String usuario = "";
        String nombres = "";
        String apellidos = "";
        String telefono = "";
        String email = "";
        String direccion = "";
        String cedula = "";
        String matricula = "";
        boolean whatsapp = false;
        ModificarDatos.ModificarPersonaEnLaBase(usuario, nombres, apellidos, telefono, email, direccion, cedula, matricula, whatsapp);
    }

    /**
     * Test of ModificarUsuarioEnLaBase method, of class ModificarDatos.
     */
    @Test
    public void testModificarUsuarioEnLaBase() {
        System.out.println("ModificarUsuarioEnLaBase");
        ConexionSQL.ConexionSQL();
        String usuario = " ";
        String contrasena = " ";
        ModificarDatos.ModificarUsuarioEnLaBase(usuario, contrasena);
    }

    
}
