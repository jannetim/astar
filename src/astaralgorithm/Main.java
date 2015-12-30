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
        BitmapHandler bmh = new BitmapHandler();
        BufferedImage map = bmh.handleMap();
        //Node node = new Node(0,0,null,0);
        //Node target = new Node(50, 60,null);
        Node node = new Node(0,0,null,0);
        Node target = new Node(9, 9,null);
        AStar algo = new AStar();
        algo.AStar(node, target);
        
        /*
        MinHeap heap = new MinHeap();
        Node node1 = new Node(50, 60,null);
        node1.setFCost(50);
        Node node2 = new Node(50, 60,null);
        node2.setFCost(40);
        Node node3 = new Node(50, 60,null);
        node3.setFCost(20);
        heap.add(node2);
        heap.add(node1);
        heap.add(node3);
        System.out.println(heap.findMin());
        heap.removeMin();
        System.out.println(heap.findMin());
        System.out.println(heap.contains(node3));*/
    }
}
