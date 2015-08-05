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
    public static void main(String[] args) {
        BitmapHandler bmh = new BitmapHandler();
        BufferedImage map = bmh.handleMap();
        Node node = new Node(0,0,null,0);
        Node target = new Node(50, 60,null);
        AStar algo = new AStar();
        algo.AStar(node, target);
    }
}
