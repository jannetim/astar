/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;

import java.util.LinkedList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author jannetim
 */
public class AStar {

    private PriorityQueue q;
    private List<Node> open;
    private List<Node> closed;

    /**
     * A*-algorithm
     *
     * @param start
     * @param target
     * @param map
     */
    public String AStar(Node start, Node target) {        
        Node[][] nodemap = new Node[100][100];
        for (int i = 0; i < nodemap.length; i++) {
            for (int j = 0; j < nodemap[i].length; j++) {
                nodemap[i][j] = new Node(i, j, null);
            }
        }
        nodemap[target.x][target.y] = target;
        Node current;

        open = new LinkedList<Node>();
        closed = new LinkedList<Node>();

        open.add(start);
        while (!open.isEmpty()) {
            current = lookForLowestF(target);
            System.out.println(current.toString());
            closed.add(current);
            
            if (current.equals(target)){
                System.out.println("!");
                workThePath(target);
                return "Jee";
            }
            
            open.remove(current);
            // consider every neighbor of current
            for (Node node : considerNeighbors(current, nodemap)) {
                if (!closed.contains(node)) {
                    if (!open.contains(node)) {
                        node.setParent(current);
                        node.setGCost(current.g + node.getCostFromPredecessor());
                        node.setFCost(calculateFCost(node, target));
                        open.add(node);                        
                    } else {
                        if (current.g + node.getCostFromPredecessor() < node.g) {
                            node.setParent(current);
                            node.setGCost(current.g + node.getCostFromPredecessor());
                            node.setFCost(calculateFCost(node, target));
                        }
                    }
                }
            }
        }
        System.out.println("Can't find path");
        return "Ei";
        //work the path
    }

    private Node lookForLowestF(Node target) {
        Node lowestF = open.get(0);
        for (Node n : open) {
            if (n.f < lowestF.f) {
                lowestF = n;
            }
        }
        return lowestF;
    }

    private ArrayList<Node> considerNeighbors(Node n, Node[][] map) {
        ArrayList nodes = new ArrayList();
        if (n.x > 0 && n.y < map.length - 1) {
            Node a = map[n.x - 1][n.y + 1];
            a.setCostFromPredecessor(14);
            nodes.add(a);
        }
        if (n.y < map.length - 1) {
            Node b = map[n.x][n.y + 1];
            b.setCostFromPredecessor(10);
            nodes.add(b);
        }
        if (n.x < map[0].length - 1 && n.y < map.length - 1) {
            Node c = map[n.x + 1][n.y + 1];

            c.setCostFromPredecessor(14);
            nodes.add(c);
        }
        if (n.x < map[0].length - 1) {
            Node d = map[n.x+1][n.y];
            
            d.setCostFromPredecessor(10);
            nodes.add(d);
        }
        if (n.x < map[0].length - 1 && n.y > 0) {
            Node e = map[n.x + 1][n.y - 1];
            
            e.setCostFromPredecessor(14);
            nodes.add(e);
        }
        if (n.y > 0) {
            Node f = map[n.x][n.y - 1];
            
            f.setCostFromPredecessor(10);
            nodes.add(f);
        }
        if (n.x > 0 && n.y > 0) {
            Node g = map[n.x - 1][n.y - 1];
            
            g.setCostFromPredecessor(14);
            nodes.add(g);
        }
        if (n.x > 0) {
            Node h = map[n.x - 1][n.y];
            
            h.setCostFromPredecessor(10);
            nodes.add(h);
        }

        return nodes;
    }

    /**
     * Calculates overall cost of F = G + H
     *
     * @param n
     * @param target
     * @return
     */
    private int calculateFCost(Node n, Node target) {
        return (calculateHCost(n, target) + n.g);
    }

    /**
     * Heuristic calculation of estimated cost to target
     *
     * @param n
     * @param target
     * @return
     */
    private int calculateHCost(Node n, Node target) {
        int a = Math.abs(n.x - n.y);
        int b = Math.abs(target.x - target.y);
        if (a < b) {
            return a * 2;
        } else {
            return b * 2;
        }
    }
    
    private void workThePath(Node target) {
        while(target!=null){
            System.out.println(target.toString());
            target = target.parent;
        }
    }
}
