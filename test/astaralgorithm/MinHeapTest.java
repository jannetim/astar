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
public class MinHeapTest {
    MinHeap mh;
    Node node;
    public MinHeapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mh = new MinHeap();
        node = new Node(1,2,null,0);    

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
    public void newHeapIsEmpty() {
        assertTrue(mh.isEmpty());
    }
    
    @Test
    public void insertWorks() throws NodeNotFoundException {
        mh.insert(node);
        assertTrue(!mh.isEmpty());
        assertTrue(mh.contains(node));
    }

    @Test
    public void removeWorks() {
        Node node = new Node(1,2,null,0);    
        mh.insert(node);
        assertEquals(mh.removeMin(), node);
        assertTrue(mh.isEmpty());
    }
    
    @Test
    public void findWorks() throws NodeNotFoundException {
        mh.insert(node);
        assertEquals(mh.find(node), node);
    }
}
