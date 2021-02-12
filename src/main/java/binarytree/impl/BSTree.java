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
        if (root == null) {
            return;
        }
        clear(root);
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
        rootIsNull(root);
        Node removeNode = getNodeByValue(root, value);
        Node parentNode = getParentNode(root, removeNode);
        if (parentNode == null || removeNode == null) {
            throw new IllegalArgumentException("Value is not found.");
        }
        boolean isChildLeft = parentNode.left == removeNode;

        if (removeNode.left == null && removeNode.right == null) {
            if (removeNode == root) {
                root = null;
            } else if (isChildLeft) {
                parentNode.left = null;
            } else {
                parentNode.right = null;
            }
        } else if (removeNode.right == null) {
            if (removeNode == root) {
                root = removeNode.left;
            } else if (isChildLeft) {
                parentNode.left = removeNode.left;
            } else {
                parentNode.right = removeNode.left;
            }
        } else if (removeNode.left == null) {
            if (removeNode == root) {
                root = removeNode.right;
            } else if (isChildLeft) {
                parentNode.left = removeNode.right;
            } else {
                parentNode.right = removeNode.right;
            }
        } else {
            Node successor = getSuccessor(removeNode);
            if (removeNode == root) {
                root = successor;
            } else if (isChildLeft) {
                parentNode.left = successor;
            } else {
                parentNode.right = successor;
            }
            successor.left = removeNode.left;
        }
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
        smallLeftRotation(root);
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

    private void clear(Node node) {
        if (node.left != null) {
            clear(node.left);
        }
        node.left = null;
        if (node.right != null) {
            clear(node.right);
        }
        node.right = null;
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

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private Node getParentNode(Node node, Node child) {
        if (child == root) {
            return root;
        }
        if (node == null) {
            return null;
        }
        if (node.left == child || node.right == child) {
            return node;
        }
        if (child.value < node.value) {
            return getParentNode(node.left, child);
        } else {
            return getParentNode(node.right, child);
        }
    }

    private Node getNodeByValue(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (node.value == value) {
            return node;
        }
        if (value < node.value) {
            return getNodeByValue(node.left, value);
        } else {
            return getNodeByValue(node.right, value);
        }

    }

    private void smallLeftRotation(Node node) {
        if (node != null) {
            if (node.left != null || node.right != null) {
                Node rotation = node.right;
                node.right = rotation.left;
                rotation.left = node;
            }
            smallLeftRotation(node.right);
            smallLeftRotation(node.left);
        }
    }

    private void rootIsNull(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("Root is null.");
        }
    }
}
