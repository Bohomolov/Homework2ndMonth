package binarytree.impl;

import binarytree.ITree;
import ilist.impl.llist.LList;
import ilist.interfaces.IList;

public class BSTree implements ITree {
    private static Node root;

    public BSTree() {
        root = null;
    }

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
        root = null;
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        int size = 1;
        return size(size, root);
    }

    @Override
    public int[] toArray() {
        if (root == null) {
            throw new NullPointerException();
        }
        IList myList = new LList();
        toArray(myList, root);
        return myList.toArray();
    }


    @Override
    public void add(int value) {
        if (root == null) {
            root = new Node(value, null, null);
        } else {
            add(value, root);
        }
    }

    @Override
    public void delete(int value) {
        if (root == null) {
            return;
        }
        delete(root, value);
    }

    private void delete(Node node, int value) {
        if (node == null) {
            return;
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
        int height = 0;
        return getHeight(root, height);
    }

    private int getHeight(Node node, int height) {
        if (node.left != null || node.right != null){
            height++;
        }
        return height;
    }

    @Override
    public int nodes() {
        if (root == null) {
            return 0;
        }
        int nodeCount = 0;
        return nodes(root, nodeCount);
    }

    @Override
    public int leaves() {
        if (root == null) {
            return 0;
        }
        int leaves = 0;
        return leaves(root, leaves);
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

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private int size(int size, Node node) {

        if (node.left != null) {
            size = size(++size, node.left);
        }

        if (node.right != null) {
            size = size(++size, node.right);
        }
        return size;
    }

    private void toArray(IList myList, Node node) {
        if (node == null) {
            return;
        }
        toArray(myList, node.left);
        myList.add(node.value);
        toArray(myList, node.right);
    }

    private int nodes(Node node, int count) {
        if (node.left != null || node.right != null) {
            count++;
        }
        if (node.left != null) {
            count = nodes(node.left, count);
        }
        if (node.right != null) {
            count = nodes(node.right, count);
        }
        return count;
    }

    private int leaves(Node node, int leaves) {
        if (node.right == null && node.left == null) {
            leaves++;
        }
        if (node.left != null) {
            leaves = leaves(node.left, leaves);
        }
        if (node.right != null) {
            leaves = leaves(node.right, leaves);
        }
        return leaves;
    }

    private void add(int value, Node node) {
        if (value < node.value) {
            if (node.left == null) {
                node.left = new Node(value, null, null);
                return;
            }
            add(value, node.left);
        } else {
            if (node.right == null) {
                node.right = new Node(value, null, null);
                return;
            }
            add(value, node.right);
        }
    }
}
