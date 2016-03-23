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
    boolean block;
    char character;
    
    public Node(int x, int y, Node parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        block = false;
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
    
    public void setCharacter(char c){
        this.character = c;
    }
    
    public char getCharacter() {
        return character;
    }
}

