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
    HeapNode lastNode;
    int count;

    public void insert(Node node) {
        HeapNode heapNode = new HeapNode(node);
        // check if there's no root, i.e. the heap is empty
        if (root == null) {
            root = heapNode;
        } else {
            // fetch a parent for the new node
            HeapNode nextParent = getNextParent();
            if (nextParent.left == null) {
                nextParent.left = heapNode;
            } else {
                nextParent.right = heapNode;
            }
            heapNode.parent = nextParent;
        }
        lastNode = heapNode;
        count++;
        if (count > 1) {
            heapifyAdd();
        }
    }

    private HeapNode getNextParent() {
        HeapNode result = lastNode;
        while ((result != root) && (result != result.parent.left)) {
            result = result.parent;
        }
        if (result != root) {
            //right child is the next possible place, if null -> new parent
            if (result.parent.right == null) {
                result = result.parent;
            } else {
                result = result.parent.right;
                //goes as far left as it can
                while (result.left != null) {
                    result = result.left;
                }
            }
        } else {    // if root...
            while (result.left != null) {
                result = result.left;
            }
        }
        return result;
    }

    private void heapifyAdd() {
        Node temp;
        HeapNode next = lastNode;

        while ((next != root) && (((Comparable) next.node).compareTo(next.parent.node) < 0)) {
            temp = next.node;
            next.node = next.parent.node;
            next.parent.node = temp;
            next = next.parent;
        }
    }

    public Node removeMin() {
        if (count == 0) {
            return null;
        }
        Node minimum = root.node;

        if (count == 1) {
            root = null;
            lastNode = null;
        } else {
            HeapNode nextLast = getNewLastNode();
            if (lastNode.parent.left == lastNode) {
                lastNode.parent.left = null;
            } else {
                lastNode.parent.right = null;
            }
            root.node = lastNode.node;
            lastNode = nextLast;
            heapifyRemove();
        }
        count--;
        return minimum;
    }

    private HeapNode getNewLastNode() {
        HeapNode result = lastNode;

        while ((result != root) && (result.parent.left == result)) {
            result = result.parent;
        }

        if (result != root) {
            result = (HeapNode) result.parent.left;
        }

        while (result.right != null) {
            result = (HeapNode) result.right;
        }
        return result;
    }

    private void heapifyRemove() {
        Node temp;
        HeapNode node = (HeapNode) root;
        HeapNode left = (HeapNode) node.left;
        HeapNode right = (HeapNode) node.right;
        HeapNode next;

        if ((left == null) && (right == null)) {
            next = null;
        } else if (left == null) {
            next = right;
        } else if (right == null) {
            next = left;
        } else if (((Comparable) left.node).compareTo(right.node) < 0) {
            next = left;
        } else {
            next = right;
        }

        while ((next != null) && (((Comparable) next.node).compareTo(node.node) < 0)) {
            temp = node.node;
            node.node = next.node;
            next.node = temp;
            node = next;
            left = (HeapNode) node.left;
            right = (HeapNode) node.right;
            if ((left == null) && (right == null)) {
                next = null;
            } else if (left == null) {
                next = right;
            } else if (right == null) {
                next = left;
            } else if (((Comparable) left.node).compareTo(right.node) < 0) {
                next = left;
            } else {
                next = right;
            }
        }
    }

    public boolean contains(Node node) throws NodeNotFoundException {
        Node temp;
        boolean found = false;

        try {
            temp = find(node);
            found = true;
        } catch (Exception NodeNotFoundException) {
            found = false;
        }
        return found;
    }

    public Node find(Node node) throws NodeNotFoundException {
        HeapNode current = findNode(node, root);
        if (current == null) {
            throw new NodeNotFoundException("find(Node node)");
        }
        return current.node;
    }

    private HeapNode findNode(Node node, HeapNode next) {
        if (next == null) {
            return null;
        }
        if (next.node.equals(node)) {
            return next;
        }

        //recursive search
        HeapNode temp = findNode(node, next.left);

        if (temp == null) {
            temp = findNode(node, next.right);
        }
        return temp;
    }

    public void remove(Node node) {
        removeMin();
    }

    public Node findMin() {
        if (count == 0) {
            return null;
        }
        return root.node;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public void add(Node node) {
        insert(node);
    }

    private class HeapNode {

        public HeapNode(Node node) {
            this.node = node;
        }
        HeapNode root, left, right;
        HeapNode parent;
        Node node;
    }
}
