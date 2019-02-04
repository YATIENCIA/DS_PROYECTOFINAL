/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.List;
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
public class BuscarProductosDBTest {
    
    public BuscarProductosDBTest() {
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
     * Test of ProductosMasBuscados method, of class BuscarProductosDB.
     */
    @Test
    public void testProductosMasBuscados() {
        System.out.println("ProductosMasBuscados");
        ConexionSQL.ConexionSQL();
        ObservableList<Producto> expResult = null;
        ObservableList<Producto> result = BuscarProductosDB.ProductosMasBuscados();
        assertEquals(0, result.size());
    }

    /**
     * Test of BuscarProductos method, of class BuscarProductosDB.
     */
    @Test
    public void testBuscarProductos() {
        System.out.println("BuscarProductos");
        ConexionSQL.ConexionSQL();
        List<Producto> result = BuscarProductosDB.BuscarProductos("");
        assertNotEquals(0, result.size());
    }

    /**
     * Test of TodosLosProductos method, of class BuscarProductosDB.
     */
    @Test
    public void testTodosLosProductos() {
        System.out.println("TodosLosProductos");
        ConexionSQL.ConexionSQL();
        ObservableList<Producto> result = BuscarProductosDB.TodosLosProductos();
        System.out.println(result);
        assertNotEquals(0, result.size());
    }
    
}
