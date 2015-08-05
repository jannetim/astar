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
    Node parent;
    int x;
    int y;
    int f;
    int g;
    int costFromPredecessor;
    
    public Node(int x, int y, Node parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public Node(int x, int y, Node parent, int g) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.g = g;
    }


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
    
    public String toString(){
        return "X: " + this.x + ", Y: " + this.y;
    }

    public int compareTo(Node o) {
        if (this.x==o.x && this.y==o.y){
            return 0;
        } else {
            return 1;
        }
    }

}

