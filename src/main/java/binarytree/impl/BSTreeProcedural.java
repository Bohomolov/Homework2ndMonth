package binarytree.impl;

import binarytree.ITree;

import java.util.Arrays;
import java.util.Stack;

public class BSTreeProcedural implements ITree {
    private Node root;

    @Override
    public void init(int[] array) {
        for (int i : array) {
            add(i);
        }
    }

    @Override
    public void print() {
        System.out.println(toString());
    }

    @Override
    public void clear() {
        if (root == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
                node.right = null;
            }
            if (node.left != null) {
                stack.push(node.left);
                node.left = null;
            }
        }
        root = null;
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        int size = 1;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
                size++;
            }
            if (node.left != null) {
                stack.push(node.left);
                size++;
            }
        }
        return size;
    }

    @Override
    public int[] toArray() {
        if (root == null) {
            return new int[]{};
        }
        int[] output = new int[size()];
        int i = 0;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            output[i] = node.value;
            i++;
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        Arrays.sort(output);
        return output;
    }

    @Override
    public void add(int value) {
        if (root == null) {
            root = new Node(value, null, null);
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (value < parent.value) {
                    current = current.left;
                    if (current == null) {
                        parent.left = new Node(value, null, null);
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = new Node(value, null, null);
                        return;
                    }
                }
            }
        }

    }

    @Override
    public void delete(int value) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current.value != value) {
            parent = current;
            if (value < parent.value) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return;
            }
        }
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        if (root == null) {
            return 0;
        }
        Stack<Node> stackRight = new Stack<>();
        stackRight.push(root);

        Stack<Node> stackLeft = new Stack<>();
        stackLeft.push(root);

        int leftHeight = 1;
        int rightHeight = 1;
        while (!stackRight.isEmpty()) {
            Node node = stackRight.pop();
            if (node != null) {
                if (node.right != null) {
                    stackRight.push(node.right);
                    rightHeight++;
                } else if (node.left != null) {
                    stackRight.push(node.left);
                    rightHeight++;
                }
            }
        }
        while (!stackLeft.isEmpty()) {
            Node node = stackLeft.pop();
            if (node != null) {
                if (node.left != null) {
                    stackLeft.push(node.left);
                    leftHeight++;
                } else if (node.right != null) {
                    stackLeft.push(node.right);
                    leftHeight++;
                }
            }
        }

        return Math.max(leftHeight, rightHeight);
    }

    @Override
    public int nodes() {
        if (root == null) {
            return 0;
        }
        int count = 0;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.right != null || node.left != null) {
                count++;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return count;
    }

    @Override
    public int leaves() {
        if (root == null) {
            return 0;
        }
        int count = 0;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node.right == null && node.left == null) {
                count++;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return count;
    }

    @Override
    public void reverse() {

    }

    @Override
    public String toString() {
        int[] temp = toArray();
        int size = size();
        StringBuilder output = new StringBuilder("[");
        if (temp.length != 0) {
            for (int i = 0; i < size - 1; i++) {
                output.append(temp[i]).append(", ");
            }
            output.append(temp[size - 1]);
        }
        output.append(']');
        return output.toString();
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        private Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    private Node getSuccessor(Node deleteNode) {
        Node successorParent = deleteNode;
        Node successor = deleteNode;
        Node current = deleteNode.right;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != deleteNode.right) {
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }
        return successor;
    }
}
