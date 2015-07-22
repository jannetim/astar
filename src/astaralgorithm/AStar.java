/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;

import java.util.LinkedList;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author jannetim
 */
public class AStar {

    private List open;
    private List closed;

    public void AStar(Node start, Node target, BufferedImage map) {
        //to make comparing nodes easier
        Node[][] nodes = new Node[800][600];
        nodes[target.x][target.y] = target;
        Node current;
        
        open = new LinkedList<Node>();
        closed = new LinkedList<Node>();

        open.add(start);
        while (1 == 1) {
            current = lookForLowestF();
            closed.add(current);
            //target node found
            if (current.equals(target)) {
                break;
            }
            //can't find the target node
            if (open.isEmpty()) {
                break;
            }
            // consider every neighbor of current
            for (Node node : getNeighbors(start, map)) {
                if (!closed.contains(nodes[node.x][node.y]) && !closed.contains(nodes[node.x][node.y])) {
                    node.setGCost(calculateGCost(node));
                    if (!open.contains(nodes[node.x][node.y]) && !open.contains(nodes[node.x][node.y])) {
                        open.add(node);
                    } else {
                        if (nodes[node.x][node.y].g > node.getGCost()) {
                            open.remove(nodes[node.x][node.y]);
                            open.add(node);
                        }
                    }
                }
            }
        }
        
        //work the path
        
    }

    private Node lookForLowestF() {
        return (Node) open.remove(1);
    }

    private Node[] getNeighbors(Node n, BufferedImage map) {
        Node a = new Node(n.x - 1, n.y + 1, n);
        Node b = new Node(n.x, n.y + 1, n);
        Node c = new Node(n.x + 1, n.y + 1, n);
        Node d = new Node(n.x + 1, n.y, n);
        Node e = new Node(n.x + 1, n.y - 1, n);
        Node f = new Node(n.x, n.y - 1, n);
        Node g = new Node(n.x - 1, n.y - 1, n);
        Node h = new Node(n.x - 1, n.y, n);
        Node[] nodes = {a, b, c, d, e, f, g, h};
        return nodes;
    }

    private int calculateFCost(Node n, Node target) {
        return (calculateHCost(n,target) + calculateGCost(n));
    }

    private int calculateGCost(Node n) {
        return n.parent.g + 10;

    }

    private int calculateHCost(Node n, Node target) {
        int a = Math.abs(n.x - n.y);
        int b = Math.abs(target.x - target.y);
        if (a<b) {
            return a;
        } else {
            return b;
        }
    }

    private class Node {

        public Node(int x, int y, Node parent) {
            this.x = x;
            this.y = y;
            this.parent = parent;
        }
        Node parent;
        int x;
        int y;
        int g;

        public void setGCost(int g) {
            this.g = g;
        }

        public int getGCost() {
            return g;
        }
    }
}
