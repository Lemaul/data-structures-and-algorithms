package dsaa.lab08;

import java.util.Stack;

public class BST<T> {

    private class Node{
        T value;
        Node left,right,parent;
        public Node(T v) {
            value=v;
        }
        public Node(T value, Node left, Node right, Node parent) {
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

    public T getElement(T toFind) {
        Node current = root;
        while (current != null) {
            if (((Link)current.value).compareTo( (Link)toFind) == 0) return current.value;
            else if (((Link)current.value).compareTo( (Link)toFind ) < 0)
                current = current.right;
            else if (((Link)current.value).compareTo( (Link)toFind ) > 0)
                current = current.left;
        }
        return null;
    }

    public Node getNode(T toFind) {
        Node current = root;
        while (current != null) {
            if (((Link)current.value).compareTo( (Link)toFind) == 0) return current;
            else if (((Link)current.value).compareTo( (Link)toFind ) < 0)
                current = current.right;
            else if (((Link)current.value).compareTo( (Link)toFind ) > 0)
                current = current.left;
        }
        return null;
    }

    public T findMin(Node n) {
        Node current = n;
        while (current.left != null)
            current = current.left;
        return current.value;
    }

    public T findMax(Node n) {
        Node current = n;
        while (current.right != null)
            current = current.right;
        return current.value;
    }

    public T successor(T elem) {
        if (getElement(elem) == null) return null;
        if (((Link)elem).equals((Link)findMax(root))) return null;
        if (getNode(elem).right != null) return findMin(getNode(elem).right);
        Node node = getNode(elem);
        while (((Link)node.parent.value).compareTo((Link)node.value) < 0)
            node = node.parent;
        return node.parent.value;
    }

    public boolean add(T elem) {
        if (root == null) {
            root = new Node(elem);
            size++;
            return true;
        }
        Node current = root;
        while (current != null) {
            if (((Link)current.value).compareTo( (Link)elem ) < 0) {
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


    public T remove(T value) {
        if (getNode(value) == null) return null;
        Node node = getNode(value);
        T nodeValue = node.value;
        if (size() == 1) {
            T val = getElement(value);
            clear();
            return val;
        }

        if (node.left == null && node.right == null) {
            if (((Link)node.parent.left.value).equals((Link)node.value) ) node.parent.left = null;
            else node.parent.right = null;
            size--;
        }
        else if (node.left == null || node.right == null) {
            Node temp = node.left == null ? node.right : node.left;
            size--;
            if (node.parent != null) // normal situation
                if (((Link)node.parent.left.value).equals((Link)node.value) ) node.parent.left = temp;
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
            T successorValue = succ.value;
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