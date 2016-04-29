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
public class MapHandlerTest {
    MapHandler mh;
    public MapHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mh = new MapHandler();
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
    public void mapHandlerCreatesMap() {
        Node[][] map = mh.populateMap(10, 10, false);
        assertNotNull(map);
    }
    
    @Test
    public void createdMapIsRightSize() {
        Node[][] map = mh.populateMap(10, 15, false);
        assertEquals(map.length,15);
        assertEquals(map[0].length,10);
    }
}
