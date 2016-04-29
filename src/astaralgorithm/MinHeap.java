/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astaralgorithm;

/**
 *  Minimum heap to be used with A*
 * @author jannetim
 */
public class MinHeap {
    /**
     * Root node of the heap
     */
    HeapNode root;
    /**
     * Last node in the heap
     */
    HeapNode lastNode;
    /**
     * Amount of nodes in the heap
     */
    int count;

    /**
     * Inserts a new node in to the heap
     * @param node Node to be inserted
     */
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

    /**
     * 
     * @return 
     */
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

    /**
     * Add new node and then adjust the branches
     */
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
    /**
     * Removes the node with lowest value
     * @return Removed node
     */
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
    /**
     * Removes the node and adjusts the brances
     */
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
    /**
     * Checks whether heap contains some certain node 
     * @param node Node that is to be looked for
     * @return Boolean value if the node was found or not
     * @throws NodeNotFoundException 
     */
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
    /**
     * Searches for some certain node
     * @param node Node to be looked for
     * @return Found node
     * @throws NodeNotFoundException 
     */
    public Node find(Node node) throws NodeNotFoundException {
        HeapNode current = findNode(node, root);
        if (current == null) {
            throw new NodeNotFoundException("find(Node node)");
        }
        return current.node;
    }
    /**
     * Finds heapNode connected to some certain node
     * @param node A* node
     * @param next HeapNode to be looked for
     * @return Found HeapNode
     */
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
    /**
     * Removes some certain node
     * @param node Node to be removed
     */
    public void remove(Node node) {
        removeMin();
    }
    /**
     * Finds minimum value node
     * @return Found min node
     */
    public Node findMin() {
        if (count == 0) {
            return null;
        }
        return root.node;
    }
    /**
     * Checks if the heap is empty
     * @return boolean-value whether heap is empty
     */
    public boolean isEmpty() {
        return (count == 0);
    }

    /**
     * Calls insert
     * @param node 
     */
    public void add(Node node) {
        insert(node);
    }
    /**
     * HeapNode used with the heap. Encapsules hierarchy data within the heap and actual node used with A*
     */
    private class HeapNode {

        public HeapNode(Node node) {
            this.node = node;
        }
        HeapNode root, left, right;
        HeapNode parent;
        Node node;
    }
}
