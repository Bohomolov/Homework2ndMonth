package binarytree.impl;

import binarytree.ITree;
import ilist.impl.llist.LList;
import ilist.interfaces.IList;

public class BSTree implements ITree {
    private Node root;

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
            return new int[]{};
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

    public void delete(Node node, int value) {
        if (node == null) {
            throw new IllegalArgumentException("Root is null.");
        }

        Node parentNode = findParentNodeByValue(root, value);
        if (parentNode == null) {
            throw new IllegalArgumentException("Value is not found");
        }
        boolean isLeftChild = parentNode.left.value == value;
        Node deleteNode = isLeftChild ? parentNode.left : parentNode.right;
        if (deleteNode.left == null && deleteNode.right == null) {
            if (root.value == value) {
                root = null;
            } else if (isLeftChild) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
        } else if (deleteNode.right == null) {
            if (root.value == value) {
                root = root.left;
            } else if (isLeftChild) {
                parentNode.left = deleteNode.left;
            } else {
                parentNode.right = deleteNode.left;
            }
        } else if (deleteNode.left == null) {
            if (root.value == value) {
                root = root.right;
            } else if (isLeftChild) {
                parentNode.left = deleteNode.right;
            } else {
                parentNode.right = deleteNode.right;
            }
        } else {
            Node successor = getSuccessor(deleteNode);
            if (deleteNode == root) {
                root = successor;
            } else if (isLeftChild) {
                parentNode.left = successor;
            } else {
                parentNode.right = successor;
            }
            successor.left = deleteNode.left;
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

    private Node findParentNodeByValue(Node node, int value) {
        if (root.value == value) {
            return root;
        }
        Node parent = null;
        if (value == node.right.value) {
            parent = node;
        } else if (node.left.value == value) {
            parent = node;
        } else if (value < node.value) {
            findParentNodeByValue(node.left, value);
        } else {
            findParentNodeByValue(node.right, value);
        }
        return parent;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
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

        private Node(int value, Node left, Node right) {
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
        if (node != null) {
            toArray(myList, node.left);
            myList.add(node.value);
            toArray(myList, node.right);
        }
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
            } else {
                add(value, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(value, null, null);
            } else {
                add(value, node.right);
            }
        }
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}
