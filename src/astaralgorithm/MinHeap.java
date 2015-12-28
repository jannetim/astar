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
public class MinHeap {
    HeapNode root;
    HeapNode last;
    
    public void insert(Node node) {
        HeapNode heapNode = new HeapNode(node);
        if (root==null) {
            root = heapNode;
        } else {
            HeapNode nextParent = getNextParent();
            if (nextParent.left == null) {
                nextParent.left = heapNode;
            } else {
                nextParent.right = heapNode;
            }
            heapNode.parent = nextParent;
        }
        
    }
    
    private HeapNode getNextParent() {
        HeapNode result = last;
        while ((result != root) && (result != result.parent.left)) {
            result = result.parent;
        }
        if (result != root) {
            if (result.parent.right == null) {
                result = result.parent;
            } else {
                result = result.parent.right;
                while (result.left != null) {
                    result = result.left;
                }
            }
        } else {
            while (result.left != null) {
                result = result.left;
            }
        }
        return result;
    }
    
    private class HeapNode{
        public HeapNode(Node node){
            this.node = node;
        }
        HeapNode root, left, right;
        HeapNode parent;
        Node node;
    }
}
