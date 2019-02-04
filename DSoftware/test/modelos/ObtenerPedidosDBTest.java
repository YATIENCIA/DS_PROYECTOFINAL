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
public class ObtenerPedidosDBTest {
    
    public ObtenerPedidosDBTest() {
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
     * Test of PedidosPendientes method, of class ObtenerPedidosDB.
     */
    @Test
    public void testPedidosPendientes() {
        System.out.println("PedidosPendientes");
        ConexionSQL.ConexionSQL();
        ObservableList<Venta> expResult = null;
        ObservableList<Venta> result = ObtenerPedidosDB.PedidosPendientes();
        assertNotEquals(expResult, result);
    }
    
}
