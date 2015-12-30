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
    //private List<Node> open;
    //private List<Node> closed;
    private MinHeap open;
    private MinHeap closed;
    private char[][] map;

    /**
     * A*-algorithm
     *
     * @param start
     * @param target
     * @return
     */
    public String AStar(Node start, Node target) throws NodeNotFoundException {
        map = new char[10][10];
        Node[][] nodemap = new Node[10][10];
        for (int i = 0; i < nodemap.length; i++) {
            for (int j = 0; j < nodemap[i].length; j++) {
                nodemap[i][j] = new Node(i, j, null);
            }
        }
        nodemap[target.y][target.x] = target;
        nodemap[start.y][start.x] = start;
        Node current;

        //open = new LinkedList<>();
        //closed = new LinkedList<>();
        open = new MinHeap();
        closed = new MinHeap();
        open.add(start);
        while (!open.isEmpty()) {
            current = lookForLowestF();
            System.out.println(current.toString());
            System.out.println("cur  g " + current.g);
            System.out.println("cur  h " + calculateHCost(current, target));
            mapHelper(current, target);
            closed.add(current);
            if (current.equals(target)) {
                System.out.println("!");
                workThePath(target);
                return "";
            }

            open.remove(current);
            
            for (Node node : considerNeighbors(current, nodemap)) {
                if (!closed.contains(node)) {
                    if (!open.contains(node)) {
                        node.setParent(current);
                        node.setGCost(current.g + node.getCostFromPredecessor());
                        node.setFCost(calculateFCost(node, target));
                        open.add(node);
                    } else {
                        if ((current.g + node.getCostFromPredecessor()) < node.g) {  
                            node.setParent(current);
                            node.setGCost(current.g + node.getCostFromPredecessor());
                            node.setFCost(calculateFCost(node, target));
                        }
                    }
                } else {
                    continue;
                }
            }
        }
        System.out.println("Can't find path");
        return "Ei";
        //work the path
    }

    private Node lookForLowestF() {
        //Node lowestF = open.get(0);
        Node lowestF = open.findMin();
        /*for (Node n : open) {
         if (n.f < lowestF.f) {
         lowestF = n;
         }
         }*/
        return lowestF;
    }

    private ArrayList<Node> considerNeighbors(Node n, Node[][] map) {
        ArrayList nodes = new ArrayList();
        if (n.x > 0 && n.y < map.length - 1) {
            Node a = map[n.y + 1][n.x - 1];
            a.setCostFromPredecessor(14);
            nodes.add(a);
        }
        if (n.y < map.length - 1) {
            Node b = map[n.y + 1][n.x];
            b.setCostFromPredecessor(10);
            nodes.add(b);
        }
        if (n.x < map[0].length - 1 && n.y < map.length - 1) {
            Node c = map[n.y + 1][n.x + 1];

            c.setCostFromPredecessor(14);
            nodes.add(c);
        }
        if (n.x < map[0].length - 1) {
            Node d = map[n.y][n.x + 1];

            d.setCostFromPredecessor(10);
            nodes.add(d);
        }
        if (n.x < map[0].length - 1 && n.y > 0) {
            Node e = map[n.y - 1][n.x + 1];

            e.setCostFromPredecessor(14);
            nodes.add(e);
        }
        if (n.y > 0) {
            Node f = map[n.y - 1][n.x];

            f.setCostFromPredecessor(10);
            nodes.add(f);
        }
        if (n.x > 0 && n.y > 0) {
            Node g = map[n.y - 1][n.x - 1];

            g.setCostFromPredecessor(14);
            nodes.add(g);
        }
        if (n.x > 0) {
            Node h = map[n.y][n.x - 1];

            h.setCostFromPredecessor(10);
            nodes.add(h);
        }

        return nodes;
    }

    /**
     * Calculates F = G + H
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
    private int calculateHCost(Node node, Node target) {
         /*int a = Math.abs(node.x - node.y);
         int b = Math.abs(target.x - target.y);
         if (a < b) {
         return a * 2;
         } else {
         return b * 2;
         }*/
        int dx = Math.abs(node.x - target.x);
        int dy = Math.abs(node.y - target.y);
        //System.out.println((dx + dy) + (1 - 2 * 1) * Math.min(dx, dy));
        return 10 * (dx + dy) + (14 - 2 * 10) * Math.min(dx, dy);
    }

    private void workThePath(Node target) {
        while (target != null) {
            System.out.println(target.toString() + "...");
            target = target.parent;
        }
    }

    private void mapHelper(Node current, Node target) {
        map[current.y][current.x] = 'x';
        map[target.y][target.x] = '0';
        for (int i = 0; i < map.length; i++) {
            System.out.println("");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
        }
        System.out.println("");
        System.out.println("");
        //System.out.println(current.f);
        map[current.y][current.x] = '#';
    }
}
