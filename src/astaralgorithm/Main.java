/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;

import java.awt.image.BufferedImage;

/**
 *
 * @author jannetim
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NodeNotFoundException {
        MapHandler mh = new MapHandler();
        Node node = new Node(0, 0, null, 0);
        Node target = new Node(17, 9, null);
        
        System.out.println("Kartta ilman estettyjä solmuja");
        AStar algo = new AStar(mh.populateMap(50, 10, false));
        Node result = algo.AStar(node, target);
        mh.workThePath(result);
        
        System.out.println("\n\nKartta estetyillä solmuilla");
        algo = new AStar(mh.populateMap(50, 10, true));
        result = algo.AStar(node, target);
        mh.workThePath(result);

    }

}
