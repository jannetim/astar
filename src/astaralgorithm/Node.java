/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;

/**
 *
 * @author jannetim
 */
public class Node implements Comparable<Node> {
    /**
     * parent node
     */
    Node parent;
    /**
     * x-axis value
     */
    int x;
    /**
     * y-axis value
     */
    int y;
    /**
     * f-cost in A*
     */
    int f;
    /**
     * g-cost in A*
     */
    int g;
    /**
     * Cost from some node (when compared) to this
     */
    int costFromPredecessor;
    /**
     * Whether this node is blocked (i.e. can or can't be traversed) or not in the map
     */
    boolean block;
    /**
     * Not in use at the moment. Made to replace the charmap-functionality. for further development
     */

    /**
     * 
     * @param x Nodes x-value
     * @param y Nodes y-value
     * @param parent Nodes parent
     */
    public Node(int x, int y, Node parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        block = false;
    }
    /**
     * 
     * @param x Nodes x-value
     * @param y Nodes y-value
     * @param parent Nodes parent
     * @param g Nodes g-value
     */
    public Node(int x, int y, Node parent, int g) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.g = g;
    }
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    /**
     * sets nodes g-cost
     * @param g g-cost value
     */
    public void setGCost(int g) {
        this.g = g;
    }
    
    public void setCostFromPredecessor(int c) {
        this.costFromPredecessor = c;
    }
    
    public int getCostFromPredecessor() {
        return this.costFromPredecessor;
    }
    
    public int getGCost() {
        return g;
    }
    
    public void setFCost(int f) {
        this.f = f;
    }
    
    public int getFCost() {
        return this.f;
    }
    
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Node getParent() {
        return this.parent;
    }
    @Override
    public String toString(){
        return "X: " + this.x + ", Y: " + this.y + ", F cost: " + this.f + ", G cost: " + this.g;
    }

    @Override
    public int compareTo(Node o) {
        return this.f - o.getFCost();
    }

    public void setBlocked() {
        this.block = true;
    }
    
    public boolean isBlocked() {
        return block;
    }
}

