package binarytree.impl;

import binarytree.ITree;
import ilist.impl.llist.LList;
import ilist.interfaces.IList;

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
        root = null;
    }

    @Override
    public int size() {
        return 0;
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
        return 0;
    }

    @Override
    public int nodes() {
        return 0;
    }

    @Override
    public int leaves() {
        if (root == null) {
            return 0;
        }
        int count = 0;
        Node leftNode = root.left;
        while (leftNode != null) {
            if (leftNode.left == null && leftNode.right == null) {
                count++;
            }
            if (leftNode.left != null && leftNode.right == null) {
                leftNode = leftNode.left;
            } else if (leftNode.left == null && leftNode.right != null) {
                leftNode = leftNode.right;
            }
        }
        return count;
    }

    @Override
    public void reverse() {

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


    private void toArray(IList myList, Node node) {
        if (node != null) {
            toArray(myList, node.left);
            myList.add(node.value);
            toArray(myList, node.right);
        }
    }

}
