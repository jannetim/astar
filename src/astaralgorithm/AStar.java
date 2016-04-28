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

    private MinHeap open;
    private MinHeap closed;
    private char[][] map;
    private char[][] blocked;
    private Node[][] nodemap;
    int mapWidth;
    int mapHeight;
    boolean blocks;

    /**
     * A*-algorithm
     *
     * @param start
     * @param target
     * @return
     */
    public AStar(int width, int height) {
        this.mapWidth = width;
        this.mapHeight = height;
        map = new char[mapHeight][mapWidth];
        nodemap = new Node[mapHeight][mapWidth];
        populateMap();
    }
    public AStar(int width, int height, boolean blocks) {
        this.mapWidth = width;
        this.mapHeight = height;
        map = new char[mapHeight][mapWidth];
        nodemap = new Node[mapHeight][mapWidth];
        populateMap();
        this.blocks = blocks;
    }
    
    private void populateMap() {
        for (int i = 0; i < nodemap.length; i++) {
            for (int j = 0; j < nodemap[i].length; j++) {
                nodemap[i][j] = new Node(j, i, null);
            }
        }
    }

    public Node AStar(Node start, Node target) throws NodeNotFoundException {

        //generateBlockedNodes();
        nodemap[target.y][target.x] = target;
        nodemap[start.y][start.x] = start;
        Node current;

        open = new MinHeap();
        closed = new MinHeap();
        start.setFCost(calculateFCost(start, target));
        open.add(start);
        while (!open.isEmpty()) {
            current = lookForLowestF();
            //System.out.println(current.toString());
            //System.out.println("cur  h " + calculateHCost(current, target));
            //mapHelper(current, target);
            closed.add(current);
            if (current.equals(target)) {
                System.out.println("!");
                workThePath(target);
                return current;
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
        return null;
        //work the path
    }

    private Node lookForLowestF() {
        //Node lowestF = open.get(0);
        Node lowestF = open.findMin();

        return lowestF;
    }

    private ArrayList<Node> considerNeighbors(Node n, Node[][] map) {
        ArrayList neighborNodes = new ArrayList();
        if (n.x > 0 && n.y < map.length - 1) {
            if (!map[n.y + 1][n.x - 1].isBlocked()) {
                Node a = map[n.y + 1][n.x - 1];
                a.setCostFromPredecessor(14);
                neighborNodes.add(a);
            }

        }
        if (n.y < map.length - 1) {
            if (!map[n.y + 1][n.x].isBlocked()) {
                Node b = map[n.y + 1][n.x];
                b.setCostFromPredecessor(10);
                neighborNodes.add(b);
            }
        }
        if (n.x < map[0].length - 1 && n.y < map.length - 1) {
            if (!map[n.y + 1][n.x + 1].isBlocked()) {
                Node c = map[n.y + 1][n.x + 1];
                c.setCostFromPredecessor(14);
                neighborNodes.add(c);
            }
        }
        if (n.x < map[0].length - 1) {
            if (!map[n.y][n.x + 1].isBlocked()) {
                Node d = map[n.y][n.x + 1];
                d.setCostFromPredecessor(10);
                neighborNodes.add(d);
            }
        }
        if (n.x < map[0].length - 1 && n.y > 0) {
            if (!map[n.y - 1][n.x + 1].isBlocked()) {
                Node e = map[n.y - 1][n.x + 1];
                e.setCostFromPredecessor(14);
                neighborNodes.add(e);
            }
        }
        if (n.y > 0) {
            if (!map[n.y - 1][n.x].isBlocked()) {
                Node f = map[n.y - 1][n.x];
                f.setCostFromPredecessor(10);
                neighborNodes.add(f);
            }
        }
        if (n.x > 0 && n.y > 0) {
            if (!map[n.y - 1][n.x - 1].isBlocked()) {
                Node g = map[n.y - 1][n.x - 1];
                g.setCostFromPredecessor(14);
                neighborNodes.add(g);
            }
        }
        if (n.x > 0) {
            if (!map[n.y][n.x - 1].isBlocked()) {
                Node h = map[n.y][n.x - 1];
                h.setCostFromPredecessor(10);
                neighborNodes.add(h);
            }
        }
        return neighborNodes;
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
        map = new char[mapHeight][mapWidth];
        generateBlockedNodes();
        while (target != null) {
            System.out.println("");
            //System.out.println(target.toString() + "...");
            map[target.y][target.x] = 'x';
            target = target.parent;
        }
        for (int i = 0; i < map.length; i++) {
            System.out.println("");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
        }
    }

    private void mapHelper(Node current, Node target) {

        map[current.y][current.x] = '+';
        map[target.y][target.x] = '0';
        current = current.parent;
        while (current != null) {
            map[current.y][current.x] = 'x';
            current = current.parent;
        }
        for (int i = 0; i < map.length; i++) {
            System.out.println("");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
        }

        System.out.println("");
        System.out.println("");

    }

    public void generateBlockedNodes() {
        if (blocks) {
            for (int i = 0; i < 9; i++) {
                map[i][mapWidth / 4] = 'B';
                nodemap[i][mapWidth / 4].setBlocked();
            }
        }
    }

}
