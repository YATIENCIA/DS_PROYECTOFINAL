/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import javafx.collections.ObservableList;
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
public class ObtenerDatosDBTest {
    
    public ObtenerDatosDBTest() {
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
     * Test of getID method, of class ObtenerDatosDB.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        ConexionSQL.ConexionSQL();
        String estrategia = "";
        int expResult = 0;
        int result = ObtenerDatosDB.getID(estrategia);
        assertEquals(0, result);
    }

    /**
     * Test of getVendedorByID method, of class ObtenerDatosDB.
     */
    @Test
    public void testGetVendedorByID() {
        System.out.println("getVendedorByID");
        ConexionSQL.ConexionSQL();
        String id = "";
        Vendedor expResult = null;
        Vendedor result = ObtenerDatosDB.getVendedorByID(id);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getProductoByID method, of class ObtenerDatosDB.
     */
    @Test
    public void testGetProductoByID() {
        System.out.println("getProductoByID");
        ConexionSQL.ConexionSQL();
        String id = "";
        Producto expResult = null;
        Producto result = ObtenerDatosDB.getProductoByID(id);
        assertNotEquals(null, result);
    }

    /**
     * Test of TodosLosUsuarios method, of class ObtenerDatosDB.
     */
    @Test
    public void testTodosLosUsuarios() {
        System.out.println("TodosLosUsuarios");
        ConexionSQL.ConexionSQL();
        ObservableList<Usuario> expResult = null;
        ObservableList<Usuario> result = ObtenerDatosDB.TodosLosUsuarios();
        assertNotEquals(0,result.size());
    }
    
}
