/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jannetim
 */
public class NodeTest {
    Node node;
    public NodeTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void createsNewNodeCorrectlyWithoutParent() {
        node = new Node(1,2,null);
        assertNotNull(node);
        assertEquals(node.getX(),1);
        assertEquals(node.getY(), 2);
        assertNull(node.getParent());
    }
    
    @Test
    public void createsNewNodeCorrectlyWithParent() {
        node = new Node(4,3,null);
        Node node2 = new Node(1,2,node);
        assertNotNull(node2);
        assertEquals(node2.getX(),1);
        assertEquals(node2.getY(), 2);
        assertEquals(node2.getParent(), node);
    }
}
