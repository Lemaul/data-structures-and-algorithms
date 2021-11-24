package classes.class05;

import java.util.Stack;

public class BST {

    private class Node{
        int value;
        Node left,right,parent;
        public Node(int v) {
            value=v;
        }
        public Node(int value, Node left, Node right, Node parent) {
            super();
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private Node root=null;
    private int size;

    public BST() { }

    public int getElement(int toFind) {
        Node current = root;
        while (current != null) {
            if (current.value == toFind) return current.value;
            else if (current.value < toFind)
                current = current.right;
            else
                current = current.left;
        }
        return -1;
    }

    public Node getNode(int toFind) {
        Node current = root;
        while (current != null) {
            if (current.value == toFind) return current;
            else if (current.value < toFind)
                current = current.right;
            else
                current = current.left;
        }
        return null;
    }

    public int findMin(Node n) {
        Node current = n;
        while (current.left != null)
            current = current.left;
        return current.value;
    }

    public int findMax(Node n) {
        Node current = n;
        while (current.right != null)
            current = current.right;
        return current.value;
    }

    public int successor(int elem) {
        if (getElement(elem) == -1) return -1;
        if (elem == findMax(root)) return -1;
        if (getNode(elem).right != null) return findMin(getNode(elem).right);
        Node node = getNode(elem);
        while (node.parent.value < node.value)
            node = node.parent;
        return node.parent.value;
    }

    public boolean add(int elem) {
        if (root == null) {
            root = new Node(elem);
            size++;
            return true;
        }
        Node current = root;
        while (current != null) {
            if (current.value < elem) {
                if (current.right == null) {
                    current.right = new Node(elem, null, null, current);
                    size++;
                    return true;
                }
                current = current.right;
            }
            else {
                if (current.left == null) {
                    current.left = new Node(elem, null, null, current);
                    size++;
                    return true;
                }
                current = current.left;
            }
        }
        return false;
    }


    public int remove(int value) {
        if (getNode(value) == null) return -1;
        Node node = getNode(value);
        int nodeValue = node.value;
        if (size() == 1) {
            int val = getElement(value);
            clear();
            return val;
        }

        if (node.left == null && node.right == null) {
            if (node.parent.left.value == node.value) node.parent.left = null;
            else node.parent.right = null;
            size--;
        }
        else if (node.left == null || node.right == null) {
            Node temp = node.left == null ? node.right : node.left;
            size--;
            if (node.parent != null) // normal situation
                if (node.parent.left.value == node.value) node.parent.left = temp;
                else node.parent.right = temp;
            else { // in case of root with one child deletion
                if (node.left == null) {
                    root = node.right;
                    root.parent = null;
                } else {
                    root = node.left;
                    root.parent = null;
                }
            }

        }
        else {
            Node succ = getNode(successor(value));
            int successorValue = succ.value;
            remove(successorValue);
            node.value = successorValue;
        }
        return nodeValue;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public String toStringInOrder() {
        if (size() == 0) return "";
        String inOrder = "";
        Stack<Node> s = new Stack<>();
        Node current = root;
        while (current != null || !s.empty()) {
            if (current == null) {
                current = s.pop();
                inOrder += current.value + ", ";
                current = current.right;
            } else {
                s.push(current);
                current = current.left;
            }
        }
        return inOrder.substring(0, inOrder.length()-2);
    }

    public String toStringPreOrder() {
        if (size() == 0) return "";
        String preOrder = "";
        Stack<Node> s = new Stack<>();
        s.push(root);
        while (!s.empty()) {
            Node current = s.pop();
            preOrder += current.value + ", ";
            if (current.right != null) s.push(current.right);
            if (current.left != null) s.push(current.left);
        }
        return preOrder.substring(0, preOrder.length()-2);
    }

    public String toStringPostOrder() {
        if (size() == 0) return "";
        String postOrder = "";
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(root);
        while (!s1.empty()) {
            Node current = s1.pop();
            s2.push(current);
            if (current.left != null) s1.push(current.left);
            if (current.right != null) s1.push(current.right);
        }
        while (!s2.empty())
            postOrder += s2.pop().value + ", ";
        return postOrder.substring(0, postOrder.length()-2);
    }

}
