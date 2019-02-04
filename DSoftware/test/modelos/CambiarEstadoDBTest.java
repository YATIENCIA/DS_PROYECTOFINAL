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
public class CambiarEstadoDBTest {
    
    public CambiarEstadoDBTest() {
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
     * Test of CambiarEstadoCuenta method, of class CambiarEstadoDB.
     */
    @Test
    public void testCambiarEstadoCuenta() {
        System.out.println("CambiarEstadoCuenta");
        String usuario = "";
        ConexionSQL.ConexionSQL();
        CambiarEstadoDB.CambiarEstadoCuenta(usuario);
    }

    /**
     * Test of CambiarEstadoProducto method, of class CambiarEstadoDB.
     */
    @Test
    public void testCambiarEstadoProducto() {
        System.out.println("CambiarEstadoProducto");
        int codigo = 0;
        ConexionSQL.ConexionSQL();
        CambiarEstadoDB.CambiarEstadoProducto(codigo);
    }
    
}
