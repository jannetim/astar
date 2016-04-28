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

    /**
     * Minimum heap for "open" nodes
     */
    private MinHeap open;
    /**
     * Minimum heap for "closed" nodes
     */
    private MinHeap closed;
    /**
     * Map of nodes i.e. the graph where the path is to be found
     */
    private Node[][] nodemap;

    
    public AStar(Node[][] nodemap) {
        this.nodemap = nodemap;
    }
    
    /**
     * The A*-algorithm
     * @param start Starting node
     * @param target Goal node
     * @return Found goal node
     * @throws NodeNotFoundException 
     */
    public Node AStar(Node start, Node target) throws NodeNotFoundException {
        nodemap[target.y][target.x] = target;
        nodemap[start.y][start.x] = start;
        Node current;

        open = new MinHeap();
        closed = new MinHeap();
        start.setFCost(calculateFCost(start, target));
        open.add(start);
        while (!open.isEmpty()) {
            current = lookForLowestF();
            closed.add(current);
            if (current.equals(target)) {
                System.out.println("!");
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
    }

    private Node lookForLowestF() {
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
        int dx = Math.abs(node.x - target.x);
        int dy = Math.abs(node.y - target.y);
        return 10 * (dx + dy) + (14 - 2 * 10) * Math.min(dx, dy);
    }
}
