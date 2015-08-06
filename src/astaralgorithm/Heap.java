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
public class Heap<T extends Comparable<T>>{
    
    private Node<T> head;
    
    public Heap() {
        head = null;
    }
    
    public Heap(Node<T> head) {
        this.head = head;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void clear() {
        head = null;
    }
    
    public void insert(T key) {
        Node<T> node = new Node<T>(key);
        
    }
    
    public T peek() {
        return head.key;
    }
    
    private class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        public T key;
        public int degree;
        public Node<T> parent;
        public Node<T> child;
        public Node<T> sibling;
        
        public Node(T key) {
            this.key = key;
        }
        
        @Override
        public int compareTo(Node<T> o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
